package com.suhu.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suhu.android.R;
import com.suhu.android.adapter.FriendAdapter;
import com.suhu.android.application.User;
import com.suhu.android.db.FriendModel;
import com.suhu.android.db.utils.TabConfig;
import com.suhu.android.db.utils.TableManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description
 */

public class FragmentFriend extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private Unbinder unbinder;
    private List<FriendModel> models;
    private FriendAdapter adapter;
    private boolean isClick = false;
    private TableManager manager;


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
        initView();
        addListener();
        return view;
    }


    private void initView() {
        if (RongIM.getInstance() != null) {
            isClick = true;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        manager = new TableManager();
        models = manager.queryAll(TabConfig.Friend.TAB_NAME, FriendModel.class);
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getUserId().equals(User.getInstance().getUserId())){
                models.remove(models.get(i));
            }
        }
        adapter = new FriendAdapter(getActivity(), models);
        recyclerView.setAdapter(adapter);

        refresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void addListener() {
        adapter.setOnItemClickListener(new FriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isClick) {
                    FriendModel model = models.get(position);
                    RongIM.getInstance().startPrivateChat(getActivity(), model.getUserId(), model.getName());
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                FriendModel model = models.get(position);
                manager.delete(TabConfig.Friend.TAB_NAME, TabConfig.Friend.USER_ID, model.getUserId());
                models.remove(model);
                adapter.notifyItemRemoved(position);
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                models.clear();
//                models.addAll(manager.queryAll(TabConfig.Friend.TAB_NAME, FriendModel.class));
//                adapter.notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
