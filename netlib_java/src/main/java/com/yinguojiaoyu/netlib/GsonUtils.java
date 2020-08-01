package com.yinguojiaoyu.netlib;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.lang.reflect.Type;

public class GsonUtils {

    private final Gson mGson;

    private GsonUtils() {
        mGson = new Gson();
    }

    public static GsonUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final GsonUtils INSTANCE = new GsonUtils();
    }

    public  Gson getGson(){
        return  mGson;
    }

    public <T> T parseJson(String json, Type type){
        return mGson.fromJson(json,type);
    }


}
