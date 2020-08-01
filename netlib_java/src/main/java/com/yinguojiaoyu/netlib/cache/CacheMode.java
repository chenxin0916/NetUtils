package com.yinguojiaoyu.netlib.cache;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.yinguojiaoyu.netlib.common.HttpUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.HashMap;

@Entity
public class CacheMode {
    private long saveTime;
    @PrimaryKey
    @NonNull
    private String cacheKey = "";
    private String content;
    private long expireTime;
    @Ignore
    CacheType cacheType;

    public CacheMode() {
    }

    public CacheMode(@NonNull String cacheKey, long expireTime, CacheType cacheType) {
        this.cacheKey = cacheKey;
        this.expireTime = expireTime;
        this.cacheType = cacheType;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    @NotNull
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


    public CacheType getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    public boolean isSaveCache(){
        return cacheType == CacheType.FIRST_CACHE_THEN_REQUEST || cacheType == CacheType.IF_NONE_CACHE_REQUEST;
    }

    public boolean isCacheExpired(){
        Log.i("chenxin",expireTime + "expireTime");
        Log.i("chenxin",saveTime + "saveTime");
        Log.i("chenxin",Calendar.getInstance().getTimeInMillis() + "currentTimeMillis");
        return expireTime + saveTime < Calendar.getInstance().getTimeInMillis();
    }

    public void generateCacheKey(@NonNull String url, HashMap<String,Object> paramsMap){
        if (cacheType != CacheType.NO_CACHE && TextUtils.isEmpty(cacheKey)) {
            cacheKey = HttpUtils.appendCacheKey(url,paramsMap);
        }
    }
}
