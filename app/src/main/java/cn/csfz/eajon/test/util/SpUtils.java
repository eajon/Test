package cn.csfz.eajon.test.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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
        try {
            editor.putString(key, new Gson().toJson(val));
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return editor.commit();
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(String key, Class<T> val) {
        try {
            return new Gson().fromJson(sp.getString(key, ""), val);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(String key, TypeToken<T> val) {
        try {
            return new Gson().fromJson(sp.getString(key, ""), val.getType());
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
