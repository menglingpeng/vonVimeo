package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.graphics.ColorSpace;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class NotificationsSettingsFragment extends BaseFragment {

    private User user;
    private Context context;
    private ListView listView;
    private CheckBox sendNotificationsCb;
    private RadioGroup demandTitleRg;
    private RadioGroup aTitleRg;
    private RadioGroup someoneTitleRg;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_notifications_settings;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        listView= (ListView) rootView.findViewById(R.id.notifications_settings_on_site_activity_lv);
        sendNotificationsCb = (CheckBox)rootView.findViewById(R.id.
                notifications_settings_on_site_activity_send_notifications_only_i_follow_cb);
        demandTitleRg = (RadioGroup)rootView.findViewById(R.id.
                notifications_settings_vimeo_on_demand_add_demand_title_rg);
        aTitleRg = (RadioGroup)rootView.findViewById(R.id.
                notifications_settings_vimeo_on_demand_add_a_title_rg);
        someoneTitleRg = (RadioGroup)rootView.findViewById(R.id.
                notifications_settings_vimeo_on_demand_someone_on_demand_title_rg);

    }

    @Override
    protected void initData() {
        sendNotificationsCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                }
                else {

                }
            }
        });
        demandTitleRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.notifications_settings_vimeo_on_demand_add_demand_title_yes_rb:
                        break;
                    case R.id.notifications_settings_vimeo_on_demand_add_demand_title_no_rb:
                        break;
                    default:
                }
            }
        });
        aTitleRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.notifications_settings_vimeo_on_demand_add_a_title_yes_rb:
                        break;
                    case R.id.notifications_settings_vimeo_on_demand_add_a_title_no_rb:
                        break;
                    default:
                }
            }
        });
        someoneTitleRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.notifications_settings_vimeo_on_demand_someone_on_demand_title_yes_rb:
                        break;
                    case R.id.notifications_settings_vimeo_on_demand_someone_on_demand_title_no_rb:
                        break;
                    default:
                }
            }
        });
    }
}
