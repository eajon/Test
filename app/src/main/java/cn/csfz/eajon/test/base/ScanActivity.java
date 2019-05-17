package cn.csfz.eajon.test.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * Created By eajon on 2019/3/19.
 */
public abstract class ScanActivity extends BaseActivity {

    public abstract void onScanResult(String data);

    IntentFilter intentFilter;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentFilter = new IntentFilter("android.intent.action.RECEIVE_SCANDATA");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String code = intent.getStringExtra("android.intent.extra.SCAN_DATA");
                onScanResult(code);
            }
        };
    }

    public void onResume() {
        super.onResume();
        //2.连接扫描服务
        self.registerReceiver(mReceiver, intentFilter);

    }

    // 4.在App不工作或者退到后台之后,有必要释放资源,关闭或者移除扫描服务
    public void onPause() {
        self.unregisterReceiver(mReceiver);
        super.onPause();
    }

}
