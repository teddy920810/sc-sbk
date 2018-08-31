package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunGroup;
import com.heeexy.example.entity.UserRunLogForGroup;
import com.heeexy.example.entity.WxUser;
import com.heeexy.example.repository.RunGroupRepository;
import com.heeexy.example.repository.UserRunLogForGroupRepository;
import com.heeexy.example.repository.WxUserRepository;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.AesCbcUtil;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.WxFeignHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserRepository wxUserRepository;

    @Autowired
    private RunGroupRepository runGroupRepository;

    @Autowired
    private UserRunLogForGroupRepository userRunLogForGroupRepository;

    @Autowired
    private WxFeignHelper wxFeignHelper;

    @Override
    public WxUser login(WxUser wxUser, String code) {
        JSONObject jsonObject = wxFeignHelper.getWxOpenid(code);
        String openid = jsonObject.getString("openid");
        WxUser user = wxUserRepository.findByOpenid(openid);
        if (user==null){
            wxUser.setOpenid(openid);
            return register(wxUser);
        }else {
            return user;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUser register(WxUser user) {
        if (user.getGroups()==null){
            Optional<RunGroup> group = runGroupRepository.findById(1L);
            List<RunGroup> groups = new ArrayList<>();
            groups.add(group.get());
            user.setGroups(groups);
            wxUserRepository.save(user);
            UserRunLogForGroup userRunLogForGroup = new UserRunLogForGroup(user,group.get());
            userRunLogForGroupRepository.save(userRunLogForGroup);
        }
        return user;
    }

    @Override
    public JSONObject listWxUser(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Page<WxUser> users =  wxUserRepository.findAll(PageRequest.of(jsonObject.getIntValue("pageNum")-1, jsonObject.getIntValue("pageRow"), new Sort(Sort.Direction.DESC, "createdDateTime")));
        return CommonUtil.successPage(jsonObject, users.getContent(), Integer.valueOf(Long.toString(users.getTotalElements())));
    }

    @Override
    public WxUser findById(Long id) {
        return wxUserRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUser updateNickNameById(Long wxUserId, String nickName) {
        WxUser user = wxUserRepository.findById(wxUserId).get();
        user.setNickName(nickName);
        return wxUserRepository.save(user);
    }
}
