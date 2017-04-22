package com.example.tianyunchen.zaozaotuandroid.dao;

import android.content.Intent;
import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.untils.UserSharePreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by tianyunchen on 2/28/17.
 */

public class LoginDao extends BaseDao {

    protected String api = "/user/login";
    protected int  method = POST;
    private int status;
    private String token;

    public String getToken() {
        return token;
    }

    public int getStatus() {
        return status;
    }

    protected String getApi(){

            return api;
    }
    protected int getMethod(){
        return method;
    }

    @Override
    protected void dealWithJson(JSONObject result) throws JSONException {
        Log.d("result",result.toString());
        int code = Integer.parseInt(result.getString("code"));
        if(code==200){
            status=1;
            JSONObject data = result.getJSONObject("data");
            token =data.getString("token");
        }else{
            status=-1;
        }


    }
}
