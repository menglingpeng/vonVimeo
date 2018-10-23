package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;

public class DistributionFragment extends BaseFragment implements View.OnClickListener{

    private Context context;
    private Video video;
    private User user;
    private Button socialUpgradeBt;
    private Button facebookConnectBt;
    private Button youtubeConnectBt;
    private Button twiterConnectBt;
    private ImageView createTagIv;
    private ImageView createCategoriesIv;
    private ImageView createCreditsIv;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_distribution;
    }

    @Override
    protected void initView() {

        context = getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        user = video.getUser();
        socialUpgradeBt = (Button)rootView.findViewById(R.id.distribution_social_settings_title_upgrade_bt);
        facebookConnectBt = (Button)rootView.findViewById(R.id.distribution_social_settings_facebook_connect_bt);
        youtubeConnectBt = (Button)rootView.findViewById(R.id.distribution_social_settings_youtube_connect_bt);
        twiterConnectBt = (Button)rootView.findViewById(R.id.distribution_social_settings_twiter_connect_bt);
        createTagIv = (ImageView)rootView.findViewById(R.id.distribution_discovey_settings_tags_create_iv);
        createCategoriesIv = (ImageView)rootView.findViewById(R.id.distribution_discovey_settings_categories_create_iv);
        createCreditsIv = (ImageView)rootView.findViewById(R.id.distribution_discovey_settings_credits_create_iv);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.distribution_social_settings_title_upgrade_bt:
                break;
            case R.id.distribution_social_settings_facebook_connect_bt:
                break;
            case R.id.distribution_social_settings_youtube_connect_bt:
                break;
            case R.id.distribution_social_settings_twiter_connect_bt:
                break;
            case R.id.distribution_discovey_settings_tags_create_iv:
                break;
            case R.id.distribution_discovey_settings_categories_create_iv:
                break;
            case R.id.distribution_discovey_settings_tags_credits_iv:
                break;
            default:
                break;
        }
    }
}
