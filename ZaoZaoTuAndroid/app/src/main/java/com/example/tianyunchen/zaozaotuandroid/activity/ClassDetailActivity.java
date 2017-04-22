package com.example.tianyunchen.zaozaotuandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.adapter.ClassDetailRecycleAdapter;
import com.example.tianyunchen.zaozaotuandroid.bean.School;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.dao.GetSingleClassDao;
import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;
import com.example.tianyunchen.zaozaotuandroid.untils.MapUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by tianyunchen on 3/14/17.
 */

public class ClassDetailActivity extends BaseActivity implements DaoListener,OnClickListener{
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private GetSingleClassDao getSingleClassDao;
    private TextView classTitle,schoolTitle;
    private HashMap<String,Object> data;

    private RelativeLayout schoolRelative;
    private int cid;
    private int sid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        cid = getIntent().getIntExtra("cid",-1);
        Log.d("classDetailActivity",cid+"");
        initViews();
        initDao();
    }

    @Override
    protected void initViews() {
        schoolRelative = (RelativeLayout)findViewById(R.id.layout_school);
        schoolRelative.setOnClickListener(this);
        classTitle = (TextView)findViewById(R.id.tv_class);
        schoolTitle = (TextView)findViewById(R.id.tv_school);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText("详情");
        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("评论");
        tabLayout.addTab(tab);
        tabLayout.addTab(tab1);
        recyclerView = (RecyclerView)findViewById(R.id.class_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void initDao(){
        HashMap<String,String> paramers = new HashMap<String,String>();
        paramers.put("cid",cid+"");
        getSingleClassDao = new GetSingleClassDao();
        getSingleClassDao.setDaoLisenter(this);
        getSingleClassDao.getData(paramers);


    }

    @Override
    public void onSuccessful(BaseDao dao) {
        data = getSingleClassDao.getData();
        School school = MapUtils.deCodeToSchool(data);
        sid = school.getSid();

        recyclerView.setAdapter(new ClassDetailRecycleAdapter(this, MapUtils.deCodeToClass(data)));
        schoolTitle.setText(school.getName());
        classTitle.setText(MapUtils.deCodeToClass(data).getName());


    }

    @Override
    public void onFaild(HttpException exception) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,SchoolDetailAcitivty.class);
        intent.putExtra("sid",sid);
        startActivity(intent);

    }
}
