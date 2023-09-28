package com.techBMT.YTV_Player_Pro.constant;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static final String SHOP_SHARED_PREFERENCES = "com.techBMT.myapplication.constan";
    private static final String FIRST_OPEN_APP = "FIRST_OPEN_APP";

    private static SharedPreferences getSharePref(Context context) {
        return context.getSharedPreferences(SHOP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }


    public static void setFirstOpenApp(boolean isFirst,Context context) {
        saveValueToKey(context, FIRST_OPEN_APP, isFirst);
    }

    public static boolean isFirstOpenApp(Context context) {
        return getSharePref(context).getBoolean(FIRST_OPEN_APP, true);
    }
    private static void saveValueToKey(Context context, String key, boolean value) {
        try {
            if (null != context) {
                SharedPreferences.Editor editor = getSharePref(context).edit();
                editor.putBoolean(key, value);
                editor.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
