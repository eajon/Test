package cn.csfz.eajon.test;

import android.os.Bundle;

import cn.csfz.eajon.test.base.BaseActivity;

/**
 * Created By eajon on 2019/5/16.
 */
public class TestActivity extends BaseActivity {
    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected int setContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initLogic() {
    }

}
