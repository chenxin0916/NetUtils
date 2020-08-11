package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

public class Gifts implements Parcelable {


    private String name;
    private String pictureUrl;
    private float price;
    private int type;
    private String mentorName;



    protected Gifts(Parcel in) {
        name = in.readString();
        pictureUrl = in.readString();
        price = in.readFloat();
        type = in.readInt();
        mentorName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pictureUrl);
        dest.writeFloat(price);
        dest.writeInt(type);
        dest.writeString(mentorName);
    }
    public String getMentorName() {
        return mentorName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Gifts> CREATOR = new Creator<Gifts>() {
        @Override
        public Gifts createFromParcel(Parcel in) {
            return new Gifts(in);
        }

        @Override
        public Gifts[] newArray(int size) {
            return new Gifts[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
