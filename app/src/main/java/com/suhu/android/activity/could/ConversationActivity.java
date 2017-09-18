package com.suhu.android.activity.could;

import android.os.Bundle;
import android.text.TextUtils;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseTitleActivity;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description 会话界面
 */

public class ConversationActivity extends BaseTitleActivity {
    @Override
    public int showContView() {
        return R.layout.activity_cloud_im;
    }

    @Override
    public void setActionBar() {

    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        //getIntent().getData().getQueryParameter("targetId");//获取id
        String name = getIntent().getData().getQueryParameter("title");//获取昵称
        if (!TextUtils.isEmpty(name)) {
            title.setText(name);
        }
    }
}
