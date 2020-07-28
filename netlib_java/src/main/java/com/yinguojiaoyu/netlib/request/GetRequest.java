package com.yinguojiaoyu.netlib.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;

public class GetRequest extends BaseRequest {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public Request buildRequest() {
        return requestBuilder.get().url(appendUrl()).build();
    }

    private String appendUrl(){
        if (requestUrl == null) {
            throw new RuntimeException("request is null");
        }

        if (paramsMap == null || paramsMap.isEmpty()) return requestUrl;

        Set<Map.Entry<String, Object>> entries = paramsMap.entrySet();
        if (entries.size() <= 0) return requestUrl;

        StringBuilder builder = new StringBuilder(requestUrl);
        if (requestUrl.indexOf('&') > 0 || requestUrl.indexOf('?') > 0) builder.append("&");
        else builder.append("?");

        try {

        for (Map.Entry<String, Object> entry : entries) {
            String urlKey = URLEncoder.encode(entry.getKey(), "UTF-8");
            String urlValue = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
            builder.append(urlKey).append("=").append(urlValue).append("&");
        }

            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
