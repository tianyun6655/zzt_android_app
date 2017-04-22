package com.example.tianyunchen.zaozaotuandroid.dao;

import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.bean.Catogry;
import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tianyunchen on 3/8/17.
 */

public class PopularDao extends BaseDao {
    protected   String api="/earlyclass/popular";
    protected  int method  = GET;

    private ArrayList<HashMap<String,Object>> popularDatas;



    protected String getApi(){

        return api;
    }
    protected int getMethod(){
        return method;
    }

    public ArrayList<HashMap<String, Object>> getPopularDatas() {
        return popularDatas;
    }

    @Override
    protected void dealWithJson(JSONObject result) throws JSONException {
        Log.d("restule",result.toString());
        int code = result.getInt("code");
        if (code==200) {
            popularDatas = new ArrayList<>();
            JSONArray jsonArray = result.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                HashMap<String,Object> singleMaps = new HashMap<>();
                JSONObject signle = jsonArray.getJSONObject(i);
                //catogry
                Catogry catogry = new Catogry();
                catogry.setCaid(signle.getInt("caid"));
                catogry.setName(signle.getString("cname"));
                singleMaps.put("category",catogry);
                //Class
                JSONObject classes = signle.getJSONObject("class");
                JSONObject firstClass = classes.getJSONObject("first");
                EarlyClass first = encodeToClass(firstClass);
                singleMaps.put("first",first);
                //second
                if(classes.isNull("second")){
                 popularDatas.add(singleMaps);
                 continue;
                 }
                JSONObject secondClass = classes.getJSONObject("second");
                EarlyClass second = encodeToClass(secondClass);
                singleMaps.put("second",second);
                popularDatas.add(singleMaps);
            }

        }
    }

    private EarlyClass encodeToClass(JSONObject jsonObject) throws JSONException {
        EarlyClass earlyClass = new EarlyClass();
        earlyClass.setCid(jsonObject.getInt("cid"));
        earlyClass.setPre_image(jsonObject.getString("pre_image"));
        earlyClass.setName(jsonObject.getString("name"));
        return earlyClass;


    }
}
