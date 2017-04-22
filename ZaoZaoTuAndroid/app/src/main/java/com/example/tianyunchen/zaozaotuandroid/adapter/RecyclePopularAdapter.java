package com.example.tianyunchen.zaozaotuandroid.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.activity.CatogeryDetailActivity;
import com.example.tianyunchen.zaozaotuandroid.bean.Catogry;
import com.example.tianyunchen.zaozaotuandroid.bean.EarlyClass;
import com.example.tianyunchen.zaozaotuandroid.bean.School;
import com.example.tianyunchen.zaozaotuandroid.untils.ImageLoadUtil;
import com.example.tianyunchen.zaozaotuandroid.view.CatogeryItemView;
import com.example.tianyunchen.zaozaotuandroid.view.CatogeryLayout;
import com.example.tianyunchen.zaozaotuandroid.view.ImageBanrnnerView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tianyunchen on 3/6/17.
 */

public class RecyclePopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ImageLoadingListener {

    private Context context;
    private final int HEAD = 1;
    private final int NORMAL = 0;
    private final int CATOGERY_LAYOUT = 2;
    private final int TITLE_LAYOUT = 3;
    private LoadMoreListener listener;
    private ArrayList<HashMap<String,Object>> datas;
    private ArrayList<Catogry> catogries;
    private ItemCallback callback;
    private View beforeView;


    public void setCallback(ItemCallback callback) {
        this.callback = callback;
    }

    public RecyclePopularAdapter(Context context, ArrayList<HashMap<String,Object>> datas, ArrayList<Catogry> catogries) {
        this.context = context;
        this.datas = datas;
        this.catogries = catogries;
        ImageLoadUtil.getmInstance(context);

    }

    public void setListener(LoadMoreListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_recycle, null, false);

        if (viewType == HEAD) {
            return new HeadHolder(new ImageBanrnnerView(context));
        } else if (viewType == CATOGERY_LAYOUT)

        {
            CatogeryLayout catogeryLayout = new CatogeryLayout(context);
            for (int i = 0; i < 8; i++) {
                Log.d("testca", catogries.size() + "");
                final CatogeryItemView catogeryItemView = new CatogeryItemView(context);
                catogeryItemView.setData(catogries.get(i));
                catogeryItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CatogeryDetailActivity.class);
                        intent.putExtra("caid", catogeryItemView.getCaid());
                        context.startActivity(intent);
                    }
                });

                catogeryLayout.addView(catogeryItemView);
            }
            return new CatogeryHold(catogeryLayout);
        } else if (viewType == TITLE_LAYOUT) {
            return new TitleLayout(LayoutInflater.from(context).inflate(R.layout.item_recommend_title, null, false));


        } else {
            return new MyViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder mMyViewHolder = (MyViewHolder) holder;
            mMyViewHolder.mMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (beforeView==null){
//                        onPenAnimator(mMyViewHolder.mMore);
//                    }else if (beforeView==mMyViewHolder.mMore){
//                        beforeView = null;
//                        closeAnimator(mMyViewHolder.mMore);
//                        return;
//                    }else{
//                        closeAnimator(beforeView);
//                        onPenAnimator(mMyViewHolder.mMore);
//                    }
//                    beforeView = mMyViewHolder.mMore;
//
                }


            });
            School school = encodeToSchool(getRealPosition(holder));
            EarlyClass earlyClass = encodeToEarlyClass(getRealPosition(holder));
            mMyViewHolder.mTv_title.setText(earlyClass.getName());
            //  mMyViewHolder.mTv_number.setText(datas.get(getRealPosition(holder)).getNumber());
            mMyViewHolder.mTv_time.setText(school.getName());
            ImageLoadUtil.getmInstance(context).displayImage(mMyViewHolder.mImage_pre,earlyClass.getPre_image());
//                   ImageLoadUtil.getmInstance(context).displayImage(mMyViewHolder.mImagefirst
//                           , encodeToEarlyClass(getRealPosition(holder), "first").getPre_image(), null,this);

        }
    }

    @Override
    public int getItemCount() {

        return datas.size() + 2;

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        } else if (position == 1) {
            return CATOGERY_LAYOUT;
        } else if (position == 2) {
            return TITLE_LAYOUT;
        } else {
            return NORMAL;
        }
    }

    @Override
    public void onLoadingStarted(String imageUri, View view) {

    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        ImageLoadUtil.getmInstance(context).displayImage((ImageView) view, imageUri);

    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // ImageView mImagefirst,mImagesecond;
        TextView mTv_title, mTv_number, mTv_time;
        ImageView mImage_pre;
        RelativeLayout mMore;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mMore = (RelativeLayout) itemView;
            mTv_title = (TextView) itemView.findViewById(R.id.tv_title);
            mTv_number = (TextView) itemView.findViewById(R.id.tv_number);
            mTv_time = (TextView) itemView.findViewById(R.id.tv_time);
            mImage_pre = (ImageView) itemView.findViewById(R.id.img_preimage);
        }
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView) {
            super(itemView);
            ImageBanrnnerView imageBanrnnerView = (ImageBanrnnerView) itemView;
            int[] ids = {R.drawable.adviserment, R.drawable.zaojiao, R.drawable.adviserment};
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            for (int i = 0; i < 3; i++) {
                ImageView iv = new ImageView(context);
                iv.setImageResource(ids[i]);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageBanrnnerView.addView(iv);

            }
        }
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position - 3;
    }

    private School encodeToSchool(int position){
        return (School) datas.get(position).get("school");
    }

    private EarlyClass encodeToEarlyClass(int position){

        return (EarlyClass)datas.get(position).get("earlyclass");
    }

    private class CatogeryHold extends RecyclerView.ViewHolder {
        public CatogeryHold(View itemView) {
            super(itemView);
        }
    }

    private class TitleLayout extends RecyclerView.ViewHolder {

        public TitleLayout(View itemView) {
            super(itemView);
        }
    }

    public interface ItemCallback {
        public void change(View view);

        public void close();

    }
//
//    private void onPenAnimator(final View view) {
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f);
//        scaleY.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                callback.change(view);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        scaleY.setDuration(500);
//        scaleY.start();
//
//     }
//
//    private void closeAnimator(final View view){
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f);
//        scaleY.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                if (beforeView==null){
//                callback.close();
//                   }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//
//        scaleY.setDuration(500);
//        scaleY.start();
//
//    }
}


