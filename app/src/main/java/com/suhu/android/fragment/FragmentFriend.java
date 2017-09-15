package com.suhu.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suhu.android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description
 */

public class FragmentFriend extends Fragment {

    private Unbinder unbinder;


    private static FragmentFriend friend;

    public static FragmentFriend getInstance() {
        if (friend == null) {
            friend = new FragmentFriend();
        }
        return friend;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.chat)
    public void onViewClicked() {
        if (RongIM.getInstance()!=null){
            RongIM.getInstance().startPrivateChat(getActivity(),"10086","移动");
        }

    }
}
