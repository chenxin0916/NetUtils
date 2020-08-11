package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserResult implements Parcelable {

    private String content;
    private ArrayList<String> picture;

    protected UserResult(Parcel in) {
        content = in.readString();
        picture = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeStringList(picture);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserResult> CREATOR = new Creator<UserResult>() {
        @Override
        public UserResult createFromParcel(Parcel in) {
            return new UserResult(in);
        }

        @Override
        public UserResult[] newArray(int size) {
            return new UserResult[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  ArrayList<String> getPicture() {
        return picture;
    }

    public void setPicture( ArrayList<String> picture) {
        this.picture = picture;
    }
}
