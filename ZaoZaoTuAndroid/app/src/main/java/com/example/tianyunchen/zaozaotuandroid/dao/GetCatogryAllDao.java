package com.example.tianyunchen.zaozaotuandroid.dao;

import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.bean.Catogry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tianyunchen on 3/9/17.
 */

public class GetCatogryAllDao extends BaseDao {


   private String api = "/catogery/all";
   private ArrayList<Catogry>  catogries;

    public ArrayList<Catogry> getCatogries() {
        return catogries;
    }

    @Override
    protected String getApi() {
        return api;
    }

    @Override
    protected int getMethod() {
        return GET;
    }

    @Override
    protected void dealWithJson(JSONObject result) throws JSONException {
       int status = result.getInt("code");
        Log.d("testtest",result.toString());
        if(status==200){
            catogries = new ArrayList<>();
            JSONArray jsonArray = result.getJSONArray("data");
            for(int i =0;i<jsonArray.length();i++){
                JSONObject signle = jsonArray.getJSONObject(i);
                Catogry catogry = new Catogry();
                catogry.setCaid(signle.getInt("caid"));
                catogry.setName(signle.getString("name"));
                catogry.setDesc(signle.getString("desc"));
                catogry.setPre_image(signle.getString("pre_image"));
                catogries.add(catogry);
            }
        }
    }
}
