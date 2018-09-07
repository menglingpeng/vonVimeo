package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;

public class WatchLaterVideosActivity extends BaseActivity {

    private Toolbar toolbar;
    private String title;
    private TextView likesCountTv;
    private TextView collectionCountTv;
    private TextView followingCountTv;
    private User user;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_watch_later_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title = new StringBuilder().append(getIntent().getStringExtra(Constants.NAME)).append(getString(R.string.s))
                .append(getString(R.string.likes)).toString();
        toolbar = (Toolbar) findViewById(R.id.user_watch_later_tb);
        likesCountTv = (TextView)findViewById(R.id.likes_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.collections_count_tv);
        followingCountTv = (TextView)findViewById(R.id.following_count_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        likesCountTv.setText(user.getMetadata().getInteractions().);
    }
}
