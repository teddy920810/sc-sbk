package com.heeexy.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.repository.RunningRepository;
import com.heeexy.example.service.RunningDataService;
import com.heeexy.example.service.UserRunLogForGroupService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/runningData")
public class RunningDataController {

    @Autowired
    private RunningDataService runningDataService;

    @Autowired
    private UserRunLogForGroupService userRunLogForGroupService;

    @GetMapping(value = "/")
    public JSONObject runDataList(HttpServletRequest request) {
        return runningDataService.listRunningData(CommonUtil.request2Json(request));
    }

    @GetMapping(value = "/ranking")
    public JSONObject runRankList(HttpServletRequest request) {
        return userRunLogForGroupService.listUserRunLogForGroup(CommonUtil.request2Json(request));
    }

    @PostMapping(value = "/check")
    public JSONObject checkRunDataSuccess(HttpServletRequest request) {
        return userRunLogForGroupService.checkRunData(CommonUtil.request2Json(request));
    }


}
