package cn.csfz.eajon.test.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class SpUtils {

    private volatile static SpUtils spUtil;
    private static String SP_NAME = "SP_NAME";

    private SharedPreferences sp;


    private SpUtils(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次，建议在Application中初始化
     */
    public static SpUtils getInstance() {
        if (spUtil == null) {
            synchronized (SpUtils.class) {
                if (spUtil == null)
                    spUtil = new SpUtils(Utils.getContext(), SP_NAME);
            }
        }
        return spUtil;
    }



    public boolean putData(String key, Object val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(val));
        return editor.commit();
    }

    public boolean putData(String key, Object val, Type type) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(val, type));
        return editor.commit();
    }


    public void putDataAsync(String key, Object val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(val));
        editor.apply();
    }

    public void putDataAsync(String key, Object val, Type type) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(val, type));
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(String key, Class<T> val) {
            return new Gson().fromJson(sp.getString(key, ""), val);
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(String key, TypeToken<T> val) {
            return new Gson().fromJson(sp.getString(key, ""), val.getType());

    }
}
