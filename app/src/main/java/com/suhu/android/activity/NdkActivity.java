package com.suhu.android.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseSlidingActivity;
import com.suhu.android.ndk.NdkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NdkActivity extends BaseSlidingActivity {


    @BindView(R.id.textView)
    TextView textView;

    @Override
    public int showContView() {
        return R.layout.activity_ndk;
    }

    @Override
    public void setActionBar() {

    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        textView.setText(NdkUtils.getString());
    }



}
