package com.example.tianyunchen.zaozaotuandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.adapter.RecyclePopularAdapter;
import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.dao.GetCatogryAllDao;
import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.dao.GetRecommendClassDao;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;
import com.example.tianyunchen.zaozaotuandroid.view.ImageBanrnnerView;
import com.example.tianyunchen.zaozaotuandroid.view.SimpleItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by tianyunchen on 3/5/17.
 */

public class EarlyLearningFragment extends BaseFragment implements DaoListener,RecyclePopularAdapter.ItemCallback {
    private RecyclerView recyclerView;
    private GetRecommendClassDao getRecommendClassDao;
    private GetCatogryAllDao getCatogryAllDao;
    private ImageBanrnnerView imageBanrnnerView;
    private ArrayList<HashMap<String,Object>>  datas;
    private SimpleItemDecoration simpleItemDecoration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDao();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews(View view) {
        imageBanrnnerView = (ImageBanrnnerView)view.findViewById(R.id.imageBanner);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyle_popular);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        simpleItemDecoration = new SimpleItemDecoration();
        simpleItemDecoration.setTopSpace(10);
        simpleItemDecoration.setBottomSpace(20);
        recyclerView.addItemDecoration(simpleItemDecoration);
        recyclerView.invalidateItemDecorations();


    }

    private void initDao(){
        HashMap<String,String> maps = new HashMap<String,String>();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        maps.put("timestamps",sf.format(new Date()));
        maps.put("city","上海市");
        getRecommendClassDao = new GetRecommendClassDao();
        getRecommendClassDao.setDaoLisenter(this);
        getCatogryAllDao = new GetCatogryAllDao();
        getCatogryAllDao.setDaoLisenter(this);
        getRecommendClassDao.getData(maps);


    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_early_learning;
    }

    @Override
    public void onSuccessful(BaseDao dao) {
        if(dao.equals(getRecommendClassDao)) {
            if (getRecommendClassDao.getClasses() != null) {
                datas = getRecommendClassDao.getClasses();
                HashMap<String, String> maps = new HashMap<String, String>();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                maps.put("timestamps", sf.format(new Date()));
                getCatogryAllDao.getData(maps);
            }
        }else if(dao.equals(getCatogryAllDao)){
            if(getCatogryAllDao.getCatogries()!=null){
                RecyclePopularAdapter recyclePopularAdapter = new RecyclePopularAdapter(mActivity,datas,getCatogryAllDao.getCatogries());
                recyclePopularAdapter.setCallback(this);
                recyclerView.setAdapter(recyclePopularAdapter);
            }
        }
    }

    @Override
    public void onFaild(HttpException exception) {

    }

    @Override
    public void change(View view) {

        simpleItemDecoration.setCurrentView(view);
        recyclerView.invalidateItemDecorations();

    }

    @Override
    public void close() {
        simpleItemDecoration.setCurrentView(null);
        recyclerView.invalidateItemDecorations();

    }
}
