package com.yinguojiaoyu.netutils;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.response.BaseResponseConvert;
import com.yinguojiaoyu.netlib.response.CommonResponseConvert;
import com.yinguojiaoyu.netutils.databinding.ActivityMainBinding;
import com.yinguojiaoyu.netutils.resource.ResourceDataMode;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        Net.getInstance().setBaseUrl("https://app.taohua6.com")
                .initCache(this)
                .setCommonCacheTime(20 * 1000)
                .setCommonCacheType(CacheType.NO_CACHE);
        mainBinding.viewText.setOnClickListener(v -> {
//        startActivity(new Intent(MainActivity.this,WebViewActivity.class));
        });

        Net.get("/app/api/app/v1/course/detail/4132")
                .expireTime(60 * 1000)
                .cacheMode(CacheType.NO_CACHE)
                .execute(new BaseResponseConvert<BaseResponseConvert<BaseServiceMode<LoginInfo>>>() {

                    @Override
                    protected void handleErrorCode(int code) throws Throwable {

                    }

                    @Override
                    protected BaseResponseConvert<BaseServiceMode<LoginInfo>> handleResponseBody(ResponseBody body) throws IOException, Exception {
                        return null;
                    }
                });

    }
}
