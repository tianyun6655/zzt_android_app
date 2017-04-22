package com.example.tianyunchen.zaozaotuandroid.untils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by tianyunchen on 3/7/17.
 */

public class ImageLoadUtil {
    private static final  int THREAD_COUNT=4;
    private static final  int PROPRITY  =2;
    private static final int DISK_CACHE_SIZE = 50*1024;
    private static final int CONNECTION_TIME_OUT = 5000;
    private static final int READ_TIME_OUT = 30*1000;


    private static ImageLoader mImageLoader = null;
    private static  ImageLoadUtil mInstance = null;

    private ImageLoadUtil(Context context) {
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context)
        .threadPoolSize(THREAD_COUNT)
        .threadPriority(Thread.NORM_PRIORITY-PROPRITY)
                .denyCacheImageMultipleSizesInMemory()
                //.memoryCache(new WeakMemoryCache())
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getDefaultOption())
                .imageDownloader(new BaseImageDownloader(context,CONNECTION_TIME_OUT,READ_TIME_OUT))
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        mImageLoader  = ImageLoader.getInstance();
    }

    private DisplayImageOptions getDefaultOption(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(20))
                .displayer(new FadeInBitmapDisplayer(200))
                .build();
        return options;

    }
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener loadingListener){
        if(mImageLoader!=null){
            mImageLoader.displayImage(url,imageView,options,loadingListener);
        }
    }

    public void displayImage(ImageView imageView,String url){
        displayImage(imageView,url,null,null);
    }
    public static  ImageLoadUtil getmInstance(Context context){
        if(mInstance==null){
            synchronized (ImageLoadUtil.class){
                if(mInstance==null){
                    mInstance = new ImageLoadUtil(context);
                }
            }
        }
        return  mInstance;
    }



}
