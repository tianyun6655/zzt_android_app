package com.example.tianyunchen.zaozaotuandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.fragment.MainFragment;
import com.example.tianyunchen.zaozaotuandroid.untils.ImageLoadUtil;

/**
 * Created by tianyunchen on 3/2/17.
 */

public class MenuActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout mine_layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageLoadUtil.getmInstance(this);
        initFragment();
    }

    @Override
    protected void initViews() {
        mine_layout = (RelativeLayout)findViewById(R.id.mine_layout_view);
        mine_layout.setOnClickListener(this);


    }
    private void initFragment(){
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,mainFragment).commit();
    }

    @Override
    public void onClick(View v) {
             int id = v.getId();
            if(id==mine_layout.getId()){

            }
    }
}
