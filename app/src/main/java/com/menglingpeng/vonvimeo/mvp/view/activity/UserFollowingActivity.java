package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserFollowingActivity extends BaseActivity {

    private Toolbar toolbar;
    private String type;
    private String userName;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_following;
    }

    @Override
    protected void initViews() {
        super.initViews();
        type = getIntent().getStringExtra(Constants.TYPE);
        userName = getIntent().getStringExtra(Constants.NAME);
        String title = new StringBuilder().append(userName).append(getString(R.string.s)).append(getString(R.string
                .following)).toString();
        toolbar = (Toolbar) findViewById(R.id.user_following_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (type.equals(Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER)) {
            replaceFragment(RecyclerFragment.newInstance(type));
        } else {
            replaceFragment(RecyclerFragment.newInstance(getIntent().getStringExtra(Constants.ID), Constants
                    .REQUEST_LIST_FOLLOWING_FOR_A_USER));
        }
    }
}
