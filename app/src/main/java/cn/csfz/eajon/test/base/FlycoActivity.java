package cn.csfz.eajon.test.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import cn.csfz.eajon.test.R;
import cn.csfz.eajon.test.adapter.ViewPagerAdapter;
import cn.csfz.eajon.test.widget.NoScrollViewPager;

/**
 * Created By eajon on 2019/6/17.
 */
public abstract class FlycoActivity extends BaseActivity {
    @BindView(R.id.flyco)
    CommonTabLayout flyco;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;


    public abstract ArrayList<Fragment> onInitFragments();

    public abstract ArrayList<CustomTabEntity> onInitFlycoTab(int size);

    public abstract boolean canScroll();


    @Override
    protected int setContentId() {
        return R.layout.activity_flyco;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Fragment> fragments = onInitFragments();

        // set adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setScroll(canScroll());

        flyco.setTabData(onInitFlycoTab(fragments.size()));
        flyco.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
