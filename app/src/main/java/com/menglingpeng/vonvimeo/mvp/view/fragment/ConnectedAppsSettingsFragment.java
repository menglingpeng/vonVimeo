package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.widget.ListView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class ConnectedAppsSettingsFragment extends BaseFragment {

    private User user;
    private Context context;
    private ListView listView;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_connected_apps_settings;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        listView = (ListView)rootView.findViewById(R.id.connected_apps_settings_to_other_accounts_lv);
    }

    @Override
    protected void initData() {

    }
}
