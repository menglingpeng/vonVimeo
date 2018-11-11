package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class NotificationsSettingsFragment extends BaseFragment {

    private User user;
    private Context context;
    private ListView listView;
    private CheckBox sendNotificationsCb;

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
    }
}
