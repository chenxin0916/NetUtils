package com.yinguojiaoyu.netlib;

public interface NetCallBack<T> {
    void onSuccess(T t);
    void onFailed();
}
