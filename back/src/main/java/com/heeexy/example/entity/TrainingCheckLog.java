package com.heeexy.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class TrainingCheckLog extends DomainObject {

    @ManyToOne
    private TrainingCheck trainingCheck;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 签到时间
     */
    private LocalDateTime checkTime;

    @ManyToOne
    private WxUser wxUser;

    public TrainingCheck getTrainingCheck() {
        return trainingCheck;
    }

    public void setTrainingCheck(TrainingCheck trainingCheck) {
        this.trainingCheck = trainingCheck;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public TrainingCheckLog(TrainingCheck trainingCheck, String latitude, String longitude, LocalDateTime checkTime, WxUser wxUser) {
        this.trainingCheck = trainingCheck;
        this.latitude = latitude;
        this.longitude = longitude;
        this.checkTime = checkTime;
        this.wxUser = wxUser;
    }

    public TrainingCheckLog() {
    }
}
