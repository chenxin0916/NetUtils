package com.yinguojiaoyu.netlib.response;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class DownloadResponse extends BaseResponseConvert<String> {

    public DownloadResponse(String destFile) {

    }

    @Override
    protected void handleErrorCode(int code) throws Throwable {

    }

    @Override
    protected String handleResponseBody(ResponseBody body) throws IOException, Exception {
        InputStream inputStream = body.byteStream();
        return null;
    }
}
