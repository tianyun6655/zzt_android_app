package com.example.tianyunchen.zaozaotuandroid.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tianyunchen on 3/6/17.
 */

public class ImageBanrnnerView extends ViewGroup {
    private int childCount;
    private int width;
    private int height;

    private int x;
    private int index =0;
    private boolean isAuto = true;
    private Timer timer = new Timer();
    private TimerTask task;
    private Handler autoHandle = new  Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0:
                    if(index==childCount){
                        index=0;
                    }
                    translate(-(index*width));
                    index++;
                    break;
            }
        }
    };
    public ImageBanrnnerView(Context context) {
        this(context,null);
    }

    public ImageBanrnnerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageBanrnnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
  private void init(){

      task = new TimerTask() {
          @Override
          public void run() {
              if(isAuto){
                  autoHandle.sendEmptyMessage(0);
              }
          }
      };
      timer.schedule(task,1000,3000);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         childCount = getChildCount();
        if(childCount==0){
            setMeasuredDimension(0,0);
        }else{
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            View view = getChildAt(0);
             height = view.getMeasuredHeight();
             this.width = view.getMeasuredWidth();
             int width = view.getMeasuredWidth()*childCount;
             setMeasuredDimension(width,height);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int leftMargin = 0;

          for(int i=0;i<childCount;i++){
              View view = getChildAt(i);
              view.layout(leftMargin,0,leftMargin+width,height);
              leftMargin +=width;

          }
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x = (int) event.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int moveX = (int) event.getX();
//                int distance = moveX - x;
//                scrollBy(-distance, 0);
//                x = moveX;
//                break;
//            case MotionEvent.ACTION_UP:
//                int scrollX = getScrollX();
//                int index = (scrollX+width/2)/width;
//                if(index<0){
//                    index = 0;
//                }else if(index>childCount-1){
//                    index = childCount-1;
//                }
//                scrollTo(index*width,0);
//                break;
//
//        }
//        return true;
// }

    public  void autoStart(){
        isAuto = true;
    }

    public  void autoOff(){
        isAuto= false;
    }

    private void translate(int endX){
        ObjectAnimator translate = ObjectAnimator.ofFloat(this,"x",getX(),endX);
        translate.setDuration(1500);
        translate.start();
    }
}
