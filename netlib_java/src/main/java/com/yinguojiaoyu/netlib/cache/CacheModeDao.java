package com.yinguojiaoyu.netlib.cache;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CacheModeDao {

    @Insert
    void insertCache(CacheMode cacheMode);

    @Delete
    void deleteCache(CacheMode cacheMode);

    @Update
    void updateCache(CacheMode cacheMode);

    @Query("SELECT * FROM cachemode WHERE cacheKey LIKE :cacheKey LIMIT 1")
    CacheMode queryByKey(String cacheKey);




}
