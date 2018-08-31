package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.RunningData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RunningDataService {

    JSONObject listRunningData(JSONObject jsonObject);

    RunningData addRunData(RunningData runningData);

    List<RunningData> findTop4(Long wxUserId,Long runGroupId,Integer status);

    Page<RunningData> findUserRunData(Long wxUserId, Long runGroupId, int pageNum, int pageSize);
}
