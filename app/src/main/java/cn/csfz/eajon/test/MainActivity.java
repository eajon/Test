package cn.csfz.eajon.test;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.csfz.eajon.test.base.NavigationActivity;
import cn.csfz.eajon.test.fragment.MyFragment;


public class MainActivity extends NavigationActivity {

    public final static String PAGE_TITLE = "page_title";
    @Override
    protected void init(Bundle savedInstanceState) {
        initToolBar(true, false, false, false, false, false);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public List<Fragment> onInitFragments(int capacity) {
        List<Fragment> fragments = new ArrayList<>(capacity);

        // create music fragment and add it
        MyFragment musicFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PAGE_TITLE, "音乐");
        musicFragment.setArguments(bundle);

        // create backup fragment and add it
        MyFragment backupFragment = new MyFragment();
        bundle = new Bundle();
        bundle.putString(PAGE_TITLE, "备份");
        backupFragment.setArguments(bundle);

        // create friends fragment and add it
        MyFragment friendsFragment = new MyFragment();
        bundle = new Bundle();
        bundle.putString(PAGE_TITLE, "好友");
        friendsFragment.setArguments(bundle);

        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(friendsFragment);

        return fragments;
    }

    @Override
    public boolean canScroll() {
        return false;
    }
}
