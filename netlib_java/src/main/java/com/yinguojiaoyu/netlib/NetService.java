package com.yinguojiaoyu.netlib;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface NetService {

    @GET("")
    Observable<BaseResp<String>> login();

}
