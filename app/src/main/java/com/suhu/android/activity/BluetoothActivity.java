package com.suhu.android.activity;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.Toast;

import com.luck.picture.lib.permissions.RxPermissions;
import com.suhu.android.R;
import com.suhu.android.base.activity.BaseSlidingActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class BluetoothActivity extends BaseSlidingActivity {
    private BluetoothAdapter adapter;

    @Override
    public int showContView() {
        return R.layout.activity_bluetooth;
    }

    @Override
    public void setActionBar() {
        title.setText("蓝牙基本使用");

    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        permission();
    }



    private void permission(){
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.BLUETOOTH
                ,Manifest.permission.BLUETOOTH_ADMIN).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    adapter = BluetoothAdapter.getDefaultAdapter();
                    if (!adapter.isEnabled()){
                        adapter.enable();
                    }
                } else {
                    Toast.makeText(BluetoothActivity.this,
                            "蓝牙打开失败", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter!=null&&adapter.isEnabled()){
            adapter.disable();
        }

    }
}
