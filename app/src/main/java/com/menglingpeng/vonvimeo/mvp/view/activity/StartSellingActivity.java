package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;

public class StartSellingActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView demandDescTv;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_start_selling;
    }

    @Override
    protected void initViews() {
        super.initViews();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.start_selling_cdl);
        toolbar = (Toolbar) findViewById(R.id.start_selling_tb);
        demandDescTv = (TextView)findViewById(R.id.start_selling_desc_tv);
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
