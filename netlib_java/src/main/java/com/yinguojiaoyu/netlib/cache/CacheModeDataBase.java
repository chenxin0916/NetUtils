package com.yinguojiaoyu.netlib.cache;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CacheMode.class}, version = 1,exportSchema = false)
public abstract class CacheModeDataBase extends RoomDatabase {
    public abstract CacheModeDao cacheDao();

}
