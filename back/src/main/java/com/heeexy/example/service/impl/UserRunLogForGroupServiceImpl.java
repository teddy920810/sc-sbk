package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunGroup;
import com.heeexy.example.entity.RunningData;
import com.heeexy.example.entity.UserRunLogForGroup;
import com.heeexy.example.entity.WxUser;
import com.heeexy.example.repository.RunningRepository;
import com.heeexy.example.repository.UserRunLogForGroupRepository;
import com.heeexy.example.repository.WxUserRepository;
import com.heeexy.example.service.UserRunLogForGroupService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.DoubleUtil;
import com.heeexy.example.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRunLogForGroupServiceImpl implements UserRunLogForGroupService {

    @Autowired
    private UserRunLogForGroupRepository repository;

    @Autowired
    private RunningRepository runningRepository;

    @Autowired
    private WxUserRepository wxUserRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject checkRunData(JSONObject jsonObjects) {
        RunningData data = runningRepository.findById(jsonObjects.getLong("runDataId")).get();
        data.setStatus(jsonObjects.getIntValue("status"));
        runningRepository.save(data);
        if (data.getStatus().equals(1))
            updateRecord(data.getWxUser(),data.getRunGroup(),data);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRankByGroup(RunGroup runGroup) {
        List<UserRunLogForGroup> datas = repository.findByRunGroup_Id(runGroup.getId());
        datas.sort(UserRunLogForGroup::integralDESC);
        for (int i=0;i<datas.size();i++){
            UserRunLogForGroup data = datas.get(i);
            data.setRanking(i+1);
            repository.save(data);
        }
    }

    @Override
    public JSONObject listUserRunLogForGroup(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Page<UserRunLogForGroup> userRunLogForGroups = repository.findByRunGroup_Id(jsonObject.getLong("runGroupId"), PageableUtil.getPageable(jsonObject.getIntValue("pageNum"), jsonObject.getIntValue("pageRow"), Sort.Direction.ASC,"ranking"));
        return CommonUtil.successPage(jsonObject, userRunLogForGroups.getContent(), Integer.valueOf(Long.toString(userRunLogForGroups.getTotalElements())));
    }

    @Override
    public JSONObject findByWxUserId(Long wxUserId) {
        WxUser u = wxUserRepository.findById(wxUserId).get();
        JSONObject jsonObject = new JSONObject();
        UserRunLogForGroup record = repository.findByWxUser_IdAndRunGroup_Id(wxUserId,1L);
        Page<UserRunLogForGroup> userRunLogForGroups = repository.findByRunGroup_Id(1L, PageableUtil.getPageable(1, 4, Sort.Direction.ASC,"ranking"));
        jsonObject.put("runData",record);
        jsonObject.put("user",u);
        List<RunningData> runningDatas = runningRepository.findTop4ByWxUser_IdAndRunGroup_IdAndStatusOrderByRunningDateTimeDesc(wxUserId,1L,1);
        jsonObject.put("runningDatas",runningDatas);
        jsonObject.put("leaderboard",userRunLogForGroups.getContent());
        return jsonObject;
    }


    private UserRunLogForGroup updateRecord(WxUser user,RunGroup group,RunningData runningData){
        UserRunLogForGroup record = repository.findByWxUser_IdAndRunGroup_Id(user.getId(),group.getId());
        List<RunningData> runningDatas = runningRepository.findByWxUser_IdAndRunGroup_IdAndStatus(user.getId(),group.getId(),1);
        //平均速度
        record.setAvgSpeed(runningDatas.stream().mapToDouble(RunningData::getSpeed).average().getAsDouble());//平均速度
        //积分
        record.setTotalIntegral(record.getTotalIntegral()+runningData.getIntegral());
        //公里数
        record.setTotalMlie(DoubleUtil.sum(record.getTotalMlie(),runningData.getTotalMile()));//公里数
        if(record.getNeedTimes()-1<=0){
            record.setNeedTimes(0);
        }else {
            record.setNeedTimes(record.getNeedTimes()-1);
        }
        return repository.save(record);
    }
}
