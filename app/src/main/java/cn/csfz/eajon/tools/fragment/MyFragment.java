package cn.csfz.eajon.tools.fragment;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import cn.csfz.eajon.tools.R;
import cn.csfz.eajon.tools.base.BaseFragment;

import static cn.csfz.eajon.tools.MainActivity.PAGE_TITLE;


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
