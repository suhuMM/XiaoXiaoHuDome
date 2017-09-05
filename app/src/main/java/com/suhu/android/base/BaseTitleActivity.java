package com.suhu.android.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suhu.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public abstract class BaseTitleActivity extends BaseActivity {


    @BindView(R.id.left)
    public ImageButton left;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.right)
    public RelativeLayout right;

    private Unbinder unbinder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(showContView());
        unbinder = ButterKnife.bind(this);
        setActionBar();
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

}
