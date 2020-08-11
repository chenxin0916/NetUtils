package com.yinguojiaoyu.netutils;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;

import com.yinguojiaoyu.netlib.cache.CacheType;
import com.yinguojiaoyu.netlib.common.Net;
import com.yinguojiaoyu.netlib.response.CommonResponseConvert;
import com.yinguojiaoyu.netlib.response.ResponseConvert;
import com.yinguojiaoyu.netutils.databinding.ActivityWebViewBinding;
import com.yinguojiaoyu.netutils.resource.Catalogues;
import com.yinguojiaoyu.netutils.resource.ResourceDataMode;

public class WebViewActivity extends AppCompatActivity {

    private ActivityWebViewBinding mWebViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebViewBinding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(mWebViewBinding.getRoot());
        init();
        Net.get("/app/api/app/v1/course/detail/2517")
                .expireTime(60*1000)
                .cacheMode(CacheType.IF_NONE_CACHE_REQUEST)
                .execute(new ResponseConvert<BaseServiceMode<ResourceDataMode>>() {
                    @Override
                    public void onSuccess(BaseServiceMode<ResourceDataMode> resourceData) {
                        Log.i("chenxin",resourceData.getData().getCoverUrl());
                        Catalogues catalogues = resourceData.getData().getCatalogues().get(0);
                        loadData(catalogues.getInfo(),"");
                    }
                });
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        mWebViewBinding.mainWebView.getSettings().setJavaScriptEnabled(true);
        mWebViewBinding.mainWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebViewBinding.mainWebView.getSettings().setUseWideViewPort(true);
        mWebViewBinding.mainWebView.getSettings().setDomStorageEnabled(true);
        mWebViewBinding.mainWebView.getSettings().setDatabaseEnabled(true);
        mWebViewBinding.mainWebView.getSettings().setLoadWithOverviewMode(true);
        mWebViewBinding.mainWebView.getSettings().setSupportZoom(true);
        mWebViewBinding.mainWebView.getSettings().setAppCacheEnabled(true);
        mWebViewBinding.mainWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebViewBinding.mainWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        mWebViewBinding.mainWebView.getSettings().setTextZoom(75);
        mWebViewBinding.mainWebView.setVerticalScrollBarEnabled(false);
        mWebViewBinding.mainWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
        mWebViewBinding.mainWebView.setHorizontalScrollBarEnabled(false);
    }

    public void loadData(String data,String extraScript){
        String css = "<style type=\"text/css\"> </style><style>img{max-width:100% !important;height:auto !important; margin-bottom:10px}</style><style>body{max-width:100% !important; line-height:170% !important; letter-spacing:1.5px !important;}</style><style>p{margin:0 !important;padding:0 !important;line-height:170% !important; text-indent: 0em !important;letter-spacing:1.5px !important;}</style><style>span{line-height:170% !important;letter-spacing:1.5px !important;}</style><style>strong{line-height:170% !important;letter-spacing:1.5px !important;}</style>";
        String html = "<html><header><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui>"+css+"</header>"+"<body style='margin:0;padding:0'>"+ extraScript + data+"</body>"+"</html>";
        mWebViewBinding.mainWebView.loadDataWithBaseURL(null,html,"text/html", "utf-8",null);
    }
}