package com.example.inwhites.danmufun;

import android.app.Application;
import android.content.Context;

/**
 * Alias: inwhites
 * <p>
 * Date: 2019/1/6
 * <p>
 * Description:
 */
public class DanmuApplication extends Application {



    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();


        context = getApplicationContext();
    }
}
