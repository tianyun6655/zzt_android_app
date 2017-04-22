package com.example.tianyunchen.zaozaotuandroid.dao;

import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.http.BaseCallBack;
import com.example.tianyunchen.zaozaotuandroid.http.BaseHttpClient;
import com.example.tianyunchen.zaozaotuandroid.http.BaseRequest;
import com.example.tianyunchen.zaozaotuandroid.http.HttpConstant;
import com.example.tianyunchen.zaozaotuandroid.http.HttpDataHandler;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;
import com.example.tianyunchen.zaozaotuandroid.http.RequestParams;
import com.example.tianyunchen.zaozaotuandroid.http.listener.HttpListener;
import com.example.tianyunchen.zaozaotuandroid.untils.StringToMD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Request;

/**
 * Created by tianyunchen on 2/28/17.
 */

public abstract  class BaseDao {
    private static  final String TOKEN="123456";
    protected static final int GET = 1;
    protected static final  int POST =2;
    protected int method =GET;
    protected String api;
    protected DaoListener daoListener;

    protected abstract String getApi();
    protected abstract int getMethod();

    public  void setDaoLisenter(DaoListener daoLisenter){
        this.daoListener = daoLisenter;
    }
    public  void getData(HashMap<String,String> paramters){
       BaseHttpClient.sendRequest(getBaseRequest(paramters),new BaseCallBack(new HttpDataHandler(new HttpListener() {
           @Override
           public void onSuccess(Object responseObj) {
               JSONObject jsonObject = null;
               try {
                   jsonObject = new JSONObject((String)responseObj);
                   dealWithJson(jsonObject);
                   loadDataSuccessful();
               } catch (JSONException e) {
                   e.printStackTrace();
               }


           }

           @Override
           public void onFailure(HttpException exception) {
               loadDataFail(exception);
           }
       })));
    }


    private Request getBaseRequest(HashMap<String,String> paramters){
        RequestParams requestParams = new RequestParams(paramters);
        requestParams.putTimes();
        requestParams.putSignaure(getSign(paramters));
        if(getMethod()==GET){
           return BaseRequest.createGetRequest(HttpConstant.testbaseUrl+getApi(),requestParams);
        }else if(getMethod()==POST){
            return BaseRequest.createPostRequest(HttpConstant.testbaseUrl+getApi(),requestParams);
        }else{
            return null;
        }
    }

   protected  abstract  void dealWithJson(JSONObject result) throws JSONException;

    private  void loadDataSuccessful(){
        if(daoListener!=null){
            daoListener.onSuccessful(this);
        }
    }

    private void loadDataFail(HttpException exception){
        if (daoListener!=null){
            daoListener.onFaild(exception);
        }
    }


    private String getSign(HashMap<String,String> paramers){
        Object[] keys = paramers.keySet().toArray();
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.sort(keys);
        for  (Object key : keys) {
            stringBuilder.append(key)
                          .append("=")
                          .append(paramers.get(key))
                          .append("&");
        }
        stringBuilder.append("token=")
                .append(TOKEN);

        Log.d("sign: ",stringBuilder.toString());


              Log.d("MD5: ",StringToMD5.md5(stringBuilder.toString()));
        return StringToMD5.md5(stringBuilder.toString());

    }
}
