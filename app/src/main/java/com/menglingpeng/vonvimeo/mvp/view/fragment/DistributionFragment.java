package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.List;

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
    private RadioGroup contentRatingRg;
    private RadioButton allAudiencesRb;
    private RadioButton matureRb;
    private Switch recordedSwitch;


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
        contentRatingRg = (RadioGroup)rootView.findViewById(R.id.distribution_discovey_settings_content_rating_rg);
        allAudiencesRb = (RadioButton)rootView.findViewById(R.id.distribution_discovey_settings_content_rating_all_audiences_rb);
        matureRb = (RadioButton)rootView.findViewById(R.id.distribution_discovey_settings_content_rating_mature_rb);
        recordedSwitch = (Switch)rootView.findViewById(R.id.distribution_discovey_settings_recorded_switch);


    }

    @Override
    protected void initData() {

        contentRatingRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.distribution_discovey_settings_content_rating_all_audiences_rb:
                        break;
                    case R.id.distribution_discovey_settings_content_rating_mature_rb:
                        break;
                    default:
                        break;
                }
            }
        });
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
                showAddVideoToCategory();
                break;
            case R.id.distribution_discovey_settings_tags_credits_iv:
                break;
            default:
                break;
        }
    }

    private void showAddVideoToCategory(){

        ListView listView;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.create_an_album_di, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        listView = (ListView) dialogView.findViewById(R.id.categorites_lv);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog = builder.create();
        dialog.show();
    }
}
