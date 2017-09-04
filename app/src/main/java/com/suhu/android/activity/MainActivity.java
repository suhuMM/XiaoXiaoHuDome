package com.suhu.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hjm.bottomtabbar.BottomTabBar;
import com.suhu.android.R;
import com.suhu.android.fragment.FragmentA;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(90, 90)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.BLACK)
                .addTabItem("信息", R.drawable.ic_error_black_24dp, FragmentA.class)
                .addTabItem("云", R.drawable.ic_cloud_black_24dp,   FragmentA.class)
                .addTabItem("我",  R.drawable.ic_person_black_24dp, FragmentA.class)
           //     .setTabBarBackgroundResource(R.mipmap.ic_launcher)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);
                    }
                });

    }
}
