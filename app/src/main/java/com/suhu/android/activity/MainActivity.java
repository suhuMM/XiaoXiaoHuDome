package com.suhu.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.luck.picture.lib.config.PictureConfig;
import com.suhu.android.R;
import com.suhu.android.application.SoftwareApp;
import com.suhu.android.application.User;
import com.suhu.android.base.activity.BaseNoTitleActivity;
import com.suhu.android.db.FriendModel;
import com.suhu.android.db.utils.TabConfig;
import com.suhu.android.db.utils.TableManager;
import com.suhu.android.fragment.FragmentCloud;
import com.suhu.android.fragment.FragmentInformation;
import com.suhu.android.fragment.FragmentPerson;
import com.suhu.android.utils.AddFriend;
import com.suhu.android.utils.Config;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseNoTitleActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    private Uri uri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBottomTabBar();
        connect(User.getInstance().getToken());
        AddFriend.addFriendList(this);
    }


    private void setBottomTabBar() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(90, 90)
                .setFontSize(12)
                .setTabPadding(10, 3, 10)
                .setChangeColor(Color.RED, Color.BLACK)
                .addTabItem("信息", R.drawable.ic_error_black_24dp, FragmentInformation.class)
                .addTabItem("云", R.drawable.ic_cloud_black_24dp, FragmentCloud.class)
                .addTabItem("我", R.drawable.ic_person_black_24dp, FragmentPerson.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    EventBus.getDefault().post(data);
                    break;
                default:
            }
        }
    }

    /**
     * @param token 从服务端获取的用户身份令牌
     * @return userId
     * @method 连接融云
     * @author suhu
     * @time 2017/9/14 9:50
     */
    private void connect(String token) {
        uri = Uri.fromFile(new File(Config.PHOTO_URL));
        if (getApplicationInfo().packageName.equals(SoftwareApp.getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {
                    Toast.makeText(MainActivity.this, "Token过期", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(String userId) {
                    try {
                        User.getInstance().setUserId(userId);
                        if (RongIM.getInstance() != null) {
                            TableManager manager = new TableManager();
                            List<FriendModel> models = manager.query(TabConfig.Friend.TAB_NAME, FriendModel.class, TabConfig.Friend.USER_ID, User.getInstance().getUserId());
                            String name = models.get(0).getName();
                            User.getInstance().setName(name);
                            RongIM.getInstance().setCurrentUserInfo(new UserInfo(User.getInstance().getUserId(), name, uri));
                            RongIM.getInstance().setMessageAttachedUserInfo(true);
                        }
                    } catch (Exception e) {

                    }
                    Toast.makeText(MainActivity.this, "融云连接成功", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Toast.makeText(MainActivity.this, "连接融云失败", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
