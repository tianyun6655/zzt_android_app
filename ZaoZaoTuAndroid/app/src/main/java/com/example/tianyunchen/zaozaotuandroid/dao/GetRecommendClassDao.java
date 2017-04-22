package com.example.tianyunchen.zaozaotuandroid.dao;

import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tianyunchen on 3/12/17.
 */

public class GetRecommendClassDao extends BaseDao {
    private ArrayList<HashMap<String,Object>> classWithSchool;
    private final String api= "/earlyclass/recommend";
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
        Log.d("GetRecommendClassDao",result.toString());
        JSONArray jsonArray = result.getJSONArray("early_classes");
        classWithSchool = new ArrayList<HashMap<String, Object>>();
        for(int i =0;i<jsonArray.length();i++){
            HashMap<String,Object> signleMap = new HashMap<>();
            JSONObject eachObject = jsonArray.getJSONObject(i);
            //ToDo:获取class的信息
            EarlyClass earlyClass = new EarlyClass();
            earlyClass.setName(eachObject.getString("name"));
            earlyClass.setPre_image(eachObject.getString("pre_image"));
            earlyClass.setDescription(eachObject.getString("description"));
            //ToDo:获取对应School
            JSONObject schoolObject = eachObject.getJSONObject("has_one_school");
            School school = new School();
            school.setName(schoolObject.getString("name"));
            //ToDo:封装到Map
            signleMap.put("school",school);
            signleMap.put("earlyclass",earlyClass);
            classWithSchool.add(signleMap);
        }


    }

    public ArrayList<HashMap<String,Object>> getClasses() {
        return classWithSchool;
    }
}
