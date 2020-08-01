package com.yinguojiaoyu.netlib.common;

import android.content.Context;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.yinguojiaoyu.netlib.cache.CacheOperate;
import com.yinguojiaoyu.netlib.request.GetRequest;
import com.yinguojiaoyu.netlib.request.PostRequest;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

import static android.util.Log.VERBOSE;

public class Net {

    public final OkHttpClient okHttpClient;
    private static String netBaseUrl = null;

    private static final class InstanceClass {
        private static Net instance = new Net();
    }

    private Net() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggingInterceptor.Builder()
                        .setLevel(Level.BASIC)
                        .log(VERBOSE)
                        .build())
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
    }

    public static void initCache(Context context) {
        CacheOperate.getInstance().initCacheDataBase(context);
    }

    public static Net getInstance(){
        return InstanceClass.instance;
    }

    public static String getNetBaseUrl() {
        return netBaseUrl;
    }

    public static void setBaseUrl(String baseUrl){
        if (!baseUrl.startsWith("http")) {
            throw new RuntimeException("base url must start with http or https");
        }
        netBaseUrl = baseUrl;
    }

    public static  GetRequest get(String url) {
        return new GetRequest(url);
    }

    public static PostRequest post(String url) {
        return new PostRequest(url);
    }

}
