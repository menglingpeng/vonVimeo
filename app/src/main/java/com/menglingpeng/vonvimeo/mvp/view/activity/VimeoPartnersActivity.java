package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;

public class VimeoPartnersActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_vimeo_partners;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.vimeo_partners_cdl);
        toolbar = (Toolbar) findViewById(R.id.vimeo_partners_tb);
    }

}
