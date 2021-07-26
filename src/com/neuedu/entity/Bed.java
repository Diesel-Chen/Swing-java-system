package com.neuedu.entity;

import com.neuedu.utils.CommonUtil;

/**
 * 床位管理实体类
 *
 * @author
 * @date 2021-7-10
 */
public class Bed {
    private String id;
    private String checkInTime;
    private String checkOutTime;
    private String status;
    private String patientName;

    public Bed(String checkInTime, String checkOutTime, String status, String patientName) {
        this.id = CommonUtil.GenerateId();
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.patientName = patientName;
    }

    public Bed(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
