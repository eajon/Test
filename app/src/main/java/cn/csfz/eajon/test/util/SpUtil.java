package cn.csfz.eajon.test.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.csfz.eajon.test.R;

public class SpUtil {

    private static SpUtil spUtil;

    private SharedPreferences sp;


    private SpUtil(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次，建议在Application中初始化
     */
    public static SpUtil getInstance() {
        synchronized (SpUtil.class) {
            if (spUtil == null) {
                spUtil = new SpUtil(Utils.getContext(), Utils.getContext().getString(R.string.app_name));
            }
            return spUtil;
        }
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
