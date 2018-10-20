package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
    private String controlsSettingsType;
    private Switch likeSwitch;
    private Switch watchLaterSwitch;
    private Switch shareSwitch;
    private Switch embedSwitch;
    private String actionsSettingsType;
    private Switch profilePictureSwitch;
    private Switch titleSwitch;
    private Switch bylineSwitch;
    private Switch userDecideSwitch;
    private ImageView userDecideSwitchInfoIv;
    private String yourDetailsSettingsType;
    private Switch customColorSwitch;
    private Switch showVimeoLogoSwitch;
    private Switch displayCustomLogeSwitch;
    private String customizationSettingsType;
    private Button customLogoUpgradeBt;



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
        likeSwitch = (Switch)rootView.findViewById(R.id.embed_actions_like_settings_switch);
        watchLaterSwitch = (Switch)rootView.findViewById(R.id.embed_actions_watch_later_settings_switch);
        shareSwitch = (Switch)rootView.findViewById(R.id.embed_actions_share_settings_switch);
        embedSwitch = (Switch)rootView.findViewById(R.id.embed_actions_embed_settings_switch);
        profilePictureSwitch = (Switch)rootView.findViewById(R.id.embed_your_details_profile_picture_settings_switch);
        titleSwitch = (Switch)rootView.findViewById(R.id.embed_your_details_title_settings_switch);
        bylineSwitch = (Switch)rootView.findViewById(R.id.embed_your_details_byline_settings_switch);
        userDecideSwitch = (Switch)rootView.findViewById(R.id.embed_your_details_user_decide_settings_switch);
        userDecideSwitchInfoIv = (ImageView)rootView.findViewById(R.id.
                embed_your_details_users_decide_settings_switch_info_tv);
        customColorSwitch = (Switch)rootView.findViewById(R.id.embed_customization_custom_color_settings_switch);
        showVimeoLogoSwitch = (Switch)rootView.findViewById(R.id.embed_customization_show_vimeo_logo_settings_switch);
        displayCustomLogeSwitch = (Switch)rootView.findViewById(R.id.
                embed_customization_display_custom_logo_settings_switch);
        customLogoUpgradeBt = (Button)rootView.findViewById(R.id.
                embed_customization_display_custom_logo_settings_upgrade_bt);
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
            case R.id.embed_customization_display_custom_logo_settings_upgrade_bt:
                break;
            case R.id.embed_your_details_users_decide_settings_switch_info_tv:
                showUserDecideInfoDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.embed_controls_playbar_settings_switch:
                controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_PLAYBAR;
                break;
            case R.id.embed_controls_volume_settings_switch:
                controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_VOLUME;
                break;
            case R.id.embed_controls_speed_settings_switch:
                controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_SPEED;
                break;
            case R.id.embed_controls_fullscreen_settings_switch:
                controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_FULLSCREEN;
                break;
            case R.id.embed_actions_like_settings_switch:
                actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_LIKE;
                break;
            case R.id.embed_actions_watch_later_settings_switch:
                actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_WATCHLATER;
                break;
            case R.id.embed_actions_share_settings_switch:
                actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_SHARE;
                break;
            case R.id.embed_actions_embed_settings_switch:
                actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_EMBED;
                break;
            case R.id.embed_your_details_profile_picture_settings_switch:
                yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_PROFILE_PICTURE;
                break;
            case R.id.embed_your_details_title_settings_switch:
                yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_TITLE;
                break;
            case R.id.embed_your_details_byline_settings_switch:
                yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_BYLINE;
                break;
            case R.id.embed_your_details_user_decide_settings_switch:
                yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_USERS_DECIDE;
                break;
            case R.id.embed_customization_custom_color_settings_switch:
                customizationSettingsType = Constants.EMBED_CUSTOMIZATION_SETTINGS_TYPE_CUSTOM_COLOR;
                break;
            case R.id.embed_customization_display_custom_logo_settings_switch:
                customizationSettingsType = Constants.EMBED_CUSTOMIZATION_SETTINGS_TYPE_DISPLAY_CUSTOM_LOGO;
                break;
            case R.id.embed_customization_show_vimeo_logo_settings_switch:
                customizationSettingsType = Constants.EMBED_CUSTOMIZATION_SETTINGS_TYPE_SHOW_VIMEO_LOGO;
                break;
            default:
                break;
        }
    }

    private void showUserDecideInfoDialog(){

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.dialog_user_decide_switch_info_msg);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

}
