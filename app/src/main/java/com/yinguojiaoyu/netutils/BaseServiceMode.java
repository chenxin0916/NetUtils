package com.yinguojiaoyu.netutils;

public class BaseServiceMode<T> {
    private int errorCode;
    private boolean isSuccess;
    private String resultMsg;
    private T data;

    public T getData() {
        return data;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
