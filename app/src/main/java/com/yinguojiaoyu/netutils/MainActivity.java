package com.yinguojiaoyu.netutils;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yinguojiaoyu.netlib.Net;
import com.yinguojiaoyu.netlib.NetCallBack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Net.setBaseUrl("https://app.taohua6.com");
        findViewById(R.id.view_text).setOnClickListener(v -> {

            Net.<String>get("app/api/app/account/login/sendMsgCode/18576601625")
                    .addHeader("device","app")
                    .params("phone","hahahahs")
                    .buildRequest(new NetCallBack<String>() {
                        @Override
                        public void onSuccess(String s) {

                        }

                        @Override
                        public void onFailed() {

                        }
                    });
        });



    }

}
