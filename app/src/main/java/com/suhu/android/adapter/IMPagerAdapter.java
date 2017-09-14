package com.suhu.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by suhu on 2017/8/29.
 */

public class IMPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    private List<String> titles;

    public IMPagerAdapter(FragmentManager fm, List<Fragment> fragments , List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments!=null?fragments.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
