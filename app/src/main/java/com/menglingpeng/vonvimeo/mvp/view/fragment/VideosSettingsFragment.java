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
    private String watchPrivacy;
    private String commentPrivacy;
    private String embedPrivacy;
    private CheckBox downloadCb;
    private CheckBox addCollectionsCh;

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
    }
}
