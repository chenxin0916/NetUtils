package com.yinguojiaoyu.netlib.request;

import okhttp3.Request;

public class DownLoadRequest extends BaseRequest {
    public DownLoadRequest(String url) {
        super(url);
    }

    @Override
    public Request buildRequest() {
        return  requestBuilder.url(requestUrl)
                .addHeader("Connection", "close")
                .build();
    }
}
