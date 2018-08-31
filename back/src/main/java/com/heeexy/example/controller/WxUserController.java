package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/wxUser")
public class WxUserController {

    private static final Logger logger = LogManager.getLogger(WxUserController.class);

    @Autowired
    private WxUserService wxUserService;

    @GetMapping(value = "/")
    public JSONObject wxUserList(HttpServletRequest request) {
        return wxUserService.listWxUser(CommonUtil.request2Json(request));
    }

}
