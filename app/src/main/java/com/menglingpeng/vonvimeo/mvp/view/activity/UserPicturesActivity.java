package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserPicturesActivity extends BaseActivity {

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private String type;
    private String title;
    private static RecyclerFragment fragment;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_pictures;
    }

    @Override
    protected void initViews() {
        super.initViews();
        type = getIntent().getStringExtra(Constants.TYPE);
        title = new StringBuilder().append(getIntent().getStringExtra(Constants.NAME)).append(getString(R.string.s))
                .append(getString(R.string.likes)).toString();
        toolbar = (Toolbar) findViewById(R.id.user_pictures_tb);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.user_pictures_fab);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public static RecyclerFragment getFragment() {
        return fragment;
    }
}
