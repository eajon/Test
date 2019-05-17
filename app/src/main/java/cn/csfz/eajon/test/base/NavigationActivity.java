package cn.csfz.eajon.test.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

import butterknife.BindView;
import cn.csfz.eajon.test.R;
import cn.csfz.eajon.test.widget.NoScrollViewPager;

/**
 * Created By eajon on 2019/5/17.
 */
public abstract class NavigationActivity extends BaseActivity {
    @BindView(R.id.bottomNavigation)
    BottomNavigationViewEx bottomNavigation;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;


    public abstract List<Fragment> onInitFragments(int capacity);

    public abstract boolean canScroll();

    @Override
    protected int setContentId() {
        return R.layout.activity_navigation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Fragment> fragments = onInitFragments(bottomNavigation.getMenu().size());
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

    /**
     * view pager adapter
     */
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        private ViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }

    }


}
