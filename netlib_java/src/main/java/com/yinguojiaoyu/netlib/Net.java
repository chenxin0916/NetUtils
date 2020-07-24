package com.yinguojiaoyu.netlib;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.util.Log.VERBOSE;

public class Net {

    final OkHttpClient okHttpClient;
    static URI netBaseUrl = null;

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

    static Net getInstance(){
        return InstanceClass.instance ;
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

    public static <T> GetRequest<T> get(String url) {
        return new GetRequest<T>(url);
    }


}
