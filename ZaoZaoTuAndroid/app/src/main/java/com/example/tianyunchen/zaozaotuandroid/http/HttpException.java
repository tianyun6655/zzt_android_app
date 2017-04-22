package com.example.tianyunchen.zaozaotuandroid.http;

/**
 * Created by tianyunchen on 2/28/17.
 */

public class HttpException extends Exception {
    private int ecode;
    private String msg;


    public HttpException(int ecode, String msg) {
        this.ecode = ecode;
        this.msg = msg;
    }

    public int getEcode() {
        return ecode;
    }

    public String getMsg() {
        return msg;
    }
}
