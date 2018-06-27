package com.example.administrator.ijkplayervideo.net;

public interface OnNetListner<T> {
    void onSuccess(T t);
    void onFaile(Exception e);
}
