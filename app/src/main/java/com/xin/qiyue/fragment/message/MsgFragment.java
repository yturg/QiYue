package com.xin.qiyue.fragment.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xin.qiyue.apple.util.MyFragmentPagerAdapter;
import com.xin.qiyue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/7/22.
 */

public class MsgFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    List<Fragment> data = new ArrayList<>();
    List<String> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_layout,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.msg_viewpager);

        tabLayout = view.findViewById(R.id.msg_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        MsgViewPgFgLeft left = new MsgViewPgFgLeft();
        MsgViewPgFgMid mid = new MsgViewPgFgMid();
        MsgViewPgFgRight right = new MsgViewPgFgRight();

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),data,list);
        adapter.addFragment(left,"动态");
        adapter.addFragment(mid,"关于你");
        adapter.addFragment(right,"通知");
        viewPager.setAdapter(adapter);
    }
}
