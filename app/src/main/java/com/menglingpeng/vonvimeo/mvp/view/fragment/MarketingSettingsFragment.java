package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class MarketingSettingsFragment extends BaseFragment implements View.OnClickListener{

    private User user;
    private Context context;
    private Button connectToMailChimpBt;
    private Button connectToConstantContactBt;
    private Button connectToCampaignMonitorBt;
    private Button connectToInfusionsoftBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_marketing_settings;
    }

    @Override
    protected void initView() {
        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        connectToMailChimpBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_mailchimp_bt);
        connectToCampaignMonitorBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_campaign_monitor_bt);
        connectToConstantContactBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_constant_contact_bt);
        connectToInfusionsoftBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_infusionsoft_bt);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
