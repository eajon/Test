package cn.csfz.eajon.test.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import cn.csfz.eajon.test.R;
import cn.csfz.eajon.test.base.BaseFragment;

import static cn.csfz.eajon.test.MainActivity.PAGE_TITLE;


/**
 * Created by yu on 2016/11/11.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String title;


    @Override
    protected int setContentId() {
        return R.layout.frag_base;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title = getArguments().getString(PAGE_TITLE);
        tvTitle.setText(title);
    }

    @Override
    protected void initLogic() {

    }


}
