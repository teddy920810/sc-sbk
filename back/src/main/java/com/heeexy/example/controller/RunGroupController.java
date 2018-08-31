package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunGroup;
import com.heeexy.example.service.RunGroupService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/runGroup")
public class RunGroupController {

    @Autowired
    private RunGroupService runGroupService;

    //打卡规则
    @PostMapping("/addRunGroup")
    public JSONObject createRunGroup(@RequestBody JSONObject requestJson){
        RunGroup  runGroup = requestJson.toJavaObject(RunGroup.class);
        runGroupService.addRunGroup(runGroup);
        return CommonUtil.successJson();
    }


    @GetMapping
    public JSONObject runGroupList(HttpServletRequest request) {
        return runGroupService.listRunGroup(CommonUtil.request2Json(request));
    }
}
