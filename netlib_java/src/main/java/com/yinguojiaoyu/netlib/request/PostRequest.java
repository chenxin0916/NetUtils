package com.yinguojiaoyu.netlib.request;

import com.yinguojiaoyu.netlib.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostRequest extends BaseRequest {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
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
        if (requestPar == null && (paramsMap == null || paramsMap.isEmpty())) {
            return requestBuilder.url(requestUrl).post(okhttp3.internal.Util.EMPTY_REQUEST).build();
        }

        String jsonRequest = "";
        try {
            JSONObject json = new JSONObject();
            if (paramsMap != null && !paramsMap.isEmpty()) {
                Set<Map.Entry<String, Object>> entries = paramsMap.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    String urlKey = URLEncoder.encode(entry.getKey(), "UTF-8");
                    String urlValue = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
                    json.put(urlKey,urlValue);
                }
                jsonRequest = json.toString();
            }
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }

        if (requestPar != null) {
            if (requestPar instanceof JSONObject) {
                jsonRequest = requestPar.toString();
            }else if (requestPar instanceof String) {
                jsonRequest = (String) requestPar;
            }else {
                jsonRequest = GsonUtils.getInstance().getGson().toJson(requestPar);
            }
        }

        RequestBody body = RequestBody.create(jsonRequest,JSON);
       return requestBuilder.url(requestUrl).post(body).build();
    }
}
