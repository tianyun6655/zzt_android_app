package com.example.tianyunchen.zaozaotuandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.bean.Catogry;
import com.example.tianyunchen.zaozaotuandroid.untils.ImageLoadUtil;

/**
 * Created by tianyunchen on 3/9/17.
 */

public class CatogeryItemView extends LinearLayout {
    private Context context;
    private ImageView mIcon;
    private TextView mTitle;
    private Catogry catogry;
    private int caid;

    public int getCaid() {
        return catogry.getCaid();
    }



    public CatogeryItemView(Context context) {
        this(context,null);
    }

    public CatogeryItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CatogeryItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        init();
    }

    private void init(){
        LayoutInflater.from(context).inflate(R.layout.item_catogery_view, this, true);
        mIcon = (ImageView)findViewById(R.id.img_icon);
        mTitle = (TextView)findViewById(R.id.tv_label);
        ImageLoadUtil.getmInstance(context).displayImage(mIcon,"http://ac-bvlxhh4u.clouddn.com/f4ed2f73602ed49a14cf.png");


    }
    public void setData(Catogry catogry) {
        this.catogry = catogry;
        mTitle.setText(catogry.getName());

        ImageLoadUtil.getmInstance(context).displayImage(mIcon,catogry.getPre_image());
    }

    public Catogry getCatogry() {
        return catogry;
    }
}
