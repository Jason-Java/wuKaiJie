package com.example.projectone;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Myapplication extends LitePalApplication {
    //运用list来保存们每一个activity,链表
    private static List<Activity> mList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                mList.add(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                mList.remove(activity);
            }
        });
    }

    public static void closeAllActivities() {
        for (Activity activity : mList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mList.clear();
    }

}
