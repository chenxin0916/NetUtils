package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

public class CouponMode implements Parcelable {

     private int id;
     private int type;
     private int amount;
     private String explain;
     private long startTime;
     private long endTime;
     private boolean canBeUsed;
     private int courseId;
     private int courseType;
     private boolean isUsed;

    private CouponMode(Parcel in) {
        id = in.readInt();
        type = in.readInt();
        amount = in.readInt();
        explain = in.readString();
        startTime = in.readLong();
        endTime = in.readLong();
        canBeUsed = in.readByte() != 0;
        courseId = in.readInt();
        courseType = in.readInt();
        isUsed = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(type);
        dest.writeInt(amount);
        dest.writeString(explain);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeByte((byte) (canBeUsed ? 1 : 0));
        dest.writeInt(courseId);
        dest.writeInt(courseType);
        dest.writeByte((byte) (isUsed ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CouponMode> CREATOR = new Creator<CouponMode>() {
        @Override
        public CouponMode createFromParcel(Parcel in) {
            return new CouponMode(in);
        }

        @Override
        public CouponMode[] newArray(int size) {
            return new CouponMode[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public boolean isCanBeUsed() {
        return canBeUsed;
    }

    public void setCanBeUsed(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
