package com.example.tianyunchen.zaozaotuandroid.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tianyunchen on 3/12/17.
 */

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private int topSpace;
    private int bottomSpace;
    private View beforView;
    private View currentView;

    public void setBeforView(View beforView) {
        this.beforView = beforView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }

    public void setTopSpace(int topSpace) {
        this.topSpace = topSpace;
    }

    public void setBottomSpace(int bottomSpace) {
        this.bottomSpace = bottomSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildPosition(view)<=2){
            return;
        }
//        if(currentView==null&&beforView==null){
//            if(parent.getChildPosition(view)==2){
//                 outRect.top=topSpace;
                outRect.bottom = bottomSpace;
//
//
//            }else{
//                outRect.bottom = bottomSpace;
//            }
//        }else{
//            if(view==currentView){
//              outRect.top=currentView.getMeasuredHeight()/2;
//                outRect.bottom=currentView.getMeasuredHeight()/2;
//
//            }else{
//                outRect.bottom = bottomSpace;
//            }
       // }


    }
}
