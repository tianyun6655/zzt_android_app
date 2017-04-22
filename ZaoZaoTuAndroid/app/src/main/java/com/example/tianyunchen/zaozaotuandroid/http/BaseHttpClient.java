package com.example.tianyunchen.zaozaotuandroid.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by tianyunchen on 2/27/17.
 */

public class BaseHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder mOkhttpClientBuilder = new OkHttpClient.Builder();
        mOkhttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        mOkhttpClientBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        mOkhttpClientBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        mOkhttpClientBuilder.followRedirects(true);
        mOkHttpClient = mOkhttpClientBuilder.build();
    }



    public static Call sendRequest(Request request, BaseCallBack baseCallBack){
        Log.d("Client","call");
        Call call = mOkHttpClient.newCall(request);
        if(call==null){
            Log.d("client","call null");
        }
        call.enqueue(baseCallBack);
        return call;
    }



}
