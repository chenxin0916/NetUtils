package com.yinguojiaoyu.netutils;

import java.util.ArrayList;

public class LoginInfo {

   private String deviceType;
   private String headUrl;
   private boolean isNewUser;
   private String name;
   private String openId;
   private String phone;
   private int sex;
   private String token;
   private String uuid;
   private String birthday;
   private String incomeStage;
   private String loveStatus;
   private ArrayList<String> interests;
   private String professionalStatus;
   private boolean isVisitor;

    public boolean isVisitor() {
        return isVisitor;
    }

    public void setVisitor(boolean visitor) {
        isVisitor = visitor;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIncomeStage() {
        return incomeStage;
    }

    public void setIncomeStage(String incomeStage) {
        this.incomeStage = incomeStage;
    }

    public String getLoveStatus() {
        return loveStatus;
    }

    public void setLoveStatus(String loveStatus) {
        this.loveStatus = loveStatus;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public String getProfessionalStatus() {
        return professionalStatus;
    }

    public void setProfessionalStatus(String professionalStatus) {
        this.professionalStatus = professionalStatus;
    }

    public String getDeviceType() {
          return deviceType;
     }

     public void setDeviceType(String deviceType) {
          this.deviceType = deviceType;
     }

     public String getHeadUrl() {
          return headUrl;
     }

     public void setHeadUrl(String headUrl) {
          this.headUrl = headUrl;
     }

     public boolean isNewUser() {
          return isNewUser;
     }

     public void setNewUser(boolean newUser) {
          isNewUser = newUser;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getOpenId() {
          return openId;
     }

     public void setOpenId(String openId) {
          this.openId = openId;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
     }

     public int getSex() {
          return sex;
     }

     public void setSex(int sex) {
          this.sex = sex;
     }

     public String getToken() {
          return token;
     }

     public void setToken(String token) {
          this.token = token;
     }

     public String getUuid() {
          return uuid;
     }

     public void setUuid(String uuid) {
          this.uuid = uuid;
     }
}
