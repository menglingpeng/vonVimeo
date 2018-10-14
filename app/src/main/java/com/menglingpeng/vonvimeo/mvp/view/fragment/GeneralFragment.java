package com.menglingpeng.vonvimeo.mvp.view.fragment;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

public class GeneralFragment extends BaseFragment {

    private EditText titleEt;
    private EditText descEt;
    private ImageView thumb1Iv;
    private ImageView thumb2Iv;
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
    private Video video;
    private String privacy;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_general;
    }

    @Override
    protected void initView() {

        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        titleEt = (EditText)rootView.findViewById(R.id.general_title_et);
        thumb1Iv = (ImageView)rootView.findViewById(R.id.general_thumb_1_iv);
        thumb2Iv = (ImageView)rootView.findViewById(R.id.general_thumb_2_iv);
        descEt = (EditText)rootView.findViewById(R.id.general_desc_et);
        watchRg = (RadioGroup)rootView.findViewById(R.id.general_privacy_watch_settings_rg);
        anyoneWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_anyone_rb);
        iWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_i_rb);
        followWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_follow_rb);
        chooseWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_choose_rb);
        passwordWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_with_password_rb);
        commentRg = (RadioGroup)rootView.findViewById(R.id.general_privacy_comment_settings_rg);
        anyoneCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_anyone_rb);
        noOneCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_no_one_rb);
        followCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_only_follow_rb);
    }

    @Override
    protected void initData() {

        titleEt.setText(video.getName());
        descEt.setText(video.getDescription());
        ImageLoader.load(this, video.getPictures().getUri(), thumb1Iv, false);
        thumb1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        watchRg.check(R.id.general_privacy_watch_settings_anyone_rb);
        watchRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.upload_privacy_settings_anyone_rb:
                        anyoneWatchRb.setText(getString(R.sting.general_privacy_watch_settings_anyone_rb_text));
                        privacy = Constants.PRIVACY_ANYONE;

                        break;
                    case R.upload_privacy_settings_only_i_rb:
                        iWatchRb.setText((R.sting.general_privacy_watch_settings_only_i_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I;
                        break;
                    case R.upload_privacy_settings_only_follow_rb:
                        followWatchRb.setText((R.sting.general_privacy_watch_settings_only_follow_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    case R.upload_privacy_settings_only_choose_rb:
                        chooseWatchRb.setText((R.sting.general_privacy_watch_settings_only_choose_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_CHOOSE;
                        break;
                    case R.id.upload_privacy_settings_only_with_password_rb:
                        passwordWatchRb.setText((R.sting.general_privacy_watch_settings_only_with_password_rb_text));
                        privacy = Constants.PRIVACY_WITH_A_PASSWORD;
                        break;
                    default:
                        break;
                }
            }
        });
        commentRg.check(R.id.general_privacy_comment_settings_anyone_rb);
        commentRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.upload_privacy_settings_anyone_rb:
                        anyoneCommentRb.setText(getString(R.sting.general_privacy_comment_settings_anyone_rb_text));
                        privacy = Constants.PRIVACY_ANYONE;

                        break;
                    case R.upload_privacy_settings_only_i_rb:
                        noOneCommentRb.setText((R.sting.general_privacy_comment_settings_no_one_rb));
                        privacy = Constants.PRIVACY_NO_ONE;
                        break;
                    case R.upload_privacy_settings_only_follow_rb:
                        followCommentRb.setText((R.sting.general_privacy_comment_settings_only_follow_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
