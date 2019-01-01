package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;

public class VimeoOndemandPagesActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView demandDescTv;
    private ProgressBar progressBar;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_vimeo_ondemand_pages;
    }

    @Override
    protected void initViews() {
        super.initViews();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.vimeo_demand_pages_cdl);
        toolbar = (Toolbar) findViewById(R.id.vimeo_demand_pages_tb);
        demandDescTv = (TextView)findViewById(R.id.vimeo_demand_pages_desc_tv);
        progressBar = (ProgressBar)findViewById(R.id.vimeo_demand_pages_pb);
        title = user.getName();
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

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
