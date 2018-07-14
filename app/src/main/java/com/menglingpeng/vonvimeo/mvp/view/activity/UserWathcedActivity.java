package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserWathcedActivity extends BaseActivity {

    private Toolbar toolbar;
    private String userName;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_watched;
    }

    @Override
    protected void initViews() {
        super.initViews();
        userName = getIntent().getStringExtra(Constants.NAME);
        String title = new StringBuilder().append(userName).append(getString(R.string.s)).append(getString(R.string
                .watched_history)).toString();
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
        replaceFragment(RecyclerFragment.newInstance(getIntent().getStringExtra(Constants.ID), Constants
                    .REQUEST_LIST_ALL_VIDOES_THAT_A_USER_HAS_WATCHED));
        }

}
