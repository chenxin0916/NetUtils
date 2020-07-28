package com.yinguojiaoyu.netlib.common;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.yinguojiaoyu.netlib.request.GetRequest;
import com.yinguojiaoyu.netlib.request.PostRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.util.Log.VERBOSE;

public class Net {

    public final OkHttpClient okHttpClient;
    private static URI netBaseUrl = null;

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
                .build();
    }

    public static Net getInstance(){
        return InstanceClass.instance ;
    }

    public static URI getNetBaseUrl() {
        return netBaseUrl;
    }

    public static void setBaseUrl(String baseUrl){
        if (!baseUrl.startsWith("http")) {
            throw new RuntimeException("base url must start with http or https");
        }

        try {
            netBaseUrl = new URI(baseUrl);
        } catch (URISyntaxException  e) {
            e.printStackTrace();
        }
    }

    public static  GetRequest get(String url) {
        return new GetRequest(url);
    }

    public static PostRequest post(String url) {
        return new PostRequest(url);
    }

}
