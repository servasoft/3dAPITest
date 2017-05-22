package com.serva.model;

import java.util.Date;
import java.util.Map;

/**
 * Created by alexdong on 17/21/05.
 */
public class Alarm {

    private String alarmId;
    private String deviceId;
    private String alarmType;
    private String level;
    private String description;
    private Date time;
    private Date ackTime;
    private String ackNotice;
    private String devIp;
    private Map<String, Object> client;

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getAckTime() {
        return ackTime;
    }

    public void setAckTime(Date ackTime) {
        this.ackTime = ackTime;
    }

    public String getAckNotice() {
        return ackNotice;
    }

    public void setAckNotice(String ackNotice) {
        this.ackNotice = ackNotice;
    }

    public String getDevIp() {
        return devIp;
    }

    public void setDevIp(String devIp) {
        this.devIp = devIp;
    }

    public Map<String, Object> getClient() {
        return client;
    }

    public void setClient(Map<String, Object> client) {
        this.client = client;
    }
}
