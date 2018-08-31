package com.heeexy.example.feign;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Content-Type:application/json")
public interface WxFeign {
    @RequestLine("GET /sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}")
    JSONObject getWxOpenid(@Param("appid") String appid, @Param("secret") String secret,
                           @Param("js_code")String js_code, @Param("grant_type") String grant_type);
}

