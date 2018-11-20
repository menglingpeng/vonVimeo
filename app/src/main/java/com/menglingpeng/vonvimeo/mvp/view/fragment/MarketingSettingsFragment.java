package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private Button downloadBt;
    private EditText trackingCodeEt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_marketing_settings;
    }

    @Override
    protected void initView() {
        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        connectToMailChimpBt = (Button)rootView.findViewById(R.id.marketing_settings_connect_to_mailchimp_bt);
        connectToCampaignMonitorBt = (Button)rootView.findViewById(R.id.marketing_settings_connect_to_campaign_monitor_bt);
        connectToConstantContactBt = (Button)rootView.findViewById(R.id.marketing_settings_connect_to_constant_contact_bt);
        connectToInfusionsoftBt = (Button)rootView.findViewById(R.id.marketing_settings_connect_to_infusionsoft_bt);
        downloadBt = (Button)rootView.findViewById(R.id.marketing_settings_download_email_addresses_download_bt);
        trackingCodeEt = (EditText)rootView.findViewById(R.id.marketing_settings_google_analytics_tracking_code_et);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.marketing_settings_connect_to_mailchimp_bt:
                connectToMailChimp();
                break;
            case R.id.marketing_settings_connect_to_campaign_monitor_bt:
                connectToCampaignMonitor();
                break;
            case R.id.marketing_settings_connect_to_constant_contact_bt:
                connectToConstantContact();
                break;
            case R.id.marketing_settings_connect_to_infusionsoft_bt:
                connectToInfusionsoft();
                break;
            case R.id.marketing_settings_download_email_addresses_download_bt:
                break;
            default:
                break;
        }
    }

    private void connectToMailChimp(){

    }

    private void connectToConstantContact(){

    }

    private void connectToCampaignMonitor(){

    }

    private void connectToInfusionsoft(){

    }
}
