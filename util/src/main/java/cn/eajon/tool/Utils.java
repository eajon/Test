package cn.eajon.tool;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * Created by liaohailiang on 2019/3/26.
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static volatile Application application;

    public static Application getContext() {
        if (application == null) {
            synchronized (Utils.class) {
                if (application == null) {
                    try {
                        application = ( Application ) Class.forName("android.app.ActivityThread")
                                .getMethod("currentApplication")
                                .invoke(null, ( Object[] ) null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return application;
    }
}
