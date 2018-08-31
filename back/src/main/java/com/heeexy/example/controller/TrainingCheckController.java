package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.TrainingCheckLogService;
import com.heeexy.example.service.TrainingCheckService;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/trainingCheck")
public class TrainingCheckController {

    private static final Logger logger = LogManager.getLogger(TrainingCheckController.class);

    @Autowired
    private TrainingCheckService trainingCheckService;

    @Autowired
    private TrainingCheckLogService trainingCheckLogService;

    /**
     * admin-分页查询集训活动
     * @param request
     * @return
     */
    @GetMapping(value = "/")
    public JSONObject trainingCheckList(HttpServletRequest request) {
        return trainingCheckService.listTrainingCheck(CommonUtil.request2Json(request));
    }


    /**
     * admin-根据集训活动id查询签到记录数据集
     * @param request
     * @return
     */
    @GetMapping(value = "/byTcId")
    public JSONObject runDataList(HttpServletRequest request) {
        return trainingCheckLogService.listTrainingCheckLogByTrainingCheck(CommonUtil.request2Json(request));
    }

    /**
     * admin-管理平台添加一个集训活动
     * @param requestJson
     * @return
     */
    @PostMapping("/addTc")
    public JSONObject addTc(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "trainingName,trainingDate,trainingContent,latitude,longitude,checkBeginTime,checkEndTime");
        return trainingCheckService.addTrainCheck(requestJson);
    }

    /**
     * admin-管理平台更新一个集训活动
     * @param requestJson
     * @return
     */
    @PostMapping("/updateTc")
    public JSONObject updateTc(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,trainingName,trainingDate,trainingContent,latitude,longitude,checkBeginTime,checkEndTime");
        return trainingCheckService.updateTrainCheck(requestJson);
    }

}
