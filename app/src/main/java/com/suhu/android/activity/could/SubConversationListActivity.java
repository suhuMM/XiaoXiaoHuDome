package com.suhu.android.activity.could;

import android.os.Bundle;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseSlidingActivity;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description  聚合会话列表
 */

public class SubConversationListActivity extends BaseSlidingActivity{
    @Override
    public int showContView() {
        return R.layout.activity_sub;
    }

    @Override
    public void setActionBar() {
        title.setText("会话列表");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {

    }
}
