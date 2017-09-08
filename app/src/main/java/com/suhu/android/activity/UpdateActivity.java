package com.suhu.android.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.BaseTitleActivity;

import butterknife.OnClick;
import ezy.boost.update.ICheckAgent;
import ezy.boost.update.IUpdateChecker;
import ezy.boost.update.IUpdateParser;
import ezy.boost.update.UpdateInfo;
import ezy.boost.update.UpdateManager;
import ezy.boost.update.UpdateUtil;

/**
 * @author suhu
 * @data 2017/9/8.
 * @description https://github.com/czy1121/update
 */

public class UpdateActivity extends BaseTitleActivity {

    String mCheckUrl = "http://client.waimai.baidu.com/message/updatetag";

    String mUpdateUrl = "http://mobile.ac.qq.com/qqcomic_android.apk";

    @Override
    public int showContView() {
        return R.layout.activity_update;
    }

    @Override
    public void setActionBar() {
        title.setText("版本更新");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        UpdateManager.setDebuggable(true);
        UpdateManager.setWifiOnly(false);
        UpdateManager.setUrl(mCheckUrl, "yyb");
        UpdateManager.check(this);
        check(false, true, false, false, true, 998);
    }


    @OnClick({R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.button_1:
                check(true, true, false, false, true, 998);
                break;
            case R.id.button_2:
                check(true, true, false, false, false, 998);
                break;
            case R.id.button_3:
                check(true, true, true, false, true, 998);
                break;
            case R.id.button_4:
                check(true, false, false, false, true, 998);
                break;
            case R.id.button_5:
                check(true, true, false, true, true, 998);
                break;
            case R.id.button_6:
                UpdateUtil.clean(this);
                Toast.makeText(this, "cleared", Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void check(boolean isManual, final boolean hasUpdate, final boolean isForce, final boolean isSilent, final boolean isIgnorable, final int
            notifyId) {
        UpdateManager
                .create(this)
                .setChecker(new IUpdateChecker() {
                    @Override
                    public void check(ICheckAgent agent, String url) {
                        Log.e("ezy.update", "checking");
                        agent.setInfo("");
                    }
                })
                .setUrl(mCheckUrl)
                .setManual(isManual)
                .setNotifyId(notifyId)
                .setParser(new IUpdateParser() {
                    @Override
                    public UpdateInfo parse(String source) throws Exception {
                        UpdateInfo info = new UpdateInfo();
                        info.hasUpdate = hasUpdate;
                        info.updateContent = "• 支持文字、贴纸、背景音乐，尽情展现欢乐气氛；\n• 两人视频通话支持实时滤镜，丰富滤镜，多彩心情；\n• 图片编辑新增艺术滤镜，一键打造文艺画风；\n• 资料卡新增点赞排行榜，看好友里谁是魅力之王。";
                        info.versionCode = 587;
                        info.versionName = "v5.8.7";
                        info.url = mUpdateUrl;
                        info.md5 = "56cf48f10e4cf6043fbf53bbbc4009e3";
                        info.size = 10149314;
                        info.isForce = isForce;
                        info.isIgnorable = isIgnorable;
                        info.isSilent = isSilent;
                        return info;
                    }
                }).check();
    }


}
