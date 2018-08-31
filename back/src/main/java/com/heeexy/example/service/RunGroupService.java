package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunGroup;

public interface RunGroupService {

    RunGroup findById(Long id);

    RunGroup addRunGroup(RunGroup runGroup);

    JSONObject listRunGroup(JSONObject jsonObject);
}
