package com;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.github.obsessive.library.utils.SPUtils;

/**
 * Created by yezhidong on 2015/10/10.
 */
public class LibApplication extends Application {

    private boolean FirstLaunch;

    @Override
    public void onCreate() {
        super.onCreate();

        FirstLaunch = (Boolean) SPUtils.get(this, "FirstLaunch", true);

        ActiveAndroid.initialize(this);// Android ORM框架ActiveAndroid的初始化
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public boolean isFirstLaunch() {
        return FirstLaunch;
    }

    public void setFirstLaunch(boolean firstLaunch) {
        SPUtils.put(this, "FirstLaunch", firstLaunch);
    }
}
