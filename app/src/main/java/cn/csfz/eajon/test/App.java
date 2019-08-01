package cn.csfz.eajon.test;

import android.app.Application;

import cn.eajon.tool.Utils;


/**
 * Created By eajon on 2019/7/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
    }
}
