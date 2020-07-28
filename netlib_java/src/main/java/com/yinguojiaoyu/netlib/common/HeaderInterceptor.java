package com.yinguojiaoyu.netlib.common;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder();
        builder.addHeader("deviceType", "app");
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("version", "2.3");
        return chain.proceed(builder.build());
    }
}
