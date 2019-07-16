package cn.csfz.eajon.test.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cn.csfz.eajon.test.util.IntentUtils;

/**
 * Created By eajon on 2019/5/16.
 */
public class ActivityUtils {

    private final static String DATA = "DATA";


    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断是否存在Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     */
    public static void launchActivity(Context context, String packageName, String className) {
        launchActivity(context, packageName, className, null);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    public static void launchActivity(Context context, String packageName, String className, Bundle bundle) {
        context.startActivity(IntentUtils.getComponentIntent(packageName, className, bundle));
    }

    public static int getWidth(Activity self) {
        DisplayMetrics dm = new DisplayMetrics();
        self.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getHeight(Activity self) {
        DisplayMetrics dm = new DisplayMetrics();
        self.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static void toActivity(Activity self, Class activity) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        self.startActivity(intent);
    }

    public static void toActivity(Activity self, Class activity, Object data) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        try {
            intent.putExtra(DATA, new Gson().toJson(data));
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        self.startActivity(intent);
    }


    public static void toActivityForResult(Activity self, Class activity, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        self.startActivityForResult(intent, requestCode);
    }

    public static void toActivityForResult(Activity self, Class activity, Object data, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        try {
            intent.putExtra(DATA, new Gson().toJson(data));
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        self.startActivityForResult(intent, requestCode);
    }


    @SuppressWarnings("unchecked")
    public static <T> T getData(Activity self, Class<T> val) {
        try {
            return new Gson().fromJson(self.getIntent().getStringExtra(DATA), val);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public static <T> T getData(Activity self, TypeToken<T> val) {
        try {
            return new Gson().fromJson(self.getIntent().getStringExtra(DATA), val.getType());
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }


}
