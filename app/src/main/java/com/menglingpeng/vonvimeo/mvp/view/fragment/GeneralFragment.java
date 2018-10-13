package com.menglingpeng.vonvimeo.mvp.view.fragment;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.menglingpeng.vonvimeo.base.BaseFragment;

public class GeneralFragment extends BaseFragment {

    private EditText titleEt;
    private EditText descEt;
    private ImageView thumb1Iv;
    private ImageView thumb2Iv;
    private RadioGroup watchRg;
    private RadioGroup commentRg;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_general;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
