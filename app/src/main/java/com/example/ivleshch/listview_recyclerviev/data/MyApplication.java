package com.example.ivleshch.listview_recyclerviev.data;

import android.app.Application;

/**
 * Created by Ivleshch on 10.11.2016.
 */
public class MyApplication extends Application {

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}
