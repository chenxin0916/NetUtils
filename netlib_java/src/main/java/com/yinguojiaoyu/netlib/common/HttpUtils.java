package com.yinguojiaoyu.netlib.common;

import com.yinguojiaoyu.netlib.request.PostRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpUtils {

    public static Response createCacheResponse(String content) throws IOException {
        Response.Builder builder = new Response.Builder();
        ResponseBody body = ResponseBody.create(content, PostRequest.JSON);
        builder.body(body);
        builder.code(200);
        builder.request(new Request.Builder().url("https://cache.com").build());
        builder.protocol(Protocol.get("http/1.1"));
        builder.message("cache");
        return builder.build();
    }

    public static String appendCacheKey(String url, HashMap<String, Object> params) {
        if (params == null) return url;
        StringBuilder cacheKey = new StringBuilder(url);
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            cacheKey.append(key).append(params.get(key));
        }
        return cacheKey.toString();
    }
}
