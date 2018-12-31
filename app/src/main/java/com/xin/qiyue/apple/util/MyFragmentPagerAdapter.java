package com.xin.qiyue.apple.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zxj on 2019-1-1.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> list;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm);
        this.mFragments = fragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment, String string) {
        mFragments.add(fragment);
        list.add(string);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
