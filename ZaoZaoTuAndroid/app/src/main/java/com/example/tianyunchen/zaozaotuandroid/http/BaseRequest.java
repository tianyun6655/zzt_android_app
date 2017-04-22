package com.example.tianyunchen.zaozaotuandroid.http;

import android.util.Log;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by tianyunchen on 2/27/17.
 */

public class BaseRequest {
    /**
     *
     * @param url
     * @param requestParams
     * @return Re
     */
    public static Request createPostRequest(String url, RequestParams requestParams){
        FormBody.Builder mFormBodyBuilder = new FormBody.Builder();
        if(url!=null&requestParams!=null){
            for(Map.Entry<String,String> entry:requestParams.getUrlParams().entrySet()){
                mFormBodyBuilder.add(entry.getKey(),entry.getValue());

            }
        }
        FormBody formBody = mFormBodyBuilder.build();

        return new Request.Builder().url(url).post(formBody).build();

    }


    public  static  Request  createGetRequest(String url,RequestParams requestParams){

        StringBuilder urlBuilder=  new StringBuilder(url).append("?");
        if(requestParams!=null){
            for(Map.Entry<String,String> entry:requestParams.getUrlParams().entrySet()){
                   urlBuilder.append(entry.getKey())
                           .append("=")
                           .append(entry.getValue())
                           .append("&");
            }
        }
        Log.d("GETRequest",urlBuilder.toString());

        return new Request.Builder()
                 .url(urlBuilder.substring(0,urlBuilder.length()-1))
                .get().build();

    }
}
