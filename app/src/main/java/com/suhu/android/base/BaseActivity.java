package com.suhu.android.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suhu.android.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public abstract class BaseActivity extends AppCompatActivity {



    @BindView(R.id.left)
    public ImageButton left;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.right)
    public RelativeLayout right;
    @BindView(R.id.frame)
    FrameLayout frame;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //日志：true：加密
        MobclickAgent.enableEncrypt(true);


        setContentView(R.layout.activity_base);
        unbinder = ButterKnife.bind(this);
        setActionBar();
        setCreateView(savedInstanceState);
    }

    public abstract void setActionBar();

    public abstract void setCreateView(Bundle savedInstanceState);


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public View showContentView(int layout) {
        View view = LayoutInflater.from(this).inflate(layout, null);
        if (frame != null) {
            frame.removeAllViews();
            frame.addView(view);
        }
        return view;
    }

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.right:

                break;
        }
    }

}
