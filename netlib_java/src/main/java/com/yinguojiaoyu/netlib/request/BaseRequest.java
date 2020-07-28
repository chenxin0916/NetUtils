package com.yinguojiaoyu.netlib.request;


import android.text.TextUtils;
import android.util.Log;

import com.yinguojiaoyu.netlib.cache.CacheMode;
import com.yinguojiaoyu.netlib.cache.CacheOperate;
import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.HttpUtils;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.common.ResponseConvert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Protocol;
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
        requestBuilder.tag(String.class,cacheKey);
        return this;
    }

    public void setTag(Object tag){
        requestBuilder.tag(tag);
    }

    //此方法会在rxjava subscribe 中执行可以做耗时操作
    public abstract Request buildRequest();

    @Override
    public void subscribe(@NonNull ObservableEmitter<Response> emitter) throws Throwable {

        if (mCacheType != CacheType.NO_CACHE) {
            if (TextUtils.isEmpty(cacheKey)) {
                String requestCacheKey = HttpUtils.appendCacheKey(requestUrl, paramsMap);
                this.cacheKey = requestCacheKey;
                requestBuilder.tag(String.class,requestCacheKey);
            }

            CacheMode cacheMode = CacheOperate.getInstance().queryCache(cacheKey);
            if (cacheMode != null) {
                String content = cacheMode.getContent();
                emitter.onNext(HttpUtils.createCacheResponse(content));
                if (mCacheType == CacheType.IF_NONE_CACHE_REQUEST) {
                    return;
                }
            }
        }

        Response execute = Net.getInstance().okHttpClient.newCall(buildRequest()).execute();
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
        responseConvert.setCacheMode(mCacheType);
        Observable.create(this)
                .map(responseConvert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseConvert::onSuccess, responseConvert::onFailed);
    }

}
