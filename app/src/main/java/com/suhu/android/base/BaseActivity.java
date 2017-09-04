package com.suhu.android.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //日志：true：加密
        MobclickAgent.enableEncrypt(true);
        setContentView(showContView());
        unbinder = ButterKnife.bind(this);
        setActionBar();
        setCreateView(savedInstanceState);


    }

    public abstract int showContView();
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


    @OnClick({R.id.left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
        }
    }

}
