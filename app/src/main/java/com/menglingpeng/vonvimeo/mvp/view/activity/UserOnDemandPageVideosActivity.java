package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.OnDemandPage;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserOnDemandPageVideosActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private ProgressBar progressBar;
    private OnDemandPage onDemandPage;
    private FloatingActionButton ftBt;
    private Context context;
    private String onDemandId;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_on_demand_page_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.on_demand_page_videos_cdl);
        toolbar = (Toolbar) findViewById(R.id.on_demand_page_videos_tb);
        progressBar = (ProgressBar)findViewById(R.id.on_demand_page_videos_pb);
        ftBt = (FloatingActionButton) findViewById(R.id.on_demand_page_videos_ftb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replaceFragment(RecyclerFragment.newInstance(Constants.ON_DEMAND_ID, onDemandId));
        ftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
