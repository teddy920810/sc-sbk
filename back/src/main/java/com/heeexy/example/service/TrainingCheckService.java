package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.TrainingCheck;
import org.springframework.data.domain.Page;

public interface TrainingCheckService {

    /**
     * admin-page list
     * @param jsonObject
     * @return
     */
    JSONObject listTrainingCheck(JSONObject jsonObject);

    /**
     * admin-insert
     *
     * @param requestJson
     * @return
     */
    JSONObject addTrainCheck(JSONObject requestJson);


    TrainingCheck findById(Long id);

    /**
     * admin-update
     * @param requestJson
     * @return
     */
    JSONObject updateTrainCheck(JSONObject requestJson);

    Page<TrainingCheck> findByPage(int pageNum,int pageSize);
}
