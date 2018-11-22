package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class VideosSettingsFragment extends BaseFragment {

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
        allAudiencesRb = (RadioButton)rootView.findViewById(R.id.videos_settings_content_rating_default_all_audiences_rb);
        matureRb = (RadioButton)rootView.findViewById(R.id.videos_settings_content_rating_default_mature_rb);
        contentRatingSaveBt = (Button) rootView.findViewById(R.id.videos_settings_content_rating_default_save_bt);
        videoPreferencesCb = (CheckBox)rootView.findViewById(R.id.videos_settings_video_preferences_cb);
        watchRg = (RadioGroup)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_rg);
        anyoneWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_anyone_rb);
        iWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_i_rb);
        followWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_follow_rb);
        chooseWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_choose_rb);
        passwordWatchRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_watch_vodeos_only_with_password_rb);
        commentRg = (RadioGroup)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_rg);
        anyoneCommentRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_anyone_rb);
        noOneCommentRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_no_one_rb);
        followCommentRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_comment_on_vodeos_only_follow_rb);
        embedRg = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_embed_vodeos_rg);
        anywhereRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_embed_vodeos_anywhere_rb);
        nowhereRb = (RadioButton)rootView.findViewById(R.id.videos_settings_privacy_embed_vodeos_nowhere_rb);
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
    }
}
