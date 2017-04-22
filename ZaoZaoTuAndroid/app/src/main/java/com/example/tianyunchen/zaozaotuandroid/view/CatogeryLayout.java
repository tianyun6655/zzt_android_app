package com.example.tianyunchen.zaozaotuandroid.view;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by tianyunchen on 3/9/17.
 */

public class CatogeryLayout extends ViewGroup {
    private Context context;
    private int childCount;
    private int screen_width;
    private int layout_height;
    private int child_height;
    private int child_width;
    public CatogeryLayout(Context context) {
        this(context,null);
    }

    public CatogeryLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CatogeryLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        init();
    }
    private void init(){
        DisplayMetrics dm = new DisplayMetrics();
        this.setBackgroundColor(Color.WHITE);
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_width= dm.widthPixels;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount =getChildCount();
        if(childCount==0){
            setMeasuredDimension(0,0);
        }else{
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            View view = getChildAt(0);
            Log.d("childTest",getChildCount()+"");
            layout_height = view.getMeasuredHeight()*2+75;
            Log.d("childTest",layout_height+"");
            child_height = view.getMeasuredHeight();
            child_width = view.getMeasuredWidth();
            setMeasuredDimension(screen_width,layout_height);
        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int half_count  = childCount/2;
        int each_size = 0;
        int topMargin =25;
        int leftMargin=0;
        if(half_count%2==0){
            each_size = screen_width/half_count;
            leftMargin = (each_size-child_width)/2;
        }else{
            each_size = screen_width/half_count+1;
        }
        for(int i=0;i<half_count;i++){
            View view = getChildAt(i);
            view.layout(leftMargin+each_size*i,topMargin,leftMargin+each_size*i+child_width,child_height+topMargin);
        }
        topMargin +=child_height+topMargin;
        for(int i=half_count;i<childCount;i++){
            View view = getChildAt(i);
            view.layout(leftMargin+each_size*(i-half_count),topMargin,leftMargin+each_size*(i-half_count)+child_width,child_height+topMargin);
        }
    }
}
