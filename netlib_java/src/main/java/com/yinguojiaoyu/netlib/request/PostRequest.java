package com.yinguojiaoyu.netlib.request;

import android.util.Log;

import com.yinguojiaoyu.netlib.GsonUtils;
import com.yinguojiaoyu.netlib.Url;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostRequest extends BaseRequest {
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Object requestPar = null;
    public PostRequest(String url) {
        super(url);
    }

    public BaseRequest params(Object requestPar){
        this.requestPar = requestPar;
        return this;
    }

    @Override
    public Request buildRequest() {
        if (requestPar == null) {
            return requestBuilder.url(httpUrlBuilder.build().url()).post(okhttp3.internal.Util.EMPTY_REQUEST).build();
        }

        String jsonRequest = "";
        if (requestPar instanceof JSONObject) {
            jsonRequest = requestPar.toString();
        }else if (requestPar instanceof String) {
            jsonRequest = (String) requestPar;
        }else {
            jsonRequest = GsonUtils.getInstance().getGson().toJson(requestPar);
        }

        RequestBody body = RequestBody.create(jsonRequest,JSON);
       return requestBuilder.url(httpUrlBuilder.build().url()).post(body).build();
    }
}
