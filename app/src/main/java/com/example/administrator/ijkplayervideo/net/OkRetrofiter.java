package com.example.administrator.ijkplayervideo.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkRetrofiter {

    private static OkHttpClient httpClient;
    private static RetrofiterAPI api;

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("myMessage", "" + message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        getHttpClient();
    }

    public static OkHttpClient getHttpClient() {
        if (httpClient == null) {

            synchronized (RetrofiterAPI.class) {
                if (httpClient == null) {
                    httpClient = new OkHttpClient();
                }
            }
        }
        return httpClient;
    }

    public static RetrofiterAPI getApi(String url) {
        if (api == null) {
            synchronized (OkHttpClient.class) {
                if (api == null) {
                    api = onCreate(RetrofiterAPI.class, url);
                }
            }
        }
        return api;
    }

    public static <T> T onCreate(Class<T> tClass, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(tClass);
    }
}
