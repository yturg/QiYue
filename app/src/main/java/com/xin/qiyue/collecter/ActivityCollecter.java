package com.xin.qiyue.collecter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/7/22.
 */

public class ActivityCollecter {
    public static List<Activity> activityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishOthers() {
        for (int i = 0; i < activityList.size() - 1; i++) {
            if (!activityList.get(i).isFinishing()) {
                activityList.get(i).finish();
            }
        }
    }
}
