package com.example.administrator.ijkplayervideo;


import com.example.administrator.ijkplayervideo.net.OnNetListner;

public interface IAdverModel {

    void getAdver(OnNetListner<AdvertisementBean> onNetListner);

    void getVideo(String page, String source, String token, String appVersion, OnNetListner<HotBean> onNetListner);
}
