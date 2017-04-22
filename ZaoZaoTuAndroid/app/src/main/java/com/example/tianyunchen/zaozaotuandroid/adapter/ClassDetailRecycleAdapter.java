package com.example.tianyunchen.zaozaotuandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.untils.MapUtils;

import java.util.HashMap;

/**
 * Created by tianyunchen on 3/14/17.
 */

public class ClassDetailRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private EarlyClass earlyClass;

    public ClassDetailRecycleAdapter(Context context, EarlyClass datas) {
        this.context = context;
        this.earlyClass = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_detail_recyle,null,false);
        return new ClassViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ClassViewHolder classViewHolder = (ClassViewHolder)holder;
        if(position==0){
            classViewHolder.mIcon.setImageResource(R.drawable.calendar);
            classViewHolder.mTitle.setText("上课时间:");
            classViewHolder.mContent.setText("周四，周六");

        }else if (position==1){
            classViewHolder.mIcon.setImageResource(R.drawable.money);
            classViewHolder.mTitle.setText("上课费用:");
            classViewHolder.mContent.setText(earlyClass.getPrice()+"");
        }else if (position==2){
            classViewHolder.mIcon.setImageResource(R.drawable.user);
            classViewHolder.mTitle.setText("上课人数:");
            classViewHolder.mContent.setText(earlyClass.getNumber()+"");
        }
        else if (position==3){
            classViewHolder.mIcon.setImageResource(R.drawable.bookshelf);
            classViewHolder.mTitle.setText("课程安排:");
            classViewHolder.mContent.setText("点击查看详情");
        }else if (position==4){
            classViewHolder.mIcon.setImageResource(R.drawable.pencil);
            classViewHolder.mTitle.setText("上课介绍:");
            classViewHolder.mContent.setText(earlyClass.getDescription());
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private  class  ClassViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIcon;
        private TextView mTitle;
        private TextView mContent;

        public ClassViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView)itemView.findViewById(R.id.img_icon);
            mTitle =(TextView)itemView.findViewById(R.id.tv_title);
            mContent =(TextView)itemView.findViewById(R.id.tv_content);

        }
    }
}
