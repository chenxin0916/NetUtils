package com.yinguojiaoyu.netlib.request;

import com.yinguojiaoyu.netlib.request.BaseRequest;

import okhttp3.Request;

public class GetRequest extends BaseRequest {

    public GetRequest(String url) {
        super(url);
    }

    public BaseRequest params(String key,String value){
        httpUrlBuilder.addQueryParameter(key,value);
        return this;
    }

    @Override
    public Request buildRequest() {
        return requestBuilder.get().url(httpUrlBuilder.build()).build();
    }

}
