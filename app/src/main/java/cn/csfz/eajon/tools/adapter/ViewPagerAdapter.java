package cn.csfz.eajon.tools.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created By eajon on 2019/6/17.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> data;

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> data) {
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

