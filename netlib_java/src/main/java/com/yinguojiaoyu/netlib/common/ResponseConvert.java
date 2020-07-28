package com.yinguojiaoyu.netlib.common;

import com.google.gson.internal.$Gson$Types;
import com.yinguojiaoyu.netlib.GsonUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;

import io.reactivex.rxjava3.functions.Function;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class ResponseConvert<T> implements Function<Response,T> {
    private Type type;

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
        data = GsonUtils.getInstance().parseJson(body.charStream(),type);
        if (data == null) {
            throw new Exception("Parse json failed,data is null");
        }

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

    public void onPrepare(){}
    public void onSuccess(T t){}
    public void onFailed(Throwable throwable){}
}
