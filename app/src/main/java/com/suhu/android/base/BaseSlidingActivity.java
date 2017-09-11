package com.suhu.android.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oubowu.slideback.SlideBackHelper;
import com.oubowu.slideback.SlideConfig;
import com.oubowu.slideback.widget.SlideBackLayout;
import com.suhu.android.R;
import com.suhu.android.application.SoftwareApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/2 0002.
 * https://github.com/YoKeyword/SwipeBackFragment
 */

public abstract class BaseSlidingActivity extends AppCompatActivity {


    @BindView(R.id.left)
    public ImageButton left;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.right)
    public RelativeLayout right;

    private Unbinder unbinder;
    private SlideBackLayout mSlideBackLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(showContView());
        unbinder = ButterKnife.bind(this);
        setActionBar();
        sliding();
        setCreateView(savedInstanceState);

    }



    public abstract int showContView();
    public abstract void setActionBar();
    public abstract void setCreateView(Bundle savedInstanceState);




    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick({R.id.left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
        }
    }

    protected  void sliding(){
        mSlideBackLayout = SlideBackHelper.attach(
                // 当前Activity
                this,
                // Activity栈管理工具
                SoftwareApp.getActivityHelper(),
                // 参数的配置
                new SlideConfig.Builder()
                        // 屏幕是否旋转
                        .rotateScreen(true)
                        // 是否侧滑
                        .edgeOnly(false)
                        // 是否禁止侧滑
                        .lock(false)
                        // 侧滑的响应阈值，0~1，对应屏幕宽度*percent
                        .edgePercent(0.1f)
                        // 关闭页面的阈值，0~1，对应屏幕宽度*percent
                        .slideOutPercent(0.5f).create(),
                // 滑动的监听
                null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSlideBackLayout.isComingToFinish();
        overridePendingTransition(R.anim.anim_none, R.anim.anim_slide_out);
    }
}
