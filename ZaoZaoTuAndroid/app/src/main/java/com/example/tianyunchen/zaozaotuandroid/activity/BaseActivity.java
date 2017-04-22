package com.example.tianyunchen.zaozaotuandroid.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by tianyunchen on 2/28/17.
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected abstract void initViews();


    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }


    protected  void setTitle(String title){

    }
}
