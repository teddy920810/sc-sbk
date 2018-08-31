package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.TrainingCheckLog;

public interface TrainingCheckLogService {

    /**
     * admin-管理平台根据集训活动id分页查询
     * @param jsonObject
     * @return 封装的分页对象
     */
    JSONObject listTrainingCheckLogByTrainingCheck(JSONObject jsonObject);

    /**
     * user-添加签到记录
     * @param trainingCheckLog
     * @return
     */
    TrainingCheckLog addTrainingCheckLog(TrainingCheckLog trainingCheckLog);
}
