package com.yinguojiaoyu.netlib.cache;

import android.content.Context;

import androidx.room.Room;

public class CacheOperate {

    private CacheModeDataBase mCacheModeDataBase;

    private static final class InstanceClass {
        private static CacheOperate instance = new CacheOperate();
    }

    public static CacheOperate getInstance(){
        return CacheOperate.InstanceClass.instance;
    }

    private CacheOperate() {}

    public synchronized void initCacheDataBase(Context context){
        if (mCacheModeDataBase == null) {
            mCacheModeDataBase = Room.databaseBuilder(context,
                    CacheModeDataBase.class, "database-name").build();
        }
    }

    public void addCache(CacheMode cacheMode){
        if (mCacheModeDataBase == null){
            throw new RuntimeException("you should init dataBase first");
        }
        mCacheModeDataBase.cacheDao().insertCache(cacheMode);
    }

    public void deleteCache(CacheMode cacheMode){
        if (mCacheModeDataBase == null){
            throw new RuntimeException("you should init dataBase first");
        }
        mCacheModeDataBase.cacheDao().deleteCache(cacheMode);
    }

    public void updateCache(CacheMode cacheMode){
        if (mCacheModeDataBase == null){
            throw new RuntimeException("you should init dataBase first");
        }
        mCacheModeDataBase.cacheDao().updateCache(cacheMode);
    }

    public CacheMode queryCache(String cacheKey){
        if (mCacheModeDataBase == null){
            throw new RuntimeException("you should init dataBase first");
        }
        return mCacheModeDataBase.cacheDao().queryByKey(cacheKey);
    }
}
