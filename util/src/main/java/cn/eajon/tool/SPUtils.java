/*
 * Copyright (C) 2016 Viking Den <vikingden@live.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.eajon.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Utility class of SharedPreference , easy to get and put data
 * author : Viking Den
 * time   : 2016/7/27 22:26
 * email  : vikingden@live.com
 */
public class SPUtils {
    public static String PREFERENCE_NAME = null;

    private SPUtils() {
        throw new AssertionError();
    }

    /**
     * initial shared preference name
     *
     * @param preferenceName name for the shared preference
     */
    public static void initPreferenceName(String preferenceName) {
        PREFERENCE_NAME = preferenceName;
    }

    /**
     * According to PREFERENCE_NAME , return it's SharedPreference
     * <p>
     * or {@link android.app.Activity} object.
     *
     * @return return a shared preference , if PREFERENCE_NAME is not null ,via Context.getSharedPreferences ; or
     * via PreferenceManager.getDefaultSharedPreferences
     */
    private static SharedPreferences getSharedPreference() {
        if (PREFERENCE_NAME != null) {
            return Utils.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return PreferenceManager.getDefaultSharedPreferences(Utils.getContext());
    }

    /**
     * Get a new Editor for these preferences, through which you can make
     * modifications to the data in the preferences and atomically commit those
     * changes back to the SharedPreferences object.
     *
     * @return Returns a new instance of the {@link SharedPreferences.Editor} interface, allowing
     * you to modify the values in this SharedPreferences object.
     */
    private static SharedPreferences.Editor getEditor() {
        return getSharedPreference().edit();
    }

    public static boolean putData(String key, Object val) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, new Gson().toJson(val));
        return editor.commit();
    }

    public static boolean putData(String key, Object val, Type type) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, new Gson().toJson(val, type));
        return editor.commit();
    }


    public static void putDataAsync(String key, Object val) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, new Gson().toJson(val));
        editor.apply();
    }

    public static void putDataAsync(String key, Object val, Type type) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, new Gson().toJson(val, type));
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getData(String key, Class<T> val) {
        return new Gson().fromJson(getSharedPreference().getString(key, ""), val);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getData(String key, TypeToken<T> val) {
        return new Gson().fromJson(getSharedPreference().getString(key, ""), val.getType());

    }

    /**
     * Remove all values from the preferences
     */
    public static void clearAll() {
        SharedPreferences sp = getSharedPreference();
        sp.edit().clear().apply();
    }
}