package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class ViewingPreferencesSettingsFragment extends BaseFragment {

    private User user;
    private Context context;
    private Button saveBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_viewing_preferences_settings;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        saveBt = (Button)rootView.findViewById(R.id.view_preferences_settings_save_bt);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
