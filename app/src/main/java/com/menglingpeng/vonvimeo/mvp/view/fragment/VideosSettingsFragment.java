package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class VideosSettingsFragment extends BaseFragment implements Switch.OnCheckedChangeListener{

    private User user;
    private Context context;
    private RadioGroup contentRatingRg;
    private RadioButton noRatingRb;
    private RadioButton allAudiencesRb;
    private RadioButton matureRb;
    private Button contentRatingSaveBt;
    private CheckBox videoPreferencesCb;
    private RadioGroup watchRg;
    private RadioButton anyoneWatchRb;
    private RadioButton iWatchRb;
    private RadioButton followWatchRb;
    private RadioButton chooseWatchRb;
    private RadioButton passwordWatchRb;
    private RadioGroup commentRg;
    private RadioButton anyoneCommentRb;
    private RadioButton noOneCommentRb;
    private RadioButton followCommentRb;
    private RadioGroup embedRg;
    private RadioButton anywhereRb;
    private RadioButton nowhereRb;
    private String watchPrivacy;
    private String commentPrivacy;
    private String embedPrivacy;
    private CheckBox downloadCb;
    private CheckBox addCollectionsCh;
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
    private String yourDetailsSettingsType;
    private ImageView userDecideSwitchInfoIv;
    private Switch customColorSwitch;
    private Switch showVimeoLogoSwitch;
    private Switch displayCustomLogeSwitch;
    private String customizationSettingsType;
    private Button customLogoUpgradeBt;
    private Button customLogosUploadBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_videos_settings;
    }

    @Override
    protected void initView() {
        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        contentRatingRg = (RadioGroup)rootView.findViewById(R.id.videos_settings_content_rating_default_rg);
        noRatingRb = (RadioButton)rootView.findViewById(R.id.videos_settings_content_rating_default_no_rating_rb);
        allAudiencesRb = (RadioButton)rootView.findViewById(R.id.
                videos_settings_content_rating_default_all_audiences_rb);
        matureRb = (RadioButton)rootView.findViewById(R.id.videos_settings_content_rating_default_mature_rb);
        contentRatingSaveBt = (Button) rootView.findViewById(R.id.videos_settings_content_rating_default_save_bt);
        videoPreferencesCb = (CheckBox)rootView.findViewById(R.id.videos_settings_video_preferences_cb);
        watchRg = (RadioGroup)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_rg);
        anyoneWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_anyone_rb);
        iWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_i_rb);
        followWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_follow_rb);
        chooseWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_choose_rb);
        passwordWatchRb = (RadioButton)rootView.findViewById(R.id.
                videos_settings_privacy_watch_vodeos_only_with_password_rb);
        commentRg = (RadioGroup)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_rg);
        anyoneCommentRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_anyone_rb);
        noOneCommentRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_no_one_rb);
        followCommentRb = (RadioButton)rootView.findViewById(R.id.
                videos_settings_privacy_comment_on_vodeos_only_follow_rb);
        embedRg = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_embed_vodeos_rg);
        anywhereRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_embed_vodeos_anywhere_rb);
        nowhereRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_embed_vodeos_nowhere_rb);
        playbarSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_controls_playbar_settings_switch);
        volumeSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_controls_volume_settings_switch);
        speedSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_controls_speed_settings_switch);
        fullscreenSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_controls_fullscreen_settings_switch);
        likeSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_actions_like_settings_switch);
        watchLaterSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_actions_watch_later_settings_switch);
        shareSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_actions_share_settings_switch);
        embedSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_actions_embed_settings_switch);
        profilePictureSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_your_details_profile_picture_settings_switch);
        titleSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_your_details_title_settings_switch);
        bylineSwitch = (Switch)rootView.findViewById(R.id.videos_settings_embed_presets_your_details_byline_settings_switch);
        userDecideSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_your_details_user_decide_settings_switch);
        userDecideSwitchInfoIv = (ImageView)rootView.findViewById(R.id.
                videos_settings_embed_presets_your_details_users_decide_settings_switch_info_tv);
        customColorSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_customization_custom_color_settings_switch);
        showVimeoLogoSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_customization_show_vimeo_logo_settings_switch);
        displayCustomLogeSwitch = (Switch)rootView.findViewById(R.id.
                videos_settings_embed_presets_customization_display_custom_logo_settings_switch);
        customLogoUpgradeBt = (Button)rootView.findViewById(R.id.
                videos_settings_embed_presets_customization_display_custom_logo_settings_upgrade_bt);
        customLogosUploadBt = (Button)rootView.findViewById(R.id.videos_settings_player_logos_upload_bt);
    }

    @Override
    protected void initData() {
        contentRatingRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;

                }
            }
        });
        contentRatingSaveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        videoPreferencesCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        watchRg.check(R.id.videos_settings_privacy_watch_vodeos_anyone_rb);
        watchRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case 0:
                        anyoneWatchRb.setText(getString(R.sting.general_privacy_watch_settings_anyone_rb_text));
                        watchPrivacy = Constants.PRIVACY_ANYONE;

                        break;
                    case 1:
                        iWatchRb.setText((R.sting.general_privacy_watch_settings_only_i_rb_text));
                        watchPrivacy = Constants.PRIVACY_ONLY_I;
                        break;
                    case 2:
                        followWatchRb.setText((R.sting.general_privacy_watch_settings_only_follow_rb_text));
                        watchPrivacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    case 3:
                        chooseWatchRb.setText((R.sting.general_privacy_watch_settings_only_choose_rb_text));
                        watchPrivacy = Constants.PRIVACY_ONLY_I_CHOOSE;
                        break;
                    case 4:
                        passwordWatchRb.setText((R.sting.general_privacy_watch_settings_only_with_password_rb_text));
                        watchPrivacy = Constants.PRIVACY_WITH_A_PASSWORD;
                        break;
                    default:
                        break;
                }
            }
        });

        commentRg.check(R.id.videos_settings_privacy_comment_on_vodeos_anyone_rb);
        commentRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case 0:
                        anyoneCommentRb.setText(getString(R.sting.general_privacy_comment_settings_anyone_rb_text));
                        commentPrivacy= Constants.PRIVACY_ANYONE;

                        break;
                    case 1:
                        noOneCommentRb.setText((R.sting.general_privacy_comment_settings_no_one_rb));
                        commentPrivacy = Constants.PRIVACY_NO_ONE;
                        break;
                    case 2:
                        followCommentRb.setText((R.sting.general_privacy_comment_settings_only_follow_rb_text));
                        commentPrivacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    default:
                        break;
                }
            }
        });
        embedRg.check(R.id.videos_settings_privacy_embed_vodeos_anywhere_rb);
        embedRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case 0:
                        anywhereRb.setText(getString(R.sting.general_privacy_embed_settings_anywhere_rb_text));
                        embedPrivacy= Constants.PRIVACY_ANYONE;

                        break;
                    case 1:
                        nowhereRb.setText((R.sting.general_privacy_embed_settings_nowhere_rb_text));
                        embedPrivacy = Constants.PRIVACY_NO_ONE;
                        break;
                    default:
                        break;
                }
            }
        });
        customLogosUploadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.videos_settings_embed_presets_controls_playbar_settings_switch:
                if(b) {
                    controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_PLAYBAR;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_controls_volume_settings_switch:
                if(b) {
                    controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_VOLUME;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_controls_speed_settings_switch:
                if(b) {
                    controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_SPEED;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_controls_fullscreen_settings_switch:
                if(b) {
                    controlsSettingsType = Constants.EMBED_CONTROLS_SETTINGS_TYPE_FULLSCREEN;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_actions_like_settings_switch:
                if(b) {
                    actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_LIKE;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_actions_watch_later_settings_switch:
                if(b){
                    actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_WATCHLATER;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_actions_share_settings_switch:
                if(b) {
                    actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_SHARE;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_actions_embed_settings_switch:
                if(b) {
                    actionsSettingsType = Constants.EMBED_ACTIONS_SETTINGS_TYPE_EMBED;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_your_details_profile_picture_settings_switch:
                if(b) {
                    yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_PROFILE_PICTURE;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_your_details_title_settings_switch:
                if(b) {
                    yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_TITLE;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_your_details_byline_settings_switch:
                if(b) {
                    yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_BYLINE;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_your_details_user_decide_settings_switch:
                if(b) {
                    yourDetailsSettingsType = Constants.EMBED_YOUR_DETAILS_SETTINGS_TYPE_USERS_DECIDE;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_customization_custom_color_settings_switch:
                if(b) {
                    customizationSettingsType = Constants.EMBED_CUSTOMIZATION_SETTINGS_TYPE_CUSTOM_COLOR;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_customization_display_custom_logo_settings_switch:
                if(b) {
                    customizationSettingsType = Constants.EMBED_CUSTOMIZATION_SETTINGS_TYPE_DISPLAY_CUSTOM_LOGO;
                }else {

                }
                break;
            case R.id.videos_settings_embed_presets_customization_show_vimeo_logo_settings_switch:
                if(b) {
                    customizationSettingsType = Constants.EMBED_CUSTOMIZATION_SETTINGS_TYPE_SHOW_VIMEO_LOGO;
                }else {

                }
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
