package com.yinguojiaoyu.netutils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.response.CommonResponseConvert;
import com.yinguojiaoyu.netlib.response.ResponseConvert;
import com.yinguojiaoyu.netutils.databinding.ActivityMainBinding;
import com.yinguojiaoyu.netutils.resource.ResourceDataMode;

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
//            startActivity(new Intent(MainActivity.this,WebViewActivity.class));
            Net.get("/app/api/app/v1/course/detail/4132")
                    .expireTime(60*1000)
                    .cacheMode(CacheType.NO_CACHE)
                    .execute(new CommonResponseConvert<BaseServiceMode<ResourceDataMode>>() {
                        @Override
                        public void onSuccess(BaseServiceMode<ResourceDataMode> resourceData) {
                            mainBinding.viewText.setText(resourceData.getData().getCoverUrl());
                        }

                        @Override
                        public void onFailed(Throwable throwable) {
                            Log.i("chenxin",throwable.toString());
                        }
                    });
        });
    }
}
