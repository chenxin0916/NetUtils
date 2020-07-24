package com.yinguojiaoyu.netlib;

import io.reactivex.rxjava3.functions.Function;
import okhttp3.Response;

class ResponseConvert<T> implements Function<Response,T> {
    @Override
    public T apply(Response response) throws Throwable {
        return null;
    }
}
