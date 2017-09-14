package com.suhu.android.activity.could;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.suhu.android.R;
import com.suhu.android.adapter.IMPagerAdapter;
import com.suhu.android.base.BaseSlidingActivity;
import com.suhu.android.fragment.FragmentCloud;
import com.suhu.android.fragment.FragmentFriend;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description
 */

public class IMActivity extends BaseSlidingActivity {
    @BindView(R.id.table)
    TabLayout table;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.actionbar)
    RelativeLayout actionbar;


    private List<Fragment> fragments;
    private List<String> tabList;
    private IMPagerAdapter adapter;

    @Override
    public int showContView() {
        return R.layout.activity_im;
    }

    @Override
    public void setActionBar() {
        actionbar.setVisibility(View.GONE);

    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        addData();
        addListener();
    }



    private void addData() {
        tabList = new ArrayList<>();
        tabList.add("聊天");
        tabList.add("聊天组");
        tabList.add("不知道");

        for (String s : tabList) {
            table.addTab(table.newTab().setText(s));
        }
        table.setTabMode(TabLayout.FIND_VIEWS_WITH_TEXT);
        fragments = new ArrayList<>();

        fragments.add(FragmentFriend.getInstance());
        fragments.add(new FragmentCloud());
        fragments.add(new FragmentCloud());

        adapter = new IMPagerAdapter(getSupportFragmentManager(),fragments,tabList);
        viewPager.setAdapter(adapter);
        table.setupWithViewPager(viewPager);
    }


    private void addListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    mSlideBackLayout.edgeOnly(false);
                }else {
                    mSlideBackLayout.edgeOnly(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
