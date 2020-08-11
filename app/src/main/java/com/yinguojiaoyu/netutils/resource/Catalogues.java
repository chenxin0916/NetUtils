package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

public class Catalogues implements Parcelable {


    private long createTime;
    private int freeSee;  //是否可以免费观看（0：不可以；1：可以）
    private int id;
    private String infoTitle;
    private long mediaTime;
    private String contentUrl;
    private String infoCover;
    private int actualReadCount;
    private int praiseCount;
    private boolean praise;
    private String info;
    private boolean collect;

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getActualReadCount() {
        return actualReadCount;
    }

    public void setActualReadCount(int actualReadCount) {
        this.actualReadCount = actualReadCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }

    public String getInfoCover() {
        return infoCover;
    }

    public void setInfoCover(String infoCover) {
        this.infoCover = infoCover;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    protected Catalogues(Parcel in) {
        createTime = in.readLong();
        freeSee = in.readInt();
        id = in.readInt();
        infoTitle = in.readString();
        mediaTime = in.readLong();
        contentUrl = in.readString();
        infoCover = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(createTime);
        dest.writeInt(freeSee);
        dest.writeInt(id);
        dest.writeString(infoTitle);
        dest.writeLong(mediaTime);
        dest.writeString(contentUrl);
        dest.writeString(infoCover);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Catalogues> CREATOR = new Creator<Catalogues>() {
        @Override
        public Catalogues createFromParcel(Parcel in) {
            return new Catalogues(in);
        }

        @Override
        public Catalogues[] newArray(int size) {
            return new Catalogues[size];
        }
    };

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setFreeSee(int freeSee) {
        this.freeSee = freeSee;
    }

    public int getFreeSee() {
        return freeSee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setMediaTime(long mediaTime) {
        this.mediaTime = mediaTime;
    }

    public long getMediaTime() {
        return mediaTime;
    }

}
