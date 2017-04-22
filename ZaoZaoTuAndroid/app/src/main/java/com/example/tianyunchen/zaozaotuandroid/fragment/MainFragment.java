package com.example.tianyunchen.zaozaotuandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.adapter.FragmentAdapter;

import java.util.ArrayList;

/**
 * Created by tianyunchen on 3/5/17.
 */

public class MainFragment extends BaseFragment {
   private ViewPager  mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,null);
    }

    @Override
    protected void initViews(View view) {
        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(initAdaper());
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_main;
    }


    private FragmentAdapter initAdaper(){
        ArrayList<BaseFragment> list=  new ArrayList<BaseFragment>();
        list.add(new EarlyLearningFragment());
        list.add(new EarlyCareFragment());
        FragmentAdapter fragmentPagerAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(),list);
        return fragmentPagerAdapter;
    }
}
