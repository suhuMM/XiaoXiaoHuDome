package com.suhu.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description
 */

public class FragmentGroup extends Fragment{

    private static FragmentGroup group;
    public static FragmentGroup getInstance(){
        if (group == null){
            group = new FragmentGroup();
        }
        return group;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
