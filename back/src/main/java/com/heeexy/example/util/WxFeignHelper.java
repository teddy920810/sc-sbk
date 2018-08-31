package com.heeexy.example.util;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.feign.WxFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WxFeignHelper {

    private static final String GRANT_TYPE = "authorization_code";

    private static final String APPID = "wx028ae475db2b68eb";

    private static final String SECRET = "75844e600a5813b9a4c55564fac86931";

    private static final String LANG_ZH_CN = "zh_CN";

    private WxFeign wxFeign;

    @Autowired
    public WxFeignHelper(final WxFeign wxFeign) {
        this.wxFeign = wxFeign;
    }

    public JSONObject getWxOpenid(String code) {
        JSONObject response = wxFeign.getWxOpenid(APPID,SECRET,code,GRANT_TYPE);
        return response;
    }
}
