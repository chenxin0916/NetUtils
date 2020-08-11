package com.yinguojiaoyu.netlib.response;

import android.text.TextUtils;

import com.google.gson.internal.$Gson$Types;
import com.yinguojiaoyu.netlib.cache.CacheMode;
import com.yinguojiaoyu.netlib.cache.CacheOperate;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.Calendar;

import io.reactivex.rxjava3.functions.Function;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BaseResponseConvert<T> implements Function<Response,T> {

    protected Type type;
    protected CacheMode cacheMode = null;
    public BaseResponseConvert(Type type) {
        this.type = type;
    }
    public BaseResponseConvert() {
        this.type = getSuperclassTypeParameter(getClass());
    }

    @Override
    public T apply(Response response) throws Throwable {
        handleErrorCode(response.code());
        ResponseBody body = response.body();
        if (body == null)  throw new ConnectException();
        return handleResponseBody(body);
    }

    protected abstract void handleErrorCode(int code) throws Throwable;
    protected abstract T handleResponseBody(ResponseBody body) throws IOException, Exception;


    protected Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        assert parameterized != null;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    public void setCacheMode(CacheMode cachemode){
        this.cacheMode = cachemode;
    }

    protected void saveCache(String string) {
        if (!TextUtils.isEmpty(string) && cacheMode.isSaveCache() && !TextUtils.isEmpty(cacheMode.getCacheKey())) {
            cacheMode.setContent(string);
            cacheMode.setSaveTime(Calendar.getInstance().getTimeInMillis());
            if ( CacheOperate.getInstance().queryCache(cacheMode.getCacheKey()) != null) {
                CacheOperate.getInstance().updateCache(cacheMode);
            }else {
                CacheOperate.getInstance().addCache(cacheMode);
            }
        }
    }

    public void onPrepare(){}
    public void onSuccess(T t){}
    public void onFailed(Throwable throwable){}
}
