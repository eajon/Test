package cn.csfz.eajon.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import cn.csfz.eajon.test.base.BaseActivity;
import cn.csfz.eajon.test.base.FlycoActivity;
import cn.csfz.eajon.test.fragment.MyFragment;
import cn.csfz.eajon.test.model.FlycoTab;

import static cn.csfz.eajon.test.MainActivity.PAGE_TITLE;

/**
 * Created By eajon on 2019/5/16.
 */
public class TestActivity extends FlycoActivity {
    private String[] mTitles = {"首页", "消息", "联系人"};
    private int[] mIconUnselectIds = {
            R.drawable.ic_check_white_48dp, R.drawable.ic_check_white_48dp,
            R.drawable.ic_check_white_48dp};
    private int[] mIconSelectIds = {
            R.drawable.ic_check_white_48dp, R.drawable.ic_check_white_48dp,
            R.drawable.ic_check_white_48dp};
    @Override
    public ArrayList<Fragment> onInitFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

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
    public ArrayList<CustomTabEntity> onInitFlycoTab(int size) {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mTabEntities.add(new FlycoTab(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        return mTabEntities;
    }

    @Override
    public boolean canScroll() {
        return false;
    }

    protected void init(Bundle savedInstanceState) {
        initToolBar(false, true, false, false, false, false);
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }
}
