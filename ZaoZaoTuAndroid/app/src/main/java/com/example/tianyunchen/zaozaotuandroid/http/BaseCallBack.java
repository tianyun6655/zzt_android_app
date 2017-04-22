package com.example.tianyunchen.zaozaotuandroid.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.http.listener.HttpListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by tianyunchen on 2/27/17.
 */

public class BaseCallBack implements Callback {

    protected  final String RESTULT_COLD="code";
    protected  final String Errir_MSG = "msg";


    protected final int NETWORK_ERROR =-1;
    protected final  int JSON_ERROR = -2;
    protected  final  int OTHER_ERROR = -3;
    protected final int SIGN_ERROR = -4;



    private Handler mHandle;
    private HttpListener httpListener;
    private Class<?> mClass;

    public BaseCallBack(HttpDataHandler httpDataHandler) {
        this.httpListener = httpDataHandler.getmListener();
        this.mClass = httpDataHandler.getmClass();
        this.mHandle = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {

        mHandle .post(new Runnable() {
            @Override
            public void run() {
                httpListener.onFailure(new HttpException(NETWORK_ERROR,"网络错误"));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        final int  code = response.code();
        Log.d("code:", code+"");
        mHandle.post(new Runnable() {
            @Override
            public void run() {
                if (code==100){
                    httpListener.onFailure(new HttpException(SIGN_ERROR,"签名错误"));
                }else{
                handResponse(result);
                }
            }
        });


    }


    private  void handResponse(String result){
        if (result==null){
            httpListener.onFailure(new HttpException(JSON_ERROR,"数据获取错误"));
            return;
        }

        httpListener.onSuccess(result);
    }
}


