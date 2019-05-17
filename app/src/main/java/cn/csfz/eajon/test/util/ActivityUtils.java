package cn.csfz.eajon.test.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created By eajon on 2019/5/16.
 */
public class ActivityUtils {

    private final static String DATA = "DATA";


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
        setValueToIntent(intent, data);
        self.startActivity(intent);
    }

    public static void toActivityWithStringList(Activity self, Class activity, ArrayList<String> data) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        intent.putStringArrayListExtra(DATA, data);
        self.startActivity(intent);
    }

    public static void toActivityWithIntegerList(Activity self, Class activity, ArrayList<Integer> data) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        intent.putIntegerArrayListExtra(DATA, data);
        self.startActivity(intent);
    }

    public static void toActivityWithCharSequenceList(Activity self, Class activity, ArrayList<CharSequence> data) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        intent.putCharSequenceArrayListExtra(DATA, data);
        self.startActivity(intent);
    }

    public static void toActivityWithParcelableList(Activity self, Class activity, ArrayList<Parcelable> data) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        intent.putParcelableArrayListExtra(DATA, data);
        self.startActivity(intent);
    }


    public static void toActivityWithJson(Activity self, Class activity, Object object, Type type) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        intent.putExtra(DATA, new Gson().toJson(object, type));
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
        setValueToIntent(intent, data);
        self.startActivityForResult(intent, requestCode);
    }


    public static void toActivityWithJsonForResult(Activity self, Class activity, Object object, Type type, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(self, activity);
        intent.putExtra(DATA, new Gson().toJson(object, type));
        self.startActivityForResult(intent, requestCode);
    }


    private static void setValueToIntent(Intent intent, Object val) {
        if (val instanceof String)
            intent.putExtra(DATA, ( String ) val);
        else if (val instanceof String[])
            intent.putExtra(DATA, ( String[] ) val);
        else if (val instanceof Boolean)
            intent.putExtra(DATA, ( Boolean ) val);
        else if (val instanceof Boolean[])
            intent.putExtra(DATA, ( Boolean[] ) val);
        else if (val instanceof Integer)
            intent.putExtra(DATA, ( Integer ) val);
        else if (val instanceof Integer[])
            intent.putExtra(DATA, ( Integer[] ) val);
        else if (val instanceof Long)
            intent.putExtra(DATA, ( Long ) val);
        else if (val instanceof Long[])
            intent.putExtra(DATA, ( Long[] ) val);
        else if (val instanceof Short)
            intent.putExtra(DATA, ( Short ) val);
        else if (val instanceof Short[])
            intent.putExtra(DATA, ( Short[] ) val);
        else if (val instanceof Double)
            intent.putExtra(DATA, ( Double ) val);
        else if (val instanceof Double[])
            intent.putExtra(DATA, ( Double[] ) val);
        else if (val instanceof Float)
            intent.putExtra(DATA, ( Float ) val);
        else if (val instanceof Float[])
            intent.putExtra(DATA, ( Float[] ) val);
        else if (val instanceof Byte)
            intent.putExtra(DATA, ( Byte ) val);
        else if (val instanceof Byte[])
            intent.putExtra(DATA, ( Byte[] ) val);
        else if (val instanceof Character)
            intent.putExtra(DATA, ( Character ) val);
        else if (val instanceof Character[])
            intent.putExtra(DATA, ( Character[] ) val);
        else if (val instanceof CharSequence)
            intent.putExtra(DATA, ( CharSequence ) val);
        else if (val instanceof CharSequence[])
            intent.putExtra(DATA, ( CharSequence[] ) val);
        else if (val instanceof Bundle)
            intent.putExtra(DATA, ( Bundle ) val);
        else if (val instanceof Parcelable)
            intent.putExtra(DATA, ( Parcelable ) val);
        else if (val instanceof Serializable)
            intent.putExtra(DATA, ( Serializable ) val);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getData(Activity self, Class<T> val) {
        if (String.class.isAssignableFrom(val))
            return ( T ) self.getIntent().getStringExtra(DATA);
        else if (String[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getStringArrayExtra(DATA);
        else if (Boolean.class.isAssignableFrom(val))
            return ( T ) ( Boolean ) self.getIntent().getBooleanExtra(DATA, false);
        else if (Boolean[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getBooleanArrayExtra(DATA);
        else if (Integer.class.isAssignableFrom(val))
            return ( T ) ( Integer ) self.getIntent().getIntExtra(DATA, 0);
        else if (Integer[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getIntArrayExtra(DATA);
        else if (Long.class.isAssignableFrom(val))
            return ( T ) ( Long ) self.getIntent().getLongExtra(DATA, ( long ) 0);
        else if (Long[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getLongArrayExtra(DATA);
        else if (Short.class.isAssignableFrom(val))
            return ( T ) ( Short ) self.getIntent().getShortExtra(DATA, ( short ) 0);
        else if (Short[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getShortArrayExtra(DATA);
        else if (Double.class.isAssignableFrom(val))
            return ( T ) ( Double ) self.getIntent().getDoubleExtra(DATA, ( double ) 0);
        else if (Double[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getDoubleArrayExtra(DATA);
        else if (Float.class.isAssignableFrom(val))
            return ( T ) ( Float ) self.getIntent().getFloatExtra(DATA, ( float ) 0);
        else if (Float[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getFloatArrayExtra(DATA);
        else if (Byte.class.isAssignableFrom(val))
            return ( T ) ( Byte ) self.getIntent().getByteExtra(DATA, ( byte ) 0);
        else if (Byte[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getByteArrayExtra(DATA);
        else if (Character.class.isAssignableFrom(val))
            return ( T ) ( Character ) self.getIntent().getCharExtra(DATA, Character.MIN_VALUE);
        else if (Character[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getCharArrayExtra(DATA);
        else if (CharSequence.class.isAssignableFrom(val))
            return ( T ) self.getIntent().getCharSequenceExtra(DATA);
        else if (CharSequence[].class.isAssignableFrom(val))
            return ( T ) self.getIntent().getCharSequenceArrayExtra(DATA);
        else if (Bundle.class.isAssignableFrom(val))
            return ( T ) self.getIntent().getBundleExtra(DATA);
        else if (Parcelable.class.isAssignableFrom(val))
            return ( T ) self.getIntent().getParcelableExtra(DATA);
        else if (Serializable.class.isAssignableFrom(val))
            return ( T ) self.getIntent().getSerializableExtra(DATA);
        return null;
    }

    public static <T> T getJsonData(Activity self, Class<T> type) {
        return new Gson().fromJson(getData(self, String.class), type);
    }

    public static ArrayList<String> getStringListData(Activity self) {
        return self.getIntent().getStringArrayListExtra(DATA);
    }

    public static ArrayList<Integer> getIntegerListData(Activity self) {
        return self.getIntent().getIntegerArrayListExtra(DATA);
    }

    public static ArrayList<CharSequence> getCharSequenceListData(Activity self) {
        return self.getIntent().getCharSequenceArrayListExtra(DATA);
    }

    public static ArrayList<Parcelable> getParcelableListData(Activity self) {
        return self.getIntent().getParcelableArrayListExtra(DATA);
    }
}
