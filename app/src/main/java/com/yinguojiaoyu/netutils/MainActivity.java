package com.yinguojiaoyu.netutils;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.common.ResponseConvert;
import com.yinguojiaoyu.netutils.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        Net.getInstance().setBaseUrl("https://app.taohua6.com")
                .initCache(this)
                .setCommonCacheTime(20*1000)
                .setCommonCacheType(CacheType.NO_CACHE);
        mainBinding.viewText.setOnClickListener(v -> {
            Net.post("/app/api/app/v1/account/login")
                    .params("phone","15201426271")
                    .params("msgCode","9999")
                    .params("deviceType","app")
                    .expireTime(60*1000)
                    .cacheMode(CacheType.IF_NONE_CACHE_REQUEST)
                    .execute(new ResponseConvert<BaseServiceMode<LoginInfo>>() {
                        @Override
                        public void onSuccess(BaseServiceMode<LoginInfo> loginInfoBaseServiceMode) {
                            Log.i("chenxin",loginInfoBaseServiceMode.getData().getName());
                            mainBinding.viewText.setText(loginInfoBaseServiceMode.getData().getBirthday());
                        }
                    });
        });
    }
}
