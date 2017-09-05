package com.suhu.android.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;
import com.suhu.android.R;
import com.suhu.android.base.BaseActivity;
import com.suhu.android.fragment.FragmentA;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBottomTabBar();
    }


    private void setBottomTabBar() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(90, 90)
                .setFontSize(12)
                .setTabPadding(10, 3, 10)
                .setChangeColor(Color.RED, Color.BLACK)
                .addTabItem("信息", R.drawable.ic_error_black_24dp, FragmentA.class)
                .addTabItem("云", R.drawable.ic_cloud_black_24dp,   FragmentA.class)
                .addTabItem("我",  R.drawable.ic_person_black_24dp, FragmentA.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }

}
