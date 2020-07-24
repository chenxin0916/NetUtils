package com.yinguojiaoyu.netlib;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder();
        builder.addHeader("Content-Type", "application/json;charset=UTF-8");
        // TODO: 2020/7/22
        return chain.proceed(builder.build());
    }
}
