package com.sandy.myframe.util;

import android.content.Context;

/**
 * User: cbl
 * Date: 2016/3/28
 * Time: 17:40
 */
public class SPreferencesTool {
    private static SPreferencesTool mInstance;
    private final String profile_name = "userinfo";

    private static final String PREFERENCES_UP_APK_INFO_isUp = "isUp";
    private static final String PREFERENCES_UP_APK_INFO_VERSION = "version";
    private static final String PREFERENCES_UP_APK_INFO_MESSAGE = "message";
    private static final String PREFERENCES_UP_APK_INFO_URL = "upLoadApkUrl";

    public synchronized static SPreferencesTool getInstance() {
        if (mInstance == null) {
            mInstance = new SPreferencesTool();
        }
        return mInstance;
    }

    public SPreferencesTool() {
    }

    public void clearPreferences(Context ctx, String profileName) {
        SharedPreferencesTool.clearPreferences(ctx, profileName);
    }

    public void putValue(Context ctx, String key, Object value) {
        SharedPreferencesTool.putValue(ctx, profile_name, key, value);
    }

    public int getIntValue(Context ctx, String key) {
        return SharedPreferencesTool.getIntValue(ctx, profile_name, key);
    }

    public String getStringValue(Context ctx, String key) {
        return SharedPreferencesTool.getStringValue(ctx, profile_name, key);
    }

    public boolean getBooleanValue(Context ctx, String key) {
        return SharedPreferencesTool.getBooleanValue(ctx, profile_name, key);
    }

    public boolean getBooleanValue(Context ctx, String key, boolean defVal) {
        return SharedPreferencesTool.getBooleanValue(ctx, profile_name, key, defVal);
    }

    public long getLongValue(Context ctx, String key) {
        return SharedPreferencesTool.getLongValue(ctx, profile_name, key);
    }


    public void saveUpLoadApk(Context context, Boolean isUp, String VersionCode, String message, String URl) {
        SPreferencesTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_isUp, isUp);
        SPreferencesTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_VERSION, VersionCode);
        SPreferencesTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_MESSAGE, message);
        SPreferencesTool.getInstance().putValue(context, PREFERENCES_UP_APK_INFO_URL, URl);
    }


    public static class SharedPreferencesConfigs {
        public static final String profile_name = "will_live_user";
        public static final String LIVE_SWITCH = "LIVE_SWITCH";
        public static final String PREFERENCES_UP_APK_INFO_isUp = "isUp";
        public static final String PREFERENCES_UP_APK_INFO_VERSION = "version";
        public static final String PREFERENCES_UP_APK_INFO_MESSAGE = "message";
        public static final String PREFERENCES_UP_APK_INFO_URL = "upLoadApkUrl";
        public static final String PREFERENCES_UP_APK_INFO_CANCEL = "cancel";
        public static final String PREFERENCES_DAY_RECORD = "datetime";
        public static final String PREFERENCES_SYSTEM_MSG = "system_msg";
    }
}
