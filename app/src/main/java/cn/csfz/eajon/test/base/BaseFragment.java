package cn.csfz.eajon.test.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.trello.rxlifecycle3.components.support.RxFragment;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.csfz.eajon.test.R;


/**
 * Created By eajon on 2019/5/17.
 */

public abstract class BaseFragment extends RxFragment {

    protected Activity self;

    @LayoutRes
    protected abstract int setContentId();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initLogic();


    /******************************lifecycle area*****************************************/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resId = setContentId();
        return inflater.inflate(resId, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        self = this.getActivity();
        ButterKnife.bind(this, view);
        init(savedInstanceState);
        initLogic();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
