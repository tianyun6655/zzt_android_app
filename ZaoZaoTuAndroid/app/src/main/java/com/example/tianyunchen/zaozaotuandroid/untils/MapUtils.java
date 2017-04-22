package com.example.tianyunchen.zaozaotuandroid.untils;

import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;

import java.util.HashMap;

/**
 * Created by tianyunchen on 3/20/17.
 */

public class MapUtils {


    public static School deCodeToSchool(HashMap<String,Object> map){
        School school = (School)map.get("school");
        return  school;
    }
    public static EarlyClass deCodeToClass(HashMap<String,Object> map){
        EarlyClass earlyClass = (EarlyClass) map.get("class");
        return  earlyClass;
    }
}
