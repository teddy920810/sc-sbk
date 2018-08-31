package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunGroup;
import com.heeexy.example.entity.UserRunLogForGroup;
import com.heeexy.example.repository.RunGroupRepository;
import com.heeexy.example.service.RunGroupService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RunGroupServiceImpl implements RunGroupService{

    @Autowired
    private RunGroupRepository runGroupRepository;

    @Override
    public RunGroup findById(Long id) {
        return runGroupRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RunGroup addRunGroup(RunGroup runGroup) {
        return runGroupRepository.save(runGroup);
    }

    @Override
    public JSONObject listRunGroup(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Page<RunGroup> runGroups = runGroupRepository.findAll(PageableUtil.getPageable(jsonObject.getIntValue("pageNum"), jsonObject.getIntValue("pageRow")));
        return CommonUtil.successPage(jsonObject, runGroups.getContent(), Integer.valueOf(Long.toString(runGroups.getTotalElements())));
    }


}
