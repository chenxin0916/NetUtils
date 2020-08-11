package com.yinguojiaoyu.netutils.resource;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

public class ResourceDataMode implements Parcelable {

    private String courseUuid;
    private String coverUrl;
    private ArrayList<Catalogues> catalogues;
    private String contentUrl;
    private ArrayList<CourseClasses> courseClasses;
    private ArrayList<CourseLists> courseLists;
    private long createTime;
    private String detailsUrl;
    private DiscountsCourseType discountsCourseType;
    private ArrayList<Gifts> gifts;
    private String infoTitle;
    private boolean isCollected;
    private String mentorName;
    private String mentorPictureUrl;
    private String mentorTeamIntroduce;
    private String mentorUuid;
    private int readCount;
    private List<Comments> reviews;
    private int status;
    private List<ResourceTag> tags;
    private int type;
    private ArrayList<UserResult> userResult;
    private float price;
    private float linePrice;
    private double[] depositStep;
    private ArrayList<CouponMode> couponList;
    private int chargeType; //1.免费;2.全款;3.定金+尾款
    private String courseName;
    private String weChatAccount;
    private boolean isBought;
    private ArrayList<CourseContentList> recommendedInterest;
    private boolean isAttentionMentorTeam;
    private int mentorAttentionCount;
    private int mentorCourseReadCount;
    private String mentorHonor;
    private String shareUrl;
    private int finish;

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public boolean isAttentionMentorTeam() {
        return isAttentionMentorTeam;
    }

    public void setAttentionMentorTeam(boolean attentionMentorTeam) {
        isAttentionMentorTeam = attentionMentorTeam;
    }

    public ArrayList<CourseContentList> getRecommendedInterest() {
        return recommendedInterest;
    }

