package com.example.tianyunchen.zaozaotuandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.activity.ClassDetailActivity;
import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tianyunchen on 3/13/17.
 */

public class ClassRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<HashMap<String,Object>> datas;
    private final  int FOOT =1;
    private final  int CLASS= 0;
    private   boolean isFoot = true;
    private LoadMoreListener loadMoreListener;

    public ClassRecycleAdapter(Context context,ArrayList<HashMap<String,Object>> datas){
        this.context = context;
        this.datas =datas;

    }


    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==CLASS){
            View view = LayoutInflater.from(context).inflate(R.layout.item_class_recycle,null,false);
            return new EarlyClassViewHold(view);
        }else {
            return new FootViewHold(LayoutInflater.from(context).inflate(R.layout.item_loading_foot,null,false));

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof EarlyClassViewHold){

            EarlyClassViewHold hold = (EarlyClassViewHold) holder;
            hold.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ClassDetailActivity.class);
                    intent.putExtra("cid",encodeToClass(position).getCid());
                    context.startActivity(intent);
                }
            });
            hold.mTv_title.setText(encodeToClass(position).getName());
            hold.mTv_school.setText(encodeToSchool(position).getName());
            hold.mTv_desc.setText(encodeToClass(position).getDescription());
          }else if(holder instanceof FootViewHold){

               loadMoreListener.load();
        }

    }

    @Override
    public int getItemCount() {
        if(isFoot){
            return datas.size()+1;

        } else{
            return datas.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFoot) {
            if (position == getItemCount() - 1) {
                return FOOT;
            } else {
                return CLASS;
            }
        } else {
            return CLASS;
        }

    }


    private class FootViewHold extends RecyclerView.ViewHolder{

        public FootViewHold(View itemView) {
            super(itemView);
        }
    }
    private class EarlyClassViewHold extends RecyclerView.ViewHolder{
        TextView mTv_title,mTv_school,mTv_desc;
        View itemView;

        public EarlyClassViewHold(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mTv_title = (TextView)itemView.findViewById(R.id.tv_title);
            mTv_school = (TextView)itemView.findViewById(R.id.tv_school);
            mTv_desc = (TextView)itemView.findViewById(R.id.tv_desc);

        }
    }





    private EarlyClass encodeToClass(int i){
        return (EarlyClass) datas.get(i).get("class");
    }

    private School  encodeToSchool(int i ){
        return (School)datas.get(i).get("school");
    }


    public void removeFoot(){
        isFoot = false;
       notifyDataSetChanged();
    }

    public  void addFootVjew(){
        isFoot = true;
        notifyDataSetChanged();
    }
    public void updateDatas(ArrayList<HashMap<String,Object>> datas){
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }

}
