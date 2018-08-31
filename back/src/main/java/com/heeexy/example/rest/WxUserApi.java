package com.heeexy.example.rest;


import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.WxUser;
import com.heeexy.example.service.UserRunLogForGroupService;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.QiniuUtil;
import com.heeexy.example.util.RandomUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import com.heeexy.example.util.jwt.JwtToken;
import com.heeexy.example.util.jwt.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/wx/")
public class WxUserApi {

    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private UserRunLogForGroupService userRunLogForGroupService;

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private QiniuUtil qiniuUtil;

    @PostMapping("/login")
    public JSONObject login(String avatarUrl, String city, String country,
                            String gender, String language, String nickName,
                            String province, String code){
        WxUser user = wxUserService.login(new WxUser(avatarUrl,city,country,gender,language,nickName,province,""),code);
        JSONObject resultJson = new JSONObject();

        if(user!=null){
            // 验证用户名密码成功后生成token
            String token = tokenUtil.generateToken(user.getOpenid());
            // 构建JwtToken
            JwtToken jwtToken = JwtToken.builder().token(token).principal(user.getOpenid()).build();
            Subject subject = SecurityUtils.getSubject();
            try {
                // 该方法会调用JwtRealm中的doGetAuthenticationInfo方法
                subject.login(jwtToken);
            } catch (UnknownAccountException exception) {
                exception.printStackTrace();
                System.out.println("账号不存在");
            } catch (IncorrectCredentialsException exception) {
                exception.printStackTrace();
                System.out.println("错误的凭证，用户名或密码不正确");
            } catch (LockedAccountException exception) {
                exception.printStackTrace();
                System.out.println("账户已锁定");
            } catch (ExcessiveAttemptsException exception) {
                exception.printStackTrace();
                System.out.println("错误次数过多");
            } catch (AuthenticationException exception) {
                exception.printStackTrace();
                System.out.println("认证失败");
            }
            // 认证通过
            if(subject.isAuthenticated()){
                resultJson.put("token",token);
                resultJson.put("openid",user.getOpenid());
                resultJson.put("nickName",user.getNickName());
                resultJson.put("wxUserId",user.getId());
                return CommonUtil.successJson(resultJson);
            }else {
                return CommonUtil.errorJson(ErrorEnum.E_20011);
            }
        }
        return CommonUtil.errorJson(ErrorEnum.E_20011);

    }

    @PostMapping("/register")
    public WxUser register(WxUser user){
        return wxUserService.register(user);
    }


    @GetMapping("/runinfo")
    public JSONObject runinfo(Long userId){
        return CommonUtil.successJson(userRunLogForGroupService.findByWxUserId(userId));
    }

    @PostMapping("updateNickName")
    public JSONObject updateNickName(@RequestParam("wxUserId") Long wxUserId, @RequestParam("nickName")String nickName){
        WxUser user = wxUserService.updateNickNameById(wxUserId,nickName);
        return CommonUtil.successJson(user);
    }

    /**
     * 上传文件到七牛云存储
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/img/qiniu")
    public String uploadImgQiniu(@RequestParam("imgPath") MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path = qiniuUtil.uploadImg(inputStream, RandomUtil.genarateId("img"));
        return path;
    }
}
