package com.nhom03.hust.quanlydonhang;

import android.app.Application;
import android.content.Context;

/**
 * Created by Admin on 02/12/2016.
 */

public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
