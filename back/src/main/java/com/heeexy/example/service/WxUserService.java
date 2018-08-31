package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.WxUser;

/**
 * 微信用户service
 * @author shiy
 */
public interface WxUserService {

    WxUser login(WxUser user, String code);

    WxUser register(WxUser user);

    JSONObject listWxUser(JSONObject jsonObject);

    WxUser findById(Long id);

    WxUser updateNickNameById(Long wxUserId,String nickName);
}
