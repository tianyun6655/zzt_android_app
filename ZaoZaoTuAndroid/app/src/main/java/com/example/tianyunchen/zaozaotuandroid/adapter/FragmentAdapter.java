package com.example.tianyunchen.zaozaotuandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tianyunchen.zaozaotuandroid.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by tianyunchen on 3/5/17.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
     private ArrayList<BaseFragment> list;
    public FragmentAdapter(FragmentManager fm,ArrayList<BaseFragment> list) {
        super(fm);
        this.list= list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
