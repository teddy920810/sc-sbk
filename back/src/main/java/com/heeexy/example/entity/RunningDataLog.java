package com.heeexy.example.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class RunningDataLog extends DomainObject{

    private Long operatorId;

    @OneToOne
    private RunningData runningData;

    private String remark;

    public RunningDataLog() {
    }

    public RunningDataLog(Long operatorId, RunningData runningData, String remark) {
        this.operatorId = operatorId;
        this.runningData = runningData;
        this.remark = remark;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public RunningData getRunningData() {
        return runningData;
    }

    public void setRunningData(RunningData runningData) {
        this.runningData = runningData;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
