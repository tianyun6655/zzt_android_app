package com.example.tianyunchen.zaozaotuandroid.dao;

import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by tianyunchen on 3/13/17.
 */

public class GetClassByCatogeryDao extends BaseDao {
    private final  String  api="/earlyclass/category";
    private ArrayList<HashMap<String,Object>> datas;
    private int last_page =0;

    @Override
    protected String getApi() {
        return api;
    }

    public ArrayList<HashMap<String, Object>> getDatas() {
        return datas;
    }

    public int getLast_page() {
        return last_page;
    }

    @Override
    protected int getMethod() {
        return GET;
    }

    @Override
    protected void dealWithJson(JSONObject result) throws JSONException {
        JSONArray jsonArray = result.getJSONArray("data");
        last_page = result.getInt("last_page");
        datas = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<jsonArray.length();i++){
            HashMap<String,Object> eachMap = new HashMap<String, Object>();
            JSONObject signle = jsonArray.getJSONObject(i);
            //ToDo:获取单个class信息
            EarlyClass earlyClass = new EarlyClass();
            earlyClass.setCid(signle.getInt("cid"));
            earlyClass.setName(signle.getString("name"));
            earlyClass.setDescription(signle.getString("description"));
            earlyClass.setPre_image(signle.getString("pre_image"));
            earlyClass.setRating(signle.getInt("rating"));
            //ToDo:获取学校信息
            School school = new School();
            JSONObject schoolObjec = signle.getJSONObject("has_one_school");
            school.setName(schoolObjec.getString("name"));
            //ToDo:封装一个map
            eachMap.put("school",school);
            eachMap.put("class",earlyClass);

            datas.add(eachMap);
        }
        Log.d("GetClassCaid",datas.size()+"");


    }
}
