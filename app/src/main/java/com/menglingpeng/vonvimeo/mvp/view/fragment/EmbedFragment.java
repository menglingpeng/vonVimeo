package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;

public class EmbedFragment extends BaseFragment implements View.OnClickListener, Switch.OnCheckedChangeListener{

    private Context context;
    private Video video;
    private User user;
    private Button titleUpgradeBt;
    private Switch playbarSwitch;
    private Switch volumeSwitch;
    private Switch speedSwitch;
    private Switch fullscreenSwitch;
    private String controlsSetingsType;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_embed;
    }

    @Override
    protected void initView() {

        context = getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        user = video.getUser();
        titleUpgradeBt = (Button)rootView.findViewById(R.id.embed_title_upgrade_bt);
        playbarSwitch = (Switch)rootView.findViewById(R.id.embed_controls_playbar_settings_switch);
        volumeSwitch = (Switch)rootView.findViewById(R.id.embed_controls_volume_settings_switch);
        speedSwitch = (Switch)rootView.findViewById(R.id.embed_controls_speed_settings_switch);
        fullscreenSwitch = (Switch)rootView.findViewById(R.id.embed_controls_fullscreen_settings_switch);
    }

    @Override
    protected void initData() {
        if(user.getAccount().equals(Constants.ACCOUNT_BASIC)){
            titleUpgradeBt.setVisibility(Button.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.embed_title_upgrade_bt:
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.embed_controls_playbar_settings_switch:
                controlsSetingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_PLAYBAR;
                break;
            case R.id.embed_controls_volume_settings_switch:
                controlsSetingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_VOLUME;
                break;
            case R.id.embed_controls_speed_settings_switch:
                controlsSetingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_SPEED;
                break;
            case R.id.embed_controls_fullscreen_settings_switch:
                controlsSetingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_FULLSCREEN;
                break;
            default:
                break;
        }
    }
}
