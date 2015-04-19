package com.example.broadcastbestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityCollector {

    public static List<Activity> sActivities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
