package com.suhu.android.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.suhu.android.R;
import com.suhu.android.adapter.holder.BaseViewHolder;
import com.suhu.android.adapter.holder.ItemDecoration;
import com.suhu.android.bean.Head;
import com.suhu.android.listener.OnMixClickListener;

import java.util.List;

/**
 * @author suhu
 * @data 2017/12/8.
 * @description
 */

public class HeadViewHolder extends BaseViewHolder<Head>{
    private RecyclerView recyclerView;
    private List<Head.Bean> list;
    private HeadBeanAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    public HeadViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Head model, int position, Context context,OnMixClickListener clickListener) {
        list = model.getListBean();
        if (recyclerView==null){
            recyclerView = (RecyclerView) getView(R.id.recycler_view);
            linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(new ItemDecoration());
            adapter = new HeadBeanAdapter(list,context);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setList(list);
        }


    }
}
