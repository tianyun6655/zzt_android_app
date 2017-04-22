package com.example.tianyunchen.zaozaotuandroid.dao.listener;

import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;

/**
 * Created by tianyunchen on 2/28/17.
 */

public interface DaoListener {
    public  void onSuccessful(BaseDao dao);
    public  void onFaild(HttpException exception);
}
