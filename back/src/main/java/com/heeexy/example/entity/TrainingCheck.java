package com.heeexy.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class TrainingCheck extends DomainObject {

    private String trainingName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate trainingDate;

    private String trainingContent;

    @JsonIgnore
    @OneToMany(mappedBy = "trainingCheck",fetch = FetchType.LAZY)
    private List<TrainingCheckLog> trainingCheckLogList;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkBeginTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkEndTime;

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(String trainingContent) {
        this.trainingContent = trainingContent;
    }

    public List<TrainingCheckLog> getTrainingCheckLogList() {
        return trainingCheckLogList;
    }

    public void setTrainingCheckLogList(List<TrainingCheckLog> trainingCheckLogList) {
        this.trainingCheckLogList.clear();
        if(trainingCheckLogList!=null)
            this.trainingCheckLogList.addAll(trainingCheckLogList);
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

    public LocalDateTime getCheckBeginTime() {
        return checkBeginTime;
    }

    public void setCheckBeginTime(LocalDateTime checkBeginTime) {
        this.checkBeginTime = checkBeginTime;
    }

    public LocalDateTime getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(LocalDateTime checkEndTime) {
        this.checkEndTime = checkEndTime;
    }
}
