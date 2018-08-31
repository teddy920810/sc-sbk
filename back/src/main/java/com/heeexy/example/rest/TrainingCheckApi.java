package com.heeexy.example.rest;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunningData;
import com.heeexy.example.entity.TrainingCheck;
import com.heeexy.example.entity.TrainingCheckLog;
import com.heeexy.example.entity.WxUser;
import com.heeexy.example.service.TrainingCheckLogService;
import com.heeexy.example.service.TrainingCheckService;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx")
public class TrainingCheckApi {

    @Autowired
    private TrainingCheckLogService trainingCheckLogService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private TrainingCheckService trainingCheckService;

    @PostMapping("/addTrainingCheck")
    public JSONObject addRecord(@RequestParam("trainingCheckId") Long trainingCheckId,
                                @RequestParam("wxUserId") Long wxUserId,
                                @RequestParam("latitude") String latitude,
                                @RequestParam("longitude")String longitude,
                                @RequestParam("checkTime") String checkTime){
        TrainingCheck trainingCheck = trainingCheckService.findById(trainingCheckId);
        WxUser user = wxUserService.findById(wxUserId);
        TrainingCheckLog trainingCheckLog = new TrainingCheckLog(trainingCheck,latitude,longitude,CommonUtil.string2LocalDateTime(checkTime),user);
        return CommonUtil.successJson(trainingCheckLogService.addTrainingCheckLog(trainingCheckLog));
    }

    @GetMapping("/loadTc")
    public JSONObject loadTc(int pageNum,int pageSize){
        Page<TrainingCheck> pageList = trainingCheckService.findByPage(pageNum,pageSize);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNum",pageNum);
        jsonObject.put("countNum",pageList.getTotalElements());
        jsonObject.put("tcList",pageList.getContent());
        return CommonUtil.successJson(jsonObject);
    }

    @GetMapping("/getTc")
    public JSONObject getTc(Long trainingCheckId){
        TrainingCheck tc = trainingCheckService.findById(trainingCheckId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tc",tc);
        return CommonUtil.successJson(jsonObject);
    }
}
