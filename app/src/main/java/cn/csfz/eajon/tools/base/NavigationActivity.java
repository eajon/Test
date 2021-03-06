package cn.csfz.eajon.tools.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import butterknife.BindView;
import cn.csfz.eajon.tools.R;
import cn.csfz.eajon.tools.adapter.ViewPagerAdapter;
import cn.csfz.eajon.tools.widget.NoScrollViewPager;

/**
 * Created By eajon on 2019/5/17.
 */
public abstract class NavigationActivity extends BaseActivity {
    @BindView(R.id.bottomNavigation)
    BottomNavigationViewEx bottomNavigation;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;


    public abstract ArrayList<Fragment> onInitFragments(int capacity);

    public abstract boolean canScroll();

    @Override
    protected int setContentId() {
        return R.layout.activity_navigation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Fragment> fragments = onInitFragments(bottomNavigation.getMenu().size());
        if (null != fragments && fragments.size() != bottomNavigation.getMenu().size()) {
            throw new ExceptionInInitializerError(fragments.size() + " != size of menu " + bottomNavigation.getMenu().size());
        }
        // set adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setScroll(canScroll());

        // binding with ViewPager
        bottomNavigation.setupWithViewPager(viewPager);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.enableItemShiftingMode(false);
    }




}