    public void setRecommendedInterest(ArrayList<CourseContentList> recommendedInterest) {
        this.recommendedInterest = recommendedInterest;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public String getWeChatAccount() {
        return weChatAccount;
    }

    public void setDepositPrice(double[] depositStep) {
        this.depositStep = depositStep;
    }

    public double[] getDepositPrice() {
        return depositStep;
    }

    public String getCourseName() {
        return courseName;
    }

    //!!! 序列化的时候write和in的顺序要一致,
    private ResourceDataMode(Parcel in) {
        courseUuid = in.readString();
        coverUrl = in.readString();
        catalogues = in.createTypedArrayList(Catalogues.CREATOR);
        contentUrl = in.readString();
        courseClasses = in.createTypedArrayList(CourseClasses.CREATOR);
        createTime = in.readLong();
        detailsUrl = in.readString();
        gifts = in.createTypedArrayList(Gifts.CREATOR);
        infoTitle = in.readString();
        isCollected = in.readByte() != 0;
        mentorName = in.readString();
        mentorPictureUrl = in.readString();
        mentorTeamIntroduce = in.readString();
        mentorUuid = in.readString();
        courseName = in.readString();
        readCount = in.readInt();
        status = in.readInt();
        type = in.readInt();
        userResult = in.createTypedArrayList(UserResult.CREATOR);
        price = in.readFloat();
        linePrice = in.readFloat();
        chargeType = in.readInt();
        weChatAccount = in.readString();
        couponList = in.createTypedArrayList(CouponMode.CREATOR);
        depositStep = in.createDoubleArray();
        isBought = in.readByte() != 0;
        mentorAttentionCount = in.readInt();
        mentorCourseReadCount = in.readInt();
        shareUrl = in.readString();
        finish = in.readInt();
    }

    public ResourceDataMode() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseUuid);
        dest.writeString(coverUrl);
        dest.writeTypedList(catalogues);
        dest.writeString(contentUrl);
        dest.writeTypedList(courseClasses);
        dest.writeLong(createTime);
        dest.writeString(detailsUrl);
        dest.writeTypedList(gifts);
        dest.writeString(infoTitle);
        dest.writeByte((byte) (isCollected ? 1 : 0));
        dest.writeString(mentorName);
        dest.writeString(mentorPictureUrl);
        dest.writeString(mentorTeamIntroduce);
        dest.writeString(mentorUuid);
        dest.writeString(courseName);
        dest.writeInt(readCount);
        dest.writeInt(status);
        dest.writeInt(type);
        dest.writeTypedList(userResult);
        dest.writeFloat(price);
        dest.writeFloat(linePrice);
        dest.writeInt(chargeType);
        dest.writeString(weChatAccount);
        dest.writeTypedList(couponList);
        dest.writeDoubleArray(depositStep);
        dest.writeByte((byte) (isBought ? 1 : 0));
        dest.writeInt(mentorAttentionCount);
        dest.writeInt(mentorCourseReadCount);
        dest.writeString(shareUrl);
        dest.writeInt(finish);

    }

    public int getMentorAttentionCount() {
        return mentorAttentionCount;
    }

    public void setMentorAttentionCount(int mentorAttentionCount) {
        this.mentorAttentionCount = mentorAttentionCount;
    }

    public int getMentorCourseReadCount() {
        return mentorCourseReadCount;
    }

    public void setMentorCourseReadCount(int mentorCourseReadCount) {
        this.mentorCourseReadCount = mentorCourseReadCount;
    }

    public String getMentorHonor() {
        return mentorHonor;
    }

    public void setMentorHonor(String mentorHonor) {
        this.mentorHonor = mentorHonor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResourceDataMode> CREATOR = new Creator<ResourceDataMode>() {
        @Override
        public ResourceDataMode createFromParcel(Parcel in) {
            return new ResourceDataMode(in);
        }

        @Override
        public ResourceDataMode[] newArray(int size) {
            return new ResourceDataMode[size];
        }
    };

    public int getChargeType() {
        return chargeType;
    }

    public void setChargeType(int chargeType) {
        this.chargeType = chargeType;
    }

    public ArrayList<CouponMode> getCouponList() {
        return couponList;
    }

    public void setCouponList(ArrayList<CouponMode> couponList) {
        this.couponList = couponList;
    }

    public String getCourseUuid() {
        return courseUuid;
    }

    public void setCourseUuid(String courseUuid) {
        this.courseUuid = courseUuid;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(float linePrice) {
        this.linePrice = linePrice;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCatalogues(ArrayList<Catalogues> catalogues) {
        this.catalogues = catalogues;
    }

    public ArrayList<Catalogues> getCatalogues() {
        return catalogues;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setCourseClasses(ArrayList<CourseClasses> courseClasses) {
        this.courseClasses = courseClasses;
    }

    public ArrayList<CourseClasses> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseLists(ArrayList<CourseLists> courseLists) {
        this.courseLists = courseLists;
    }

    public ArrayList<CourseLists> getCourseLists() {
        return courseLists;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDiscountsCourseType(DiscountsCourseType discountsCourseType) {
        this.discountsCourseType = discountsCourseType;
    }

    public DiscountsCourseType getDiscountsCourseType() {
        return discountsCourseType;
    }

    public void setGifts(ArrayList<Gifts> gifts) {
        this.gifts = gifts;
    }

    public ArrayList<Gifts> getGifts() {
        return gifts;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public boolean getIsCollected() {
        return isCollected;
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

    public void setMentorTeamIntroduce(String mentorTeamIntroduce) {
        this.mentorTeamIntroduce = mentorTeamIntroduce;
    }

    public String getMentorTeamIntroduce() {
        return mentorTeamIntroduce;
    }

    public void setMentorUuid(String mentorUuid) {
        this.mentorUuid = mentorUuid;
    }

    public String getMentorUuid() {
        return mentorUuid;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReviews(List<Comments> reviews) {
        this.reviews = reviews;
    }

    public List<Comments> getReviews() {
        return reviews;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setTags(List<ResourceTag> tags) {
        this.tags = tags;
    }

    public List<ResourceTag> getTags() {
        return tags;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setUserResult(ArrayList<UserResult> userResult) {
        this.userResult = userResult;
    }

    public ArrayList<UserResult> getUserResult() {
        return userResult;
    }
}
