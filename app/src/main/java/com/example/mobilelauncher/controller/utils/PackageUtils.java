package com.example.mobilelauncher.controller.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class PackageUtils {
    public static List<ResolveInfo> getLauncherActivities(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent getLuncherIntent = getLauncherIntent();

        List<ResolveInfo> activities = packageManager.queryIntentActivities(getLuncherIntent, 0);
        return activities;

    }

    public static Intent getLauncherIntent() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return intent;
    }

    public static ComponentName getComponentName(ResolveInfo resolveInfo){
        return new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName
                ,resolveInfo.activityInfo.name);
    }
}