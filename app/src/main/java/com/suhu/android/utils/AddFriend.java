package com.suhu.android.utils;

import android.Manifest;
import android.app.Activity;
import android.widget.Toast;

import com.luck.picture.lib.permissions.RxPermissions;
import com.suhu.android.R;
import com.suhu.android.application.SoftwareApp;
import com.suhu.android.db.FriendModel;
import com.suhu.android.db.utils.TabConfig;
import com.suhu.android.db.utils.TableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class AddFriend {

    public static void addFriendList(Activity activity) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    setData();
                } else {
                    Toast.makeText(SoftwareApp.getInstance(),
                            SoftwareApp.getInstance().getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private static void setData(){
        FriendModel friend_1 = new FriendModel();
        friend_1.setUserId("10086");
        friend_1.setName("移动");
        friend_1.setToken("YeBfudEd4kx52Qv/vSR1F4Nw32R3eixTPXxyUXopBsUzPNc5LVg5qbHf6jzGd+3q7gveb6sBTvQrM02kBsHLJA==");
        friend_1.setUrl("http://img1.imgtn.bdimg.com/it/u=806806176,4079081363&fm=27&gp=0.jpg");

        FriendModel friend_2 = new FriendModel();
        friend_2.setUserId("3argexb630q4e");
        friend_2.setName("苏虎");
        friend_2.setToken("6JvpriAko02wYBlh3yTIjD8S/nVdu+xyK4vZkxev90Z4gc7fI1Qv89w6dWL6wb/XDXEiBS2o8gZmGFWW7ddH9TaHBQHGCCPDEnOjvcD3aCk=");
        friend_2.setUrl("http://www.51zxw.net/bbs/UploadFile/2013-4/201341122335711220.jpg");

        TableManager manager = new TableManager();
        int a = manager.insert(TabConfig.Friend.TAB_NAME,friend_1);
        if (a > 0) {
            Toast.makeText(SoftwareApp.getInstance(), "ok", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SoftwareApp.getInstance(), "失败", Toast.LENGTH_SHORT).show();
        }
        manager.insert(TabConfig.Friend.TAB_NAME,friend_2);
    }

}
