package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseClasses implements Parcelable {

    private String classesAddress;
    private int classesMember;
    private long classesTime;
    private int joinMember;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected CourseClasses(Parcel in) {
        classesAddress = in.readString();
        classesMember = in.readInt();
        classesTime = in.readLong();
        joinMember = in.readInt();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(classesAddress);
        dest.writeInt(classesMember);
        dest.writeLong(classesTime);
        dest.writeInt(joinMember);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseClasses> CREATOR = new Creator<CourseClasses>() {
        @Override
        public CourseClasses createFromParcel(Parcel in) {
            return new CourseClasses(in);
        }

        @Override
        public CourseClasses[] newArray(int size) {
            return new CourseClasses[size];
        }
    };

    public void setClassesAddress(String classesAddress) {
        this.classesAddress = classesAddress;
    }

    public String getClassesAddress() {
        return classesAddress;
    }

    public void setClassesMember(int classesMember) {
        this.classesMember = classesMember;
    }

    public int getClassesMember() {
        return classesMember;
    }

    public void setClassesTime(long classesTime) {
        this.classesTime = classesTime;
    }

    public long getClassesTime() {
        return classesTime;
    }

    public void setJoinMember(int joinMember) {
        this.joinMember = joinMember;
    }

    public int getJoinMember() {
        return joinMember;
    }
}
