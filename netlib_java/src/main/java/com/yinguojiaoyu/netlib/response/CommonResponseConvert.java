package com.yinguojiaoyu.netlib.response;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.internal.$Gson$Types;
import com.yinguojiaoyu.netlib.GsonUtils;
import com.yinguojiaoyu.netlib.cache.CacheMode;
import com.yinguojiaoyu.netlib.cache.CacheOperate;
import com.yinguojiaoyu.netlib.cache.CacheType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.Calendar;

import io.reactivex.rxjava3.functions.Function;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class CommonResponseConvert<T> implements Function<Response,T> {
    private Type type;
    private CacheMode cacheMode = null;

    public CommonResponseConvert(Type type) {
        this.type = type;
    }

    public CommonResponseConvert() {
        this.type = getSuperclassTypeParameter(getClass());
    }

    @Override
    public T apply(Response response) throws Throwable {
        if (response.code() != 200) {
            throw new Throwable("Serve Error" + response.code());
        }

        ResponseBody body = response.body();
        if (body == null) {
            throw new ConnectException();
        }

        String string = body.string();
        T data =  GsonUtils.getInstance().parseJson(string,type);
        saveCache(string);
        if (data == null) {
            throw new Exception("Parse json failed,data is null");
        }
        string = null;
        cacheMode = null;
        return data;
    }

    private void saveCache(String string) {
        if ( cacheMode.isSaveCache() && !TextUtils.isEmpty(cacheMode.getCacheKey())) {
            cacheMode.setContent(string);
            cacheMode.setSaveTime(Calendar.getInstance().getTimeInMillis());
            if ( CacheOperate.getInstance().queryCache(cacheMode.getCacheKey()) != null) {
                CacheOperate.getInstance().updateCache(cacheMode);
            }else {
                CacheOperate.getInstance().addCache(cacheMode);
            }
        }
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
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
    public void onPrepare(){}
    public void onSuccess(T t){}
    public void onFailed(Throwable throwable){}
}
