package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseContentList implements Parcelable,Comparable<CourseContentList>, Serializable {

    private String bookStatus;
    private int chapters;  //电子书阅读进度（n章）,课程观看进度（n%）
    private int chargeType;
    private int freeSee;
    private int id;
    private String name;
    private boolean isSelected;

    private List<String> infoCovers;
    private String infoTitle;
    private boolean isBought;
    private boolean isFreeCourse;
    private float linePrice;
    private int mediaTime;
    private String mentorName;
    private String mentorPictureUrl;
    private int no;
    private float price;
    private int readCount;
    private ArrayList<SimpleStringMode> tags;
    private int type;

    private String courseName;
    private String title;
    private String coverUrl;
    private String classesAddress;
    private long classesTime;
    private String courseUuid;
    private int progress;
    private String infoCover;
    private String playUrl;
    private String courseTitle;
    private int studyCount;
    private int praiseCount;
    private String chaptersName;
    private String mentorIcon;
    private boolean isCollect;
    private boolean isShowMore;
    private long historyTime;
    private String mentorUuid;
    private boolean courseInfoUpdateStatus;
    private long createTime;
    private int courseTypeInfoCount;
    private int drawable;

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getMentorUuid() {
        return mentorUuid;
    }

    public void setMentorUuid(String mentorUuid) {
        this.mentorUuid = mentorUuid;
    }

    public String getChaptersName() {
        return chaptersName;
    }

    public long getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(long historyTime) {
        this.historyTime = historyTime;
    }

    public boolean isShowMore() {
        return isShowMore;
    }

    public void setShowMore(boolean showMore) {
        isShowMore = showMore;
    }

    public CourseContentList() {
    }

    protected CourseContentList(Parcel in) {
        bookStatus = in.readString();
        chapters = in.readInt();
        chargeType = in.readInt();
        freeSee = in.readInt();
        id = in.readInt();
        infoCovers = in.createStringArrayList();
        infoTitle = in.readString();
        isBought = in.readByte() != 0;
        isFreeCourse = in.readByte() != 0;
        linePrice = in.readFloat();
        mediaTime = in.readInt();
        mentorName = in.readString();
        mentorPictureUrl = in.readString();
        no = in.readInt();
        price = in.readFloat();
        readCount = in.readInt();
        tags = in.createTypedArrayList(SimpleStringMode.CREATOR);
        type = in.readInt();
        courseName = in.readString();
        title = in.readString();
        coverUrl = in.readString();
        classesAddress = in.readString();
        classesTime = in.readLong();
        courseUuid = in.readString();
        progress = in.readInt();
        infoCover = in.readString();
        playUrl = in.readString();
        courseTitle = in.readString();
        name = in.readString();
        studyCount = in.readInt();
        praiseCount = in.readInt();
        chaptersName = in.readString();
        mentorIcon = in.readString();
        isCollect = in.readByte() != 0;
        historyTime = in.readLong();
        mentorUuid = in.readString();
        courseInfoUpdateStatus = in.readByte() != 0;
        createTime = in.readLong();
        courseTypeInfoCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookStatus);
        dest.writeInt(chapters);
        dest.writeInt(chargeType);
        dest.writeInt(freeSee);
        dest.writeInt(id);
        dest.writeStringList(infoCovers);
        dest.writeString(infoTitle);
        dest.writeByte((byte) (isBought ? 1 : 0));
        dest.writeByte((byte) (isFreeCourse ? 1 : 0));
        dest.writeFloat(linePrice);
        dest.writeInt(mediaTime);
        dest.writeString(mentorName);
        dest.writeString(mentorPictureUrl);
        dest.writeInt(no);
        dest.writeFloat(price);
        dest.writeInt(readCount);
        dest.writeTypedList(tags);
        dest.writeInt(type);
        dest.writeString(courseName);
        dest.writeString(title);
        dest.writeString(coverUrl);
        dest.writeString(classesAddress);
        dest.writeLong(classesTime);
        dest.writeString(courseUuid);
        dest.writeInt(progress);
        dest.writeString(infoCover);
        dest.writeString(playUrl);
        dest.writeString(courseTitle);
        dest.writeString(name);
        dest.writeInt(studyCount);
        dest.writeInt(praiseCount);
        dest.writeString(chaptersName);
        dest.writeString(mentorIcon);
        dest.writeByte((byte) (isCollect ? 1 : 0));
        dest.writeLong(historyTime);
        dest.writeString(mentorUuid);
        dest.writeByte((byte) (courseInfoUpdateStatus ? 1 : 0));
        dest.writeLong(createTime);
        dest.writeInt(courseTypeInfoCount);
    }

    public boolean isCourseInfoUpdateStatus() {
        return courseInfoUpdateStatus;
    }

    public int getCourseTypeInfoCount() {
        return courseTypeInfoCount;
    }

    public void setCourseTypeInfoCount(int courseTypeInfoCount) {
        this.courseTypeInfoCount = courseTypeInfoCount;
    }

    public void setCourseInfoUpdateStatus(boolean courseInfoUpdateStatus) {
        this.courseInfoUpdateStatus = courseInfoUpdateStatus;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public int getStudyCount() {
        return studyCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseContentList> CREATOR = new Creator<CourseContentList>() {
        @Override
        public CourseContentList createFromParcel(Parcel in) {
            return new CourseContentList(in);
        }

        @Override
        public CourseContentList[] newArray(int size) {
            return new CourseContentList[size];
        }
    };

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getMentorIcon() {
        return mentorIcon;
    }

    public void setMentorIcon(String mentorIcon) {
        this.mentorIcon = mentorIcon;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getInfoCover() {
        return infoCover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public boolean isFreeCourse() {
        return isFreeCourse;
    }

    public void setFreeCourse(boolean freeCourse) {
        isFreeCourse = freeCourse;
    }

    public String getClassesAddress() {
        return classesAddress;
    }

    public void setClassesAddress(String classesAddress) {
        this.classesAddress = classesAddress;
    }

    public long getClassesTime() {
        return classesTime;
    }

    public void setClassesTime(long classesTime) {
        this.classesTime = classesTime;
    }

    public String getCourseUuid() {
        return courseUuid;
    }

    public void setCourseUuid(String courseUuid) {
        this.courseUuid = courseUuid;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChargeType(int chargeType) {
        this.chargeType = chargeType;
    }

    public int getChargeType() {
        return chargeType;
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

    public void setInfoCovers(List<String> infoCovers) {
        this.infoCovers = infoCovers;
    }

    public List<String> getInfoCovers() {
        return infoCovers;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setIsBought(boolean isBought) {
        this.isBought = isBought;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public void setIsFreeCourse(boolean isFreeCourse) {
        this.isFreeCourse = isFreeCourse;
    }

    public boolean getIsFreeCourse() {
        return isFreeCourse;
    }

    public void setLinePrice(float linePrice) {
        this.linePrice = linePrice;
    }

    public float getLinePrice() {
        return linePrice;
    }

    public void setMediaTime(int mediaTime) {
        this.mediaTime = mediaTime;
    }

    public int getMediaTime() {
        return mediaTime;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorPictureUrl(String mentorPictureUrl) {
        this.mentorPictureUrl = mentorPictureUrl;
    }

    public String getMentorPictureUrl() {
        return mentorPictureUrl;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setTags(ArrayList<SimpleStringMode> tags) {
        this.tags = tags;
    }

    public ArrayList<SimpleStringMode> getTags() {
        return tags;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(CourseContentList o) {

        if (o.getHistoryTime() > this.historyTime) {
            return -1;
        }else if (o.getHistoryTime() < this.historyTime){
            return 1;
        }

        return 0;
    }
}
