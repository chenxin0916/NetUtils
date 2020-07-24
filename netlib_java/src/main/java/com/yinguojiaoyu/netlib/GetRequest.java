package com.yinguojiaoyu.netlib;

import io.reactivex.rxjava3.functions.Consumer;

public class GetRequest<T> extends BaseRequest<T> {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public void buildRequest() {
        requestBuilder.get().url(httpUrlBuilder.build());
    }



}
