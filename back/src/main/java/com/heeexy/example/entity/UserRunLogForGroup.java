package com.heeexy.example.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class UserRunLogForGroup extends DomainObject{

    @ManyToOne
    private WxUser wxUser;

    @ManyToOne
    private RunGroup runGroup;

    private Double totalMlie= new Double(0);

    private Double avgSpeed= new Double(0);

    private Integer ranking;

    private Integer totalIntegral = 0;

    /**
     * 打卡类型（按天、周、月）
     */
    private Integer type;

    private Integer needTimes;

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public RunGroup getRunGroup() {
        return runGroup;
    }

    public void setRunGroup(RunGroup runGroup) {
        this.runGroup = runGroup;
    }

    public Double getTotalMlie() {
        return totalMlie;
    }

    public void setTotalMlie(Double totalMlie) {
        this.totalMlie = totalMlie;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
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

    public UserRunLogForGroup(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public UserRunLogForGroup(WxUser wxUser, RunGroup runGroup) {
        this.wxUser = wxUser;
        this.runGroup = runGroup;
        this.type = runGroup.getType();
        this.needTimes = runGroup.getNeedTimes();
    }

    public static int integralDESC(UserRunLogForGroup obj1, UserRunLogForGroup obj2) {
        return obj2.getTotalIntegral().compareTo(obj2.getTotalIntegral());
    }

    public UserRunLogForGroup(WxUser wxUser, RunGroup runGroup, Double totalMlie, Double avgSpeed, Integer ranking, Integer totalIntegral, Integer type, Integer needTimes) {
        this.wxUser = wxUser;
        this.runGroup = runGroup;
        this.totalMlie = totalMlie;
        this.avgSpeed = avgSpeed;
        this.ranking = ranking;
        this.totalIntegral = totalIntegral;
        this.type = type;
        this.needTimes = needTimes;
    }

    protected UserRunLogForGroup() {
    }
}
