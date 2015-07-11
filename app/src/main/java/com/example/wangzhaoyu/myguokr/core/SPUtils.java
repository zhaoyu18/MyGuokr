package com.example.wangzhaoyu.myguokr.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author wangzhaoyu
 */
public class SPUtils {
    public final static String TAG = SPUtils.class.getSimpleName();
    private SharedPreferences mSharePref;
    private SharedPreferences.Editor mSpEditor;
    private boolean mIsInit = false;

    private static class InstanceHolder {
        public static SPUtils mHolder = new SPUtils();
    }

    public static SPUtils getInstance() {
        return InstanceHolder.mHolder;
    }

    public void init(Context context) {
        mSharePref = context.getSharedPreferences("my_guokr_sp", Context.MODE_PRIVATE);
        //TODO always on?
        mSpEditor = mSharePref.edit();
        mIsInit = true;
    }

    public void putString(String key, String value) {
        if (!mIsInit)
            return;
        mSpEditor.putString(key, value);
        mSpEditor.commit();
    }

    public String getString(String key, String defaultvalue) {
        if (!mIsInit)
            return defaultvalue;
        return mSharePref.getString(key, defaultvalue);
    }

    public String getString(String key) {
        if (!mIsInit)
            return null;
        return mSharePref.getString(key, null);
    }

    public void putInt(String key, int value) {
        if (!mIsInit)
            return;
        mSpEditor.putInt(key, value);
        mSpEditor.commit();
    }

    public void putFloat(String key, float value) {
        if (!mIsInit)
            return;
        mSpEditor.putFloat(key, value);
        mSpEditor.commit();
    }

    public float getFloat(String key, float defValue) {
        if (!mIsInit)
            return defValue;
        return mSharePref.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        if (!mIsInit)
            return defValue;
        return mSharePref.getInt(key, defValue);
    }

    public int getInt(String key) {
        if (!mIsInit)
            return 0;
        return mSharePref.getInt(key, 0);
    }

    public void putLong(String key, long value) {
        if (!mIsInit)
            return;
        mSpEditor.putLong(key, value);
        mSpEditor.commit();
    }

    public long getLong(String key, long defValue) {
        if (!mIsInit)
            return defValue;
        return mSharePref.getLong(key, defValue);
    }

    public long getLong(String key) {
        if (!mIsInit)
            return 0;
        return mSharePref.getLong(key, 0);
    }

    public void putBoolean(String key, boolean value) {
        if (!mIsInit)
            return;
        mSpEditor.putBoolean(key, value);
        mSpEditor.commit();
    }

    public boolean getBoolean(String key) {
        if (!mIsInit)
            return false;
        return mSharePref.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        if (!mIsInit)
            return defValue;
        return mSharePref.getBoolean(key, defValue);
    }

    public void remove(String key) {
        if (!mIsInit) {
            return;
        }
        mSpEditor.remove(key).commit();
    }

    public void clearLoginInfo() {
        if (mIsInit) {

        }
    }

    public final class KEYS {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String STRING_COOKIE = "string_cookie";
        public static final String USER_UKEY = "user_ukey";
    }
}
