package com.yinguojiaoyu.netlib.request;


import android.text.TextUtils;

import com.yinguojiaoyu.netlib.cache.CacheMode;
import com.yinguojiaoyu.netlib.cache.CacheOperate;
import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.common.ResponseConvert;

import java.lang.reflect.Type;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BaseRequest implements ObservableOnSubscribe<Response> {
    protected  Request.Builder requestBuilder;
    protected String requestUrl;
    protected CacheType mCacheType = CacheType.NO_CACHE;
    protected String cacheKey = "";

    protected HashMap<String,Object> paramsMap;
    public BaseRequest(String url) {
        requestBuilder = new Request.Builder();
        if (Net.getNetBaseUrl() == null) {
            throw new RuntimeException("Base url is null");
        }

        requestUrl = Net.getNetBaseUrl().concat(url);
    }

    public BaseRequest addHeader(String name,String value){
        requestBuilder.addHeader(name,value);
        return this;
    }

    public synchronized void initParamMap(){
        if (paramsMap == null) {
            paramsMap = new HashMap<>();
        }
    }

    public BaseRequest params(String key,String value){
        initParamMap();
        paramsMap.put(key,value);
        return this;
    }

    public BaseRequest params(String key,int value){
        initParamMap();
        paramsMap.put(key,value);
        return this;
    }

    public BaseRequest params(String key,long value){
        initParamMap();
        paramsMap.put(key,value);
        return this;
    }

    public BaseRequest params(String key,boolean value){
        initParamMap();
        paramsMap.put(key,value);
        return this;
    }

    public BaseRequest params(String key,double value){
        initParamMap();
        paramsMap.put(key,value);
        return this;
    }

    public BaseRequest cacheMode(CacheType cacheType){
        this.mCacheType = cacheType;
        return this;
    }

    public BaseRequest cacheKey(String cacheKey){
        this.cacheKey = cacheKey;
        return this;
    }

    public void setTag(Object tag){
        requestBuilder.tag(tag);
    }

    //此方法会在rxjava subscribe 中执行可以做耗时操作
    public abstract Request buildRequest();

    @Override
    public void subscribe(@NonNull ObservableEmitter<Response> emitter) throws Throwable {
        if (TextUtils.isEmpty(cacheKey)) {
            cacheKey = requestUrl;
        }
        CacheMode cacheMode = CacheOperate.getInstance().queryCache(cacheKey);
        if (cacheMode != null) {
            String content = cacheMode.getContent();
            Response.Builder builder = new Response.Builder();
            builder.body(ResponseBody.create(content,PostRequest.JSON));
            builder.code(200);
            emitter.onNext(builder.build());
            return;
        }

        Response execute = Net.getInstance().okHttpClient.newCall(buildRequest()).execute();

        if (execute.code() == 200 && execute.body() != null) {
            CacheMode hasMode = CacheOperate.getInstance().queryCache(cacheKey);
            if (hasMode != null) {
                CacheOperate.getInstance().updateCache(hasMode);
            }else {
                CacheMode newCacheMode = new CacheMode();
                newCacheMode.setCacheKey(cacheKey);
                newCacheMode.setContent(execute.body().string());
                newCacheMode.setSaveTime(System.currentTimeMillis());
                CacheOperate.getInstance().addCache(newCacheMode);
            }
        }

        emitter.onNext(execute);
    }

    public @NonNull Observable<Response> getObservable(){
        return Observable.create(this);
    }

    public @NonNull <T> Observable<T> getObservable(Type type){
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
