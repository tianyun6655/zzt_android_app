package com.example.tianyunchen.zaozaotuandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianyunchen.zaozaotuandroid.activity.BaseActivity;

/**
 * Created by tianyunchen on 3/5/17.
 */

public abstract  class BaseFragment extends Fragment {
    protected Context mActivity;
    protected abstract void  initViews(View view);
    protected abstract int getContentId();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentId(),null,false);
        initViews(view);
        return view;
    }

    protected Context getHoldingActivity(){
        return  mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = context;
    }


}
