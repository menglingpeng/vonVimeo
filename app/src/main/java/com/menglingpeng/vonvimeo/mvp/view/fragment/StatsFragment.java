package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;

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
            default:
                break;
        }
    }
}
