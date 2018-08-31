package com.heeexy.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class RunningData extends DomainObject{

    @ManyToOne
    private WxUser wxUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "running_datetime")
    private LocalDateTime runningDateTime;

    private Double speed = new Double(0);

    private String imageData;

    private Double totalMile = new Double(0);

    private Integer status = 0;

    private Long timeCost;

    /**
     * 单次打卡可累积的积分
     */
    private Integer integral= 0;

    @ManyToOne(cascade=CascadeType.ALL)
    private RunGroup runGroup;

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public LocalDateTime getRunningDateTime() {
        return runningDateTime;
    }

    public void setRunningDateTime(LocalDateTime runningDateTime) {
        this.runningDateTime = runningDateTime;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public Double getTotalMile() {
        return totalMile;
    }

    public void setTotalMile(Double totalMile) {
        this.totalMile = totalMile;
    }

    public RunGroup getRunGroup() {
        return runGroup;
    }

    public void setRunGroup(RunGroup runGroup) {
        this.runGroup = runGroup;
        this.integral = runGroup.getIntegral();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Long timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public RunningData(WxUser wxUser, LocalDateTime runningDateTime, Double speed, String imageData, Double totalMile, Integer status, Long timeCost, RunGroup runGroup,Integer integral) {
        this.wxUser = wxUser;
        this.runningDateTime = runningDateTime;
        this.speed = speed;
        this.imageData = imageData;
        this.totalMile = totalMile;
        this.status = status;
        this.timeCost = timeCost;
        this.runGroup = runGroup;
        this.integral = integral;
    }

    protected RunningData() {
    }

    public RunningData(LocalDateTime runningDateTime, Double speed, String imageData, Double totalMile, Integer status, Long timeCost,Integer integral) {
        this.runningDateTime = runningDateTime;
        this.speed = speed;
        this.imageData = imageData;
        this.totalMile = totalMile;
        this.status = status;
        this.timeCost = timeCost;
        this.integral = integral;
    }

    public RunningData(LocalDateTime runningDateTime, Double speed, String imageData, Double totalMile, Long timeCost) {
        this.runningDateTime = runningDateTime;
        this.speed = speed;
        this.imageData = imageData;
        this.totalMile = totalMile;
        this.timeCost = timeCost;
    }
}
