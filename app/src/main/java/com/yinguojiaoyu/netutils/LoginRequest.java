package com.yinguojiaoyu.netutils;

public class LoginRequest {

    private String deviceType;
    private String phone;
    private String msgCode;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
