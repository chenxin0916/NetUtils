package com.yinguojiaoyu.netutils;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.common.ResponseConvert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Net.setBaseUrl("https://app.taohua6.com");
        Net.initCache(this);

        findViewById(R.id.view_text).setOnClickListener(v -> {

            Net.post("/app/api/app/v1/account/login")
                    .params("phone","15201426271")
                    .params("msgCode","9999")
                    .params("deviceType","app")
                    .cacheMode(CacheType.FIRST_CACHE_THEN_REQUEST)
                    .execute(new ResponseConvert<BaseServiceMode<LoginInfo>>() {
                        @Override
                        public void onSuccess(BaseServiceMode<LoginInfo> loginInfoBaseServiceMode) {
                            Log.i("chenxin",loginInfoBaseServiceMode.getData().getName());
                        }

                        @Override
                        public void onFailed(Throwable throwable) {
                            super.onFailed(throwable);
                            Log.i("chenxin","failed " + throwable.toString());
                        }
                    });
        });
    }
}
