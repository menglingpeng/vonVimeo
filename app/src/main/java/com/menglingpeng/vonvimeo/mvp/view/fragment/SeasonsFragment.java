package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;

import com.menglingpeng.vonvimeo.base.BaseFragment;

public class SeasonsFragment extends BaseFragment {

    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
    }

    @Override
    protected void initView() {
        context = getContext();
    }

    @Override
    protected void initData() {

    }
}
