package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.TrainingCheckLog;
import com.heeexy.example.entity.WxUser;
import com.heeexy.example.repository.TrainingCheckLogRepository;
import com.heeexy.example.service.TrainingCheckLogService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingCheckLogServiceImpl implements TrainingCheckLogService {

    @Autowired
    private TrainingCheckLogRepository trainingCheckLogRepository;
    @Override
    public JSONObject listTrainingCheckLogByTrainingCheck(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Page<TrainingCheckLog> trainingCheckLogs =  trainingCheckLogRepository.findByTrainingCheck_Id(jsonObject.getLong("trainingCheckId"),PageRequest.of(jsonObject.getIntValue("pageNum")-1, jsonObject.getIntValue("pageRow"), new Sort(Sort.Direction.DESC, "createdDateTime")));
        return CommonUtil.successPage(jsonObject, trainingCheckLogs.getContent(), Integer.valueOf(Long.toString(trainingCheckLogs.getTotalElements())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TrainingCheckLog addTrainingCheckLog(TrainingCheckLog trainingCheckLog) {
        return trainingCheckLogRepository.save(trainingCheckLog);
    }
}
