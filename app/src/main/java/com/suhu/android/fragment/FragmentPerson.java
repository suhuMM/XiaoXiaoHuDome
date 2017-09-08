package com.suhu.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suhu.android.R;
import com.suhu.android.activity.ImageSelectActivity;
import com.suhu.android.activity.UpdateActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class FragmentPerson extends Fragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



    @OnClick({R.id.image_select, R.id.update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_select:
                startActivity(new Intent(getActivity(), ImageSelectActivity.class));
                break;
            case R.id.update:
                startActivity(new Intent(getActivity(), UpdateActivity.class));
                break;
        }
    }
}
