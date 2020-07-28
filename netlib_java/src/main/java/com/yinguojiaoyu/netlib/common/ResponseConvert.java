package com.yinguojiaoyu.netlib.common;

import android.text.TextUtils;

import com.google.gson.internal.$Gson$Types;
import com.yinguojiaoyu.netlib.GsonUtils;
import com.yinguojiaoyu.netlib.cache.CacheMode;
import com.yinguojiaoyu.netlib.cache.CacheOperate;
import com.yinguojiaoyu.netlib.cache.CacheType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.Objects;

import io.reactivex.rxjava3.functions.Function;
import okhttp3.CacheControl;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class ResponseConvert<T> implements Function<Response,T> {
    private Type type;
    private CacheType cacheType = null;

    public ResponseConvert(Type type) {
        this.type = type;
    }

    public ResponseConvert() {
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
        T data;
        String string = body.string();
        data =  GsonUtils.getInstance().getGson().fromJson(string,type);

        if ( cacheType == CacheType.IF_NONE_CACHE_REQUEST || cacheType == CacheType.FIRST_CACHE_THEN_REQUEST) {
            String cacheKey = response.request().tag(String.class);
            if (!TextUtils.isEmpty(cacheKey)){
                CacheMode newCacheMode = new CacheMode();
                newCacheMode.setCacheKey(cacheKey);
                newCacheMode.setContent(string);
                newCacheMode.setSaveTime(System.currentTimeMillis());

                if ( CacheOperate.getInstance().queryCache(cacheKey) != null) {
                    CacheOperate.getInstance().updateCache(newCacheMode);
                }else {
                    CacheOperate.getInstance().addCache(newCacheMode);
                }
            }
        }

        if (data == null) {
            throw new Exception("Parse json failed,data is null");
        }
        string = null;
        return data;
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

    public void setCacheMode(CacheType cacheType){
        this.cacheType = cacheType;
    }
    public void onPrepare(){}
    public void onSuccess(T t){}
    public void onFailed(Throwable throwable){}
}
