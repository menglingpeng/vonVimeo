package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class BillingSettingsFragment extends BaseFragment {

    private User user;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_billing_settings;
    }

    @Override
    protected void initView() {
        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();

    }

    @Override
    protected void initData() {

    }
}
