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
        builder.addHeader("version", "2.8");
        builder.addHeader("token","Bearer eyJhbGciOiJIUzUxMiJ9.eyJwaG9uZSI6bnVsbCwib3BlbklkIjoibzJreWJ3bzV3YzRmWUZjRTNaUlVkOXR4am9fcyIsInNleCI6MCwibmFtZSI6Ik1hcmsiLCJoZWFkVXJsIjoiaHR0cDovL3RoaXJkd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL0xEVVFyYjdvTFRRVk5wVW1LeW1iYUFtVmlibXQwZlRYVnJaODZSRnlPdE5RcDZCWXRHZVhMaEZ3cHZpYWljT2s4RlJvMXE1TGgwdGcwNWd2bW43eXNWVUZ3LzEzMiIsInZpc2l0b3IiOmZhbHNlLCJleHAiOjE1OTc2MzI5NDcsInV1aWQiOiJjMDhiNDBkYTA5ZjQ0NGUxOTczOTQ1N2RlMGZmYjJlZCJ9.i47qqTLBAlAUmpAjLnhAbbXwTm6aEhUwpVy6OIxPZh13fVA9Mxeh8mRZON2rYBhVK2p5yHIX-XXxTrCBiV5oeQ");
        return chain.proceed(builder.build());
    }
}
