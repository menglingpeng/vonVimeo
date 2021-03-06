package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.view.activity.UpgradeActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

public class CollaborationFragment extends BaseFragment implements Switch.OnCheckedChangeListener, View.OnClickListener{

    private Context context;
    private Video video;
    private User user;
    private Button reviewToolsUpgradeBt;
    private Switch privateReviewPageSwitch;
    private Switch allowNotesSwitch;
    private Switch vimeoLogoSwitch;
    private String reviewToolsType;
    private Button versionsUpgradeBt;
    private TextView videoNameTv;
    private TextView videoSizeTv;
    private TextView videoUplodedTimeTv;
    private TextView videoDurationTv;
    private Button replaceVideoBt;
    private ImageView avatarIv;
    private TextView uploadedTimeTv;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_collaboration;
    }

    @Override
    protected void initView() {

        context = getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        user = video.getUser();
        reviewToolsUpgradeBt = (Button)rootView.findViewById(R.id.collaboration_review_tools_upgrade_bt);
        privateReviewPageSwitch = (Switch)rootView.findViewById(R.id.
                collaboration_review_tools_enable_private_review_page_settings_switch);
        allowNotesSwitch = (Switch)rootView.findViewById(R.id.collaboration_review_tools_allow_notes_settings_switch);
        vimeoLogoSwitch = (Switch)rootView.findViewById(R.id.
                collaboration_review_tools_display_vimeo_logo_settings_switch);
        versionsUpgradeBt = (Button)rootView.findViewById(R.id.collaboration_versions_settings_title_upgrade_bt);
        videoNameTv = (TextView)rootView.findViewById(R.id.collaboration_versions_settings_video_name_tv);
        videoSizeTv = (TextView)rootView.findViewById(R.id.collaboration_versions_settings_video_size_tv);
        videoUplodedTimeTv = (TextView)rootView.findViewById(R.id.collaboration_versions_settings_video_uploaded_time_tv);
        videoDurationTv = (TextView)rootView.findViewById(R.id.collaboration_versions_settings_video_duration_tv);
        replaceVideoBt = (Button)rootView.findViewById(R.id.collaboration_versions_settings_replace_bt);
        avatarIv = (ImageView)rootView.findViewById(R.id.collaboration_history_avatar_iv);
        uploadedTimeTv = (TextView)rootView.findViewById(R.id.collaboration_history_video_uploaded_time_tv)
    }

    @Override
    protected void initData() {

        videoNameTv.setText(video.getName());
        videoDurationTv.setText(video.getDuration());
        videoUplodedTimeTv.setText(video.getCreated_time());
        videoSizeTv.setText(video.get);
        ImageLoader.loadCricleImage(this, video.getPictures().getUri(), avatarIv);
        uploadedTimeTv.setText(video.getCreated_time());
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.collaboration_review_tools_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.collaboration_versions_settings_title_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.collaboration_versions_settings_replace_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.collaboration_review_tools_enable_private_review_page_settings_switch:
                reviewToolsType = Constants.EMBED_REVIEW_TOOLS_SETTINGS_TYPE_PRIVATE_REVIEW_PAGE;
                break;
            case R.id.collaboration_review_tools_allow_notes_settings_switch:
                reviewToolsType = Constants.EMBED_REVIEW_TOOLS_SETTINGS_TYPE_ALLOW_NOTES;
                break;
            case R.id.collaboration_review_tools_display_vimeo_logo_settings_switch:
                reviewToolsType = Constants.EMBED_REVIEW_TOOLS_SETTINGS_TYPE_DISPLAY_VIMEO_LOGO;
                break;
        }
    }
}
