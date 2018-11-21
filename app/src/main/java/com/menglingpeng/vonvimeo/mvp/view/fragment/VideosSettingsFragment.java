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
