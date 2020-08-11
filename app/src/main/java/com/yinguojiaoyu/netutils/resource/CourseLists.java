package com.yinguojiaoyu.netutils.resource;

import java.util.List;

public class CourseLists {

    private String coverUrl;
    private float linePrice;
    private String mentorName;
    private float price;
    private int status;
    private int studyCount;
    private List<ResourceTag> tags;
    private String title;

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setLinePrice(int linePrice) {
        this.linePrice = linePrice;
    }

    public float getLinePrice() {
        return linePrice;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStudyCount(int studyCount) {
        this.studyCount = studyCount;
    }

    public int getStudyCount() {
        return studyCount;
    }

    public void setTags(List<ResourceTag> tags) {
        this.tags = tags;
    }

    public List<ResourceTag> getTags() {
        return tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
