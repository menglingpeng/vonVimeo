package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class StatsFragment extends BaseFragment implements View.OnClickListener{

    private Video video;
    private String videoId;
    private User user;
    private Context context;
    private Button titleUpgradeBt;
    private Button interactionUpgradeBt;
    private Button socialUpgradeBt;
    private ImageView playsInfoTv;
    private ImageView impressionsInfoTv;
    private ImageView playRateInfoTv;
    private ImageView playByURLInfoTv;
    private String info;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_stats;
    }

    @Override
    protected void initView() {

        context = getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        videoId = IdStringUtil.getId(video.getUri());
        user = video.getUser();
        titleUpgradeBt = (Button)rootView.findViewById(R.id.stats_title_upgrade_bt);
        interactionUpgradeBt = (Button)rootView.findViewById(R.id.stats_interaction_title_upgrade_bt);
        socialUpgradeBt = (Button)rootView.findViewById(R.id.stats_social_title_upgrade_bt);
        playsInfoTv = (ImageView)rootView.findViewById(R.id.stats_video_plays_info_iv);
        impressionsInfoTv = (ImageView)rootView.findViewById(R.id.stats_video_impressions_info_iv);
        playRateInfoTv = (ImageView)rootView.findViewById(R.id.stats_video_play_rate_info_iv);
        playByURLInfoTv = (ImageView)rootView.findViewById(R.id.stats_video_play_by_url_info_iv);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.stats_title_upgrade_bt:
                break;
            case R.id.stats_interaction_title_upgrade_bt:
                break;
            case R.id.stats_social_title_upgrade_bt:
                break;
            case R.id.stats_video_plays_info_iv:
                info = context.getString(R.string.stats_video_plays_info_text);
                showInfoDialog(info);
                break;
            case R.id.stats_video_impressions_info_iv:
                info = context.getString(R.string.stats_video_impressions_info_text);
                showInfoDialog(info);
                break;
            case R.id.stats_video_play_rate_info_iv:
                info = context.getString(R.string.stats_video_play_rate_info_text);
                showInfoDialog(info);
                break;
            case R.id.stats_video_play_by_url_info_iv:
                info = context.getString(R.string.stats_video_play_by_url_info_text);
                showInfoDialog(info);
                break;
            default:
                break;
        }
    }

    private void showInfoDialog(String info){

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(info);
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
