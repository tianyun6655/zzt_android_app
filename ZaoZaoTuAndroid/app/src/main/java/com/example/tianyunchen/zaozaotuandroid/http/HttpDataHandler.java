package com.example.tianyunchen.zaozaotuandroid.http;

import com.example.tianyunchen.zaozaotuandroid.http.listener.HttpListener;

/**
 * Created by tianyunchen on 2/27/17.
 */

public class HttpDataHandler {
    private HttpListener mListener = null;
    public Class<?> mClass=null;

    public HttpDataHandler(HttpListener mListener) {
        this.mListener = mListener;
    }

    public HttpDataHandler(HttpListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }

    public HttpListener getmListener() {
        return mListener;
    }

    public Class<?> getmClass() {
        return mClass;
    }
}
