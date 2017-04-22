package com.example.tianyunchen.zaozaotuandroid.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.adapter.ClassRecycleAdapter;
import com.example.tianyunchen.zaozaotuandroid.adapter.LoadMoreListener;
import com.example.tianyunchen.zaozaotuandroid.bean.Catogry;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.dao.GetCatogryAllDao;
import com.example.tianyunchen.zaozaotuandroid.dao.GetClassByCatogeryDao;
import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by tianyunchen on 3/9/17.
 */

public class CatogeryDetailActivity extends BaseActivity implements DaoListener,LoadMoreListener,TabLayout.OnTabSelectedListener{
    private TabLayout tableLayout;
    private GetCatogryAllDao getCatogryAllDao;
    private GetClassByCatogeryDao getClassByCatogeryDao;
    private RecyclerView recyclerView;
    private ClassRecycleAdapter classRecycleAdapter;
    private ArrayList<HashMap<String,Object>> datas;
    private int current_page =1;
    private int last_page;
    private int caid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catogery_class);
        caid = getIntent().getIntExtra("caid",-1);
        Log.d("catogrecaid",caid+"");
        initViews();
        initDatas();
    }

    @Override
    protected void initViews() {

        tableLayout = (TabLayout) findViewById(R.id.tab_layout);
        recyclerView = (RecyclerView)findViewById(R.id.class_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyItem());


    }

    private void initDatas(){
        getCatogryAllDao = new GetCatogryAllDao();
        getCatogryAllDao.setDaoLisenter(this);
        HashMap<String,String> maps = new HashMap<String,String>();
        getClassByCatogeryDao = new GetClassByCatogeryDao();
        getClassByCatogeryDao.setDaoLisenter(this);
        getCatogryAllDao.getData(maps);

    }

    @Override
    public void onSuccessful(BaseDao dao) {
        if (dao.equals(getCatogryAllDao)){
            for(int i =0;i<getCatogryAllDao.getCatogries().size();i++){
                TabLayout.Tab tab = tableLayout.newTab();
                tab.setTag(getCatogryAllDao.getCatogries().get(i));
                tableLayout.addTab(tab.setText(getCatogryAllDao.getCatogries().get(i).getName()));

            }
            if(getTabByCaid(caid)!=null)
            {
                tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                tableLayout.addOnTabSelectedListener(this);
                getTabByCaid(caid).select();
                HashMap<String,String> map = new HashMap();
                map.put("caid",caid+"");
                map.put("city","上海市");
                map.put("page",current_page+"");
                getClassByCatogeryDao.getData(map);
            }
        }else if (dao.equals(getClassByCatogeryDao)){
                datas = getClassByCatogeryDao.getDatas();
                last_page = getClassByCatogeryDao.getLast_page();
            if(classRecycleAdapter==null){
                classRecycleAdapter = new ClassRecycleAdapter(this,datas);
                classRecycleAdapter.setLoadMoreListener(this);
                recyclerView.setAdapter(classRecycleAdapter);
                if(last_page<=current_page){
                    classRecycleAdapter.removeFoot();
                }else {
                    classRecycleAdapter.addFootVjew();
                    current_page++;
                }

            }else{
                classRecycleAdapter.updateDatas(datas);
                if(last_page<=current_page){
                    classRecycleAdapter.removeFoot();
                }else {
                    classRecycleAdapter.addFootVjew();
                    current_page++;

                }
            }
        }

    }

    @Override
    public void onFaild(HttpException exception) {

    }

    private TabLayout.Tab getTabByCaid(int caid){
       int size =  tableLayout.getTabCount();

        for(int i=0;i<size;i++){
            Catogry temp = (Catogry)tableLayout.getTabAt(i).getTag();
            if(caid==temp.getCaid()){
                return tableLayout.getTabAt(i);
            }
        }
        return null;

    }

    @Override
    public void load() {
        Toast.makeText(this,"load",Toast.LENGTH_LONG).show();
        HashMap<String,String> map = new HashMap();
        map.put("caid",caid+"");
        map.put("city","上海市");
        map.put("page",current_page+"");
        getClassByCatogeryDao.getData(map);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        classRecycleAdapter = null;
        current_page=1;
        Catogry catogry =  (Catogry) tab.getTag();
        caid = catogry.getCaid();
        Log.d("listener",caid+"");
        HashMap<String,String> map = new HashMap();
        map.put("caid",caid+"");
        map.put("city","上海市");
        map.put("page",current_page+"");
        getClassByCatogeryDao.getData(map);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public  class MyItem extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if(parent.getChildPosition(view)==0){
                outRect.top=20;
            }
            outRect.bottom = 20;
        }
    }

}
