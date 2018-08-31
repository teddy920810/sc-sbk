package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunningData;
import com.heeexy.example.repository.RunGroupRepository;
import com.heeexy.example.repository.RunningRepository;
import com.heeexy.example.repository.WxUserRepository;
import com.heeexy.example.service.RunningDataService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RunningDataServiceImpl implements RunningDataService {

    @Autowired
    private RunningRepository runningRepository;

    @Autowired
    private WxUserRepository wxUserRepository;

    @Autowired
    private RunGroupRepository runGroupRepository;

    @Override
    public JSONObject listRunningData(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Page<RunningData> runningDatas = runningRepository.findByWxUser_IdAndRunGroup_Id(jsonObject.getLong("wxUserId"),1L,PageableUtil.getPageable(jsonObject.getIntValue("pageNum"), jsonObject.getIntValue("pageRow")));
        return CommonUtil.successPage(jsonObject, runningDatas.getContent(), Integer.valueOf(Long.toString(runningDatas.getTotalElements())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RunningData addRunData(RunningData runningData) {
        return runningRepository.save(runningData);
    }

    @Override
    public List<RunningData> findTop4(Long wxUserId, Long runGroupId, Integer status) {
        return runningRepository.findTop4ByWxUser_IdAndRunGroup_IdAndStatusOrderByRunningDateTimeDesc(wxUserId,runGroupId,status);
    }

    @Override
    public Page<RunningData> findUserRunData(Long wxUserId, Long runGroupId, int pageNum, int pageSize) {
        Page<RunningData> runningDatas = runningRepository.findByWxUser_IdAndRunGroup_Id(wxUserId,runGroupId,PageableUtil.getPageable(pageNum, pageSize, Sort.Direction.DESC,"runningDateTime"));
        return runningDatas;
    }
}
