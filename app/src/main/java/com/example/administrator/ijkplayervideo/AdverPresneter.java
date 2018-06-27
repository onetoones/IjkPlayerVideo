package com.example.administrator.ijkplayervideo;

import android.util.Log;

import com.example.administrator.ijkplayervideo.net.OnNetListner;

public class AdverPresneter{

    private IZHotFragment activitys;
    private final IAdverModel model;

    public AdverPresneter(IZHotFragment activitys) {
        this.activitys = activitys;
        model = new AdverModel();
    }

    public void adver(){

        model.getAdver(new OnNetListner<AdvertisementBean>() {
            @Override
            public void onSuccess(AdvertisementBean bean) {
                activitys.gethot(bean);
            }

            @Override
            public void onFaile(Exception e) {

            }
        });

    }
//视频
    public void hots(String page, String source, String token, String appVersion){

        model.getVideo(page, source, token, appVersion, new OnNetListner<HotBean>() {
            @Override
            public void onSuccess(HotBean hotBean) {
                activitys.getVideos(hotBean);
            }

            @Override
            public void onFaile(Exception e) {

            }
        });

    }

}
