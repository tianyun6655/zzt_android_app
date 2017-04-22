package com.example.tianyunchen.zaozaotuandroid.dao;

import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tianyunchen on 3/2/17.
 */

public class RegisterDao extends BaseDao {

    protected String api = "/user/register";
    protected int  method = POST;
    private int status;
    protected int getMethod(){
        return method;
    }


    protected String getApi(){
            return api;
    }

    public int getStatus() {
        return status;
    }

    @Override
    protected void dealWithJson(JSONObject result) throws JSONException {
        String code =result.getString("code");
        if(code.equals("200")){
            status=1;
        }else {
            status=0;
        }
    }
}
