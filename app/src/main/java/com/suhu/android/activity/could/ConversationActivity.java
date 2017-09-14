package com.suhu.android.activity.could;

import android.os.Bundle;

import com.suhu.android.R;
import com.suhu.android.base.BaseSlidingActivity;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description 会话界面
 */

public class ConversationActivity extends BaseSlidingActivity{
    @Override
    public int showContView() {
        return R.layout.activity_cloud_im;
    }

    @Override
    public void setActionBar() {
        title.setText("会话界面");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {

    }
}
