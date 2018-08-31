package com.heeexy.example.rest;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunningData;
import com.heeexy.example.entity.WxUser;
import com.heeexy.example.service.RunGroupService;
import com.heeexy.example.service.RunningDataService;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.FileUpload;
import com.heeexy.example.util.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/wx")
public class WxRunningApi {

    @Autowired
    private RunningDataService runningDataService;
    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private RunGroupService runGroupService;

    @PostMapping("/addRecord")
    public JSONObject addRecord(@RequestParam("wxUserId") Long wxUserId,
                                 @RequestParam("runningDateTime") String runningDateTime, @RequestParam("speed")Double speed,
                                 @RequestParam("totalMile") Double totalMile, @RequestParam("timeCost")Long timeCost,
                                 @RequestParam("groupId")Long groupId,@RequestParam("imageData")String imageData){
        WxUser user = wxUserService.findById(wxUserId);
        RunningData runningData = new RunningData(CommonUtil.string2LocalDateTime(runningDateTime+" 00:00:00"),speed,imageData,totalMile,timeCost);
        runningData.setWxUser(user);
        runningData.setRunGroup(runGroupService.findById(groupId));
        return CommonUtil.successJson(runningDataService.addRunData(runningData));
    }

    @RequestMapping("/uploadImg")
    public JSONObject upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        String filePath = CommonUtil.getClasspath() + Constants.FILEPATHIMG ;      //文件上传路径
        return CommonUtil.successJson(FileUpload.fileUp(file, filePath, UUID.randomUUID().toString()));
    }


    @GetMapping("/userRunDatas")
    public JSONObject loadMyRunData(int pageNum,int pageSize,Long wxUserId,Long groupId){
        Page<RunningData> pageList = runningDataService.findUserRunData(wxUserId,groupId,pageNum,pageSize);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNum",pageNum);
        jsonObject.put("countNum",pageList.getTotalElements());
        jsonObject.put("runDataList",pageList.getContent());
        return CommonUtil.successJson(jsonObject);
    }
}
