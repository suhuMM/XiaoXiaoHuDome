package com.suhu.android.base.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wnw on 16-5-26.
 */
public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;

    //当前页，从0开始
    private int currentPage = 0;

    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = false;

    //是否还有跟多的数据
    private boolean hasMoreItems = true;
    private int mLoadMoreItems = 10;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (totalItemCount < mLoadMoreItems) {
            setHasMoreItems(false);
            mLoadMoreItems += 10;
        } else if (hasMoreItems && !recyclerView.canScrollVertically(1)) {
            currentPage++;
            onLoadMore(currentPage);
        }
    }

    private void setHasMoreItems(boolean b) {
        hasMoreItems = b;
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     */
    public abstract void onLoadMore(int currentPage);
}
