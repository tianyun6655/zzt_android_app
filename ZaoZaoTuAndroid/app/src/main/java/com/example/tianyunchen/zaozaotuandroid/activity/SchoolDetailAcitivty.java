package com.example.tianyunchen.zaozaotuandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;

/**
 * Created by tianyunchen on 4/5/17.
 */

public class SchoolDetailAcitivty extends BaseActivity implements DaoListener {
    private String TAG = "SchoolDetailActivity";

    private int sid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
        sid = getIntent().getIntExtra("sid",-1);
        Log.d(TAG,sid+"");

    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onSuccessful(BaseDao dao) {

    }

    @Override
    public void onFaild(HttpException exception) {

    }
}
