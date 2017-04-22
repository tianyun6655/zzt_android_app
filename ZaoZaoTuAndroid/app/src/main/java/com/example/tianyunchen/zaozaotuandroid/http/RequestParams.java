package com.example.tianyunchen.zaozaotuandroid.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianyunchen on 2/27/17.
 *
 */

public class RequestParams {

    private HashMap<String,String> urlParams  = new HashMap<String,String>();

    public RequestParams(Map<String,String> params){
        if (params!=null){
            for(Map.Entry<String,String> entry:params.entrySet()){
                put(entry.getKey(),entry.getValue());
            }
        }
    }

    private    void put(String key,String values){
        if(key!=null&&values!=null){
            urlParams.put(key,values);
        }
    }

    public  void  putTimes(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        urlParams.put("timestamps",sf.format(new Date()));
    }

    public void putSignaure(String value){
        if(value!=null){
            urlParams.put("sign",value);
        }
    }

    public HashMap<String, String> getUrlParams() {
        return urlParams;
    }
}
