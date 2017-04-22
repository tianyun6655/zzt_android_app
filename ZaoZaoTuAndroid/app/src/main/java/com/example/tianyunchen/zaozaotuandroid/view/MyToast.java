package com.example.tianyunchen.zaozaotuandroid.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianyunchen.zaozaotuandroid.R;

/**
 * Created by tianyunchen on 3/4/17.
 */

public class MyToast {
    private static TextView mTitle,mContent;
    private static ImageView mImageView;


    public static void showToast(Context context, String title,String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
        //初始化布局控件
        mTitle = (TextView) toastRoot.findViewById(R.id.title);
        mContent = (TextView) toastRoot.findViewById(R.id.content);
        mTitle.setText(title);
        mContent.setText(message);

//        //Toast的初始化
        Toast toastStart = new Toast(context);
        //获取屏幕高度
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setDuration(Toast.LENGTH_LONG);
        toastStart.setGravity(Gravity.BOTTOM, 0, 20);

        toastStart.setView(toastRoot);
        toastStart.show();
    }
}
