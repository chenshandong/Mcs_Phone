package com.yzd.android.mcs_phone.api;

import android.content.Context;

import com.LibApplication;
import com.android.volley.toolbox.StringRequest;
import com.github.obsessive.library.base.BaseAppManager;
import com.github.obsessive.library.utils.SPUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2015/10/10.
 */
public class McsApplication extends LibApplication {

    private boolean DebugMode = true;

    public boolean isDebugMode() {
        return DebugMode;
    }
    public void setDebugMode(boolean debugMode) {
        DebugMode = debugMode;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }


    public boolean getNewFeature() {
        return (boolean) SPUtils.get(getApplicationContext(), "isNewFeature", true);
    }
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public void exitApp() {
        BaseAppManager.getInstance().clear();
        System.gc();
        MobclickAgent.onKillProcess(this);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
