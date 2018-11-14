package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class ConnectedAppsSettingsFragment extends BaseFragment implements View.OnClickListener{

    private User user;
    private Context context;
    private ListView listView;
    private Button connectToFacebookBt;
    private Button connectToYouTubekBt;
    private Button connectToTwitterBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_connected_apps_settings;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        listView = (ListView)rootView.findViewById(R.id.connected_apps_settings_to_other_accounts_lv);
        connectToFacebookBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_facebook_bt);
        connectToYouTubekBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_youtube_bt);
        connectToTwitterBt = (Button)rootView.findViewById(R.id.connected_apps_settings_connect_to_twitter_bt);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.connected_apps_settings_connect_to_facebook_bt:
                break;
            case R.id.connected_apps_settings_connect_to_youtube_bt:
                break;
            case R.id.connected_apps_settings_connect_to_twitter_bt:
                break;
            default:
                break;
        }
    }
}
