package com.example.tianyunchen.zaozaotuandroid.dao;

import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * Created by tianyunchen on 3/20/17.
 */

public class GetSingleClassDao extends BaseDao {
    private  String api ="/earlyclass";
    private  int method = GET;
    private HashMap<String,Object> data;
    @Override
    protected String getApi() {
        return api;
    }

    @Override
    protected int getMethod() {
        return method;
    }

    @Override
    protected void dealWithJson(JSONObject result) throws JSONException {
        JSONObject jsonObject= result.getJSONObject("early_class");
        data = new HashMap<String,Object>();

        //EarlyClassInfo
        JSONObject detail = jsonObject.getJSONObject("has_one_detail");
        EarlyClass earlyClass = new EarlyClass();
        earlyClass.setCid(jsonObject.getInt("cid"));
        earlyClass.setName(jsonObject.getString("name"));
        earlyClass.setPre_image(jsonObject.getString("pre_image"));
        earlyClass.setRating(jsonObject.getInt("rating"));
        earlyClass.setPrice(detail.getInt("price"));
        earlyClass.setTime(detail.getString("time"));
        earlyClass.setDescription(jsonObject.getString("description"));
        //SchoolInfo
        JSONObject schooljson = jsonObject.getJSONObject("has_one_school");
        School school = new School();
        school.setName(schooljson.getString("name"));
        school.setSid(schooljson.getInt("sid"));
        school.setDescription(schooljson.getString("description"));
        //
        data.put("school",school);
        data.put("class",earlyClass);






    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
