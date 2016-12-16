package com.example.ivleshch.listview_recyclerviev.data;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Ivleshch on 10.11.2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration =
                new RealmConfiguration.Builder()
                        .name("realm_demo.realm")
                        .schemaVersion(1)
                        .build();
        Realm.setDefaultConfiguration(configuration);
    }

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
