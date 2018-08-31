package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunGroup;

public interface UserRunLogForGroupService {

    //审核积分，更新积分

    //更新平均配速

    //更新打卡次数


    //更新总里数

    //更新排行---每晚定时更新 ---根据积分排行

    JSONObject checkRunData(JSONObject jsonObject);

    void updateRankByGroup(RunGroup runGroup);

    JSONObject listUserRunLogForGroup(JSONObject jsonObject);

    JSONObject findByWxUserId(Long wxUserId);
}
