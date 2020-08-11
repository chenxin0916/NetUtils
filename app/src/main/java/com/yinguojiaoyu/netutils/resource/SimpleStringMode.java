package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SimpleStringMode implements Parcelable, Serializable {
    private int id;
    private String name;
    private long createTime;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public SimpleStringMode(String name) {
        this.name = name;
    }

    protected SimpleStringMode(Parcel in) {
        id = in.readInt();
        name = in.readString();
        createTime = in.readLong();
        icon = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeLong(createTime);
        dest.writeString(icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleStringMode> CREATOR = new Creator<SimpleStringMode>() {
        @Override
        public SimpleStringMode createFromParcel(Parcel in) {
            return new SimpleStringMode(in);
        }

        @Override
        public SimpleStringMode[] newArray(int size) {
            return new SimpleStringMode[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
