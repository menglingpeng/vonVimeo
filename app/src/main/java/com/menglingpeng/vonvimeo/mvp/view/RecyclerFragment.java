package com.menglingpeng.vonvimeo.mvp.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.adapter.RecyclerAdapter;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.utils.Constants;

public class RecyclerFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerPresenter presenter;
    private RecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String type;

    public static RecyclerFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static RecyclerFragment newInstance(String id, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        bundle.putString(Constants.ID, id);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static RecyclerFragment newInstance(User user, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        bundle.putSerializable(Constants.USER, user);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
        type = getArguments().get(Constants.TYPE).toString();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void showRefreshProgress(final boolean refreshState) {
        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(refreshState);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
