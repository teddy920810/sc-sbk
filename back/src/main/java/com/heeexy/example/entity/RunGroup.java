package com.heeexy.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity
public class RunGroup extends DomainObject{

    private String groupName;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    /**
     * 单次打卡可累积的积分
     */
    private Integer integral;

    private Integer type = 1;

    private Integer needTimes;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL,mappedBy="groups",fetch = FetchType.LAZY)
    private List<WxUser> users;

    @JsonIgnore
    @OneToMany(mappedBy = "runGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunningData> runningDatas;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<WxUser> getUsers() {
        return users;
    }

    public void setUsers(List<WxUser> users) {
        this.users = users;
    }

    public List<RunningData> getRunningDatas() {
        return runningDatas;
    }

    public void setRunningDatas(List<RunningData> runningDatas) {
        this.runningDatas = runningDatas;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNeedTimes() {
        return needTimes;
    }

    public void setNeedTimes(Integer needTimes) {
        this.needTimes = needTimes;
    }
}
