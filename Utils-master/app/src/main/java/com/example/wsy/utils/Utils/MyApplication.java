package com.example.wsy.utils.Utils;

import android.app.Application;

/**
 * Created by wsy on 2017/9/28.
 */

public class MyApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance(){
        return instance;
    }


}
