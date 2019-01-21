package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;

public class VimeoOnDemandPageBackgroudDetailActivity extends BaseActivity {

    private String title;
    private Context context;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private String sortType;
    private TextView seasonTitleTv;
    private TextView regionTitleTv;
    private TextView posterTitleTv;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_vimeo_on_demand_page_backgroud_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.backgroud_detail_cdl);
        toolbar = (Toolbar) findViewById(R.id.genre_detail_tb);
        progressBar = (ProgressBar)findViewById(R.id.backgroud_detail_pb);
        seasonTitleTv = (TextView) findViewById(R.id.season_title_tv);
        regionTitleTv = (TextView) findViewById(R.id.region_title_tv);
        posterTitleTv = (TextView) findViewById(R.id.poster_title_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
