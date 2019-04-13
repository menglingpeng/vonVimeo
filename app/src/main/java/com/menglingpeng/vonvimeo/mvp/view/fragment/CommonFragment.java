package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class CommonFragment extends BaseFragment {

    private String type;
    private Context context;

    public static CommonFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        CommonFragment fragment = new CommonFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initLayoutId() {
        type = getArguments().get(Constants.TYPE).toString();
        context = getActivity().getApplicationContext();
        switch (type){
            case Constants.COMMON_FRAGMENT_FEATURES:
                layoutId = R.layout.fragment_features;
                break;
            case Constants.COMMON_FRAGMENT_PRICING:
                layoutId = R.layout.fragment_pricing;
                break;
            default:
                break;
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


}
