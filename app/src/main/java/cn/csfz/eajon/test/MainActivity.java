package cn.csfz.eajon.test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import cn.csfz.eajon.test.base.NavigationActivity;
import cn.csfz.eajon.test.base.Response;
import cn.csfz.eajon.test.fragment.MyFragment;
import cn.csfz.eajon.test.util.SpUtils;


public class MainActivity extends NavigationActivity {

    public final static String PAGE_TITLE = "page_title";
    @Override
    protected void init(Bundle savedInstanceState) {
        initToolBar(true, false, false, false, false, false);
        Response response =new Response();
        response.setCode(1);
        List<Response> responseArrayList =new ArrayList<>();
        responseArrayList.add(response);
        Map<String,Response> hashMap =new HashMap<>();
        hashMap.put("3",response);
//        SpUtils.getInstance().putData("1",response);
//        SpUtils.getInstance().putData("2",responseArrayList);
//        SpUtils.getInstance().putData("3",hashMap);
        SpUtils.getInstance().putData("5","5");
//        SpUtils.getInstance().putData("4",String.class);
        Response response1 = SpUtils.getInstance().getData("1",Response.class);
        List<Response> response2 = SpUtils.getInstance().getData("2",new TypeToken<List<Response>>(){});
        HashMap<String,Response> response3 = SpUtils.getInstance().getData("3",new TypeToken<HashMap<String,Response>>(){});
        String a = SpUtils.getInstance().getData("5",String.class);

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
    public ArrayList<Fragment> onInitFragments(int capacity) {
        ArrayList<Fragment> fragments = new ArrayList<>(capacity);

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
