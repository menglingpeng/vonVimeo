package com.menglingpeng.vonvimeo.mvp.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;

public class UserProjectActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = android.R.layout.activity_user_project;
    }

    @Override
    protected void initViews() {
        super.initViews();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.auth_user_project_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_projects_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_projects_tb);
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
