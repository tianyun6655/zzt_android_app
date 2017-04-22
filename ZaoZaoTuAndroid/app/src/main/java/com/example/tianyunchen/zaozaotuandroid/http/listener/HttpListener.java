package com.example.tianyunchen.zaozaotuandroid.http.listener;

import com.example.tianyunchen.zaozaotuandroid.http.HttpException;

import org.json.JSONException;

/**
 * Created by tianyunchen on 2/27/17.
 */

public interface HttpListener {

    public void onSuccess(Object responseObj);
    public void onFailure(HttpException responseObj);
}
