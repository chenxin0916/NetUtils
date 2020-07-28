package com.yinguojiaoyu.netlib.cache;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CacheMode {
    private long saveTime;
    @PrimaryKey
    @NonNull
    private String cacheKey = "";
    private String content;
    private long expireTime;

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(@NonNull String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
