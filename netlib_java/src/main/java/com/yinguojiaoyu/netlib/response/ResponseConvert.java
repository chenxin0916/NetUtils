package com.yinguojiaoyu.netlib.response;

import com.yinguojiaoyu.netlib.GsonUtils;

import okhttp3.ResponseBody;

public class ResponseConvert<T> extends BaseResponseConvert<T> {

    @Override
    protected void handleErrorCode(int code) throws Throwable {
        if (code != 200) {
            throw new Throwable("Serve Error" +code);
        }
    }

    @Override
    protected T handleResponseBody(ResponseBody body) throws Exception {
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
}
