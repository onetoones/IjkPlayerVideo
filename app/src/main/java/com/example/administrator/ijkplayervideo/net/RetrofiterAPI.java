package com.example.administrator.ijkplayervideo.net;



import com.example.administrator.ijkplayervideo.AdvertisementBean;
import com.example.administrator.ijkplayervideo.HotBean;
import com.example.administrator.ijkplayervideo.MyBean;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofiterAPI {

    //广告
    @GET("quarter/getAd")
    Observable<AdvertisementBean> advert();


    //热门视频
    @POST("quarter/getHotVideos")
    @FormUrlEncoded
    Observable<HotBean> hotVideo(@Field("page") String page, @Field("source") String source, @Field("token") String token, @Field("appVersion") String appVersion);



}