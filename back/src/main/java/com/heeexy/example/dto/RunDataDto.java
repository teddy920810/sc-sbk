package com.heeexy.example.dto;

public class RunDataDto  extends DtoObject {

    private String runTime;

    private String speed;

    private String totalmile;

    private String timeCost;

    private String image;

    private String groupId;

    private String userId;

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTotalmile() {
        return totalmile;
    }

    public void setTotalmile(String totalmile) {
        this.totalmile = totalmile;
    }

    public String getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(String timeCost) {
        this.timeCost = timeCost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RunDataDto(String runTime, String speed, String totalmile, String timeCost, String image, String groupId, String userId) {
        this.runTime = runTime;
        this.speed = speed;
        this.totalmile = totalmile;
        this.timeCost = timeCost;
        this.image = image;
        this.groupId = groupId;
        this.userId = userId;
    }

    protected RunDataDto() {
    }
}
