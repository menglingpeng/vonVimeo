package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UpgradeSettingsFragment extends BaseFragment {

    private User user;
    private String userId;
    private Context context;
    private Button plusUpgradeBt;
    private TextView plusUpgradeTv;
    private Button proUpgradeBt;
    private Button BusinessUpgradeBt;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_upgrade;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        userId = IdStringUtil.getId(user.getUri());
    }

    @Override
    protected void initData() {

    }
}
