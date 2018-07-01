package com.menglingpeng.vonvimeo.mvp.view;

import android.os.Bundle;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class RecyclerFragment extends BaseFragment {

    public static RecyclerFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initLayoutId() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
