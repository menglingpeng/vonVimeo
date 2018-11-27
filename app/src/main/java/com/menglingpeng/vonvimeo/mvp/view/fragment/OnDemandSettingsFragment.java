package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class OnDemandSettingsFragment extends BaseFragment implements RecyclerView{

    private User user;
    private Context context;
    private String userId;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_on_demand_settings;
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

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
