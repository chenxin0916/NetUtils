package com.yinguojiaoyu.netlib.request;


import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.common.ResponseConvert;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseRequest implements ObservableOnSubscribe<Response> {
    protected  Request.Builder requestBuilder;
    protected HttpUrl.Builder httpUrlBuilder;

    public BaseRequest(String url) {
        requestBuilder = new Request.Builder();
        httpUrlBuilder = new HttpUrl.Builder();
        if (Net.getNetBaseUrl() == null) {
            throw new RuntimeException("Base url is null");
        }
        httpUrlBuilder.scheme(Net.getNetBaseUrl().getScheme());
        httpUrlBuilder.host(Net.getNetBaseUrl().getHost());

        String[] UrlSplit = url.split("/");
        for (String s : UrlSplit) {
            httpUrlBuilder.addPathSegment(s);
        }
    }

    public BaseRequest host(String host){
        httpUrlBuilder.host(host);
        return this;
    }

    public BaseRequest addHeader(String name,String value){
        requestBuilder.addHeader(name,value);
        return this;
    }

    public void setTag(Object tag){
        requestBuilder.tag(tag);
    }

    //此方法会在rxjava subscribe 中执行可以做耗时操作
    public abstract Request buildRequest();

    @Override
    public void subscribe(@NonNull ObservableEmitter<Response> emitter) throws Throwable {
        Response execute = Net.getInstance().okHttpClient.newCall(buildRequest()).execute();
        emitter.onNext(execute);
    }

    protected @NonNull Observable<Response> getObservable(){
        return Observable.create(this);
    }

    protected @NonNull <T> Observable<T> getObservable(Type type){
        return Observable.create(this)
                .map(new ResponseConvert<T>(type) {});
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public <T>void execute(ResponseConvert<T> responseConvert){
        responseConvert.onPrepare();
        Observable.create(this)
                .map(responseConvert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseConvert::onSuccess, responseConvert::onFailed);
    }
}
