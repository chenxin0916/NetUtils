package com.yinguojiaoyu.netlib;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseRequest<T> implements ObservableOnSubscribe<Response> {
    protected  Request.Builder requestBuilder;
    protected HttpUrl.Builder httpUrlBuilder;
    public BaseRequest(String url) {
        requestBuilder = new Request.Builder();
        httpUrlBuilder = new HttpUrl.Builder();
        if (Net.netBaseUrl == null) {
            throw new RuntimeException("Base url is null");
        }
        httpUrlBuilder.scheme(Net.netBaseUrl.getScheme());
        httpUrlBuilder.host(Net.netBaseUrl.getHost());

        String[] UrlSplit = url.split("/");
        for (String s : UrlSplit) {
            httpUrlBuilder.addPathSegment(s);
        }
    }

    public BaseRequest<T> host(String host){
        httpUrlBuilder.host(host);
        return this;
    }

    public BaseRequest<T> addHeader(String name,String value){
        requestBuilder.addHeader(name,value);
        return this;
    }

    public BaseRequest<T> params(String key,String value){
        httpUrlBuilder.addQueryParameter(key,value);
        return this;
    }

    public void setTag(Object tag){
        requestBuilder.tag(tag);
    }

    public abstract void buildRequest();

    @Override
    public void subscribe(@NonNull ObservableEmitter<Response> emitter) throws Throwable {
        Response execute = Net.getInstance().okHttpClient.newCall(requestBuilder.build()).execute();
        emitter.onNext(execute);
    }

    protected @NonNull Observable<T> getObservable(){
        return Observable.create(this)
                .map(new ResponseConvert<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void execute(NetCallBack<T> netCallBack){
       getObservable().subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) throws Throwable {

            }
        });
    }

}
