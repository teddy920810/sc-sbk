package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.TrainingCheck;
import com.heeexy.example.repository.TrainingCheckRepository;
import com.heeexy.example.service.TrainingCheckService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainingCheckServiceImpl implements TrainingCheckService {

    @Autowired
    private TrainingCheckRepository trainingCheckRepository;
    @Override
    public JSONObject listTrainingCheck(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        Page<TrainingCheck> users =  trainingCheckRepository.findAll(PageRequest.of(jsonObject.getIntValue("pageNum")-1, jsonObject.getIntValue("pageRow"), new Sort(Sort.Direction.DESC, "createdDateTime")));
        return CommonUtil.successPage(jsonObject, users.getContent(), Integer.valueOf(Long.toString(users.getTotalElements())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addTrainCheck(JSONObject requestJson) {
        TrainingCheck trainingCheck = JSONObject.toJavaObject(requestJson,TrainingCheck.class);
        trainingCheckRepository.save(trainingCheck);
        return CommonUtil.successJson();
    }

    @Override
    public TrainingCheck findById(Long id) {
        return trainingCheckRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateTrainCheck(JSONObject requestJson) {
        TrainingCheck trainingCheck = JSONObject.toJavaObject(requestJson,TrainingCheck.class);
        trainingCheckRepository.save(trainingCheck);
        return CommonUtil.successJson();
    }

    @Override
    public Page<TrainingCheck> findByPage(int pageNum, int pageSize) {
        return trainingCheckRepository.findAll(PageableUtil.getPageable(pageNum, pageSize, Sort.Direction.DESC,"trainingDate"));
    }
}
