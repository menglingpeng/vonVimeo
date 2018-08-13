package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserFollowingActivity extends BaseActivity implements RecyclerView, View.OnClickListener{

    private Toolbar toolbar;
    private String type;
    private String userName;
    private TextView likesCountTv;
    private TextView collectionCountTv;
    private TextView followingCountTv;

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
        likesCountTv = (TextView)findViewById(R.id.likes_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.collection_count_tv);
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
        if (type.equals(Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER)) {
            replaceFragment(RecyclerFragment.newInstance(type));
        } else {
            replaceFragment(RecyclerFragment.newInstance(getIntent().getStringExtra(Constants.ID), Constants
                    .REQUEST_LIST_FOLLOWING_FOR_A_USER));
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.likes_count_tv:
                intent = new Intent(this, UserLikesActivity.class);
                startActivity(intent);
                break;
            case R.id.collection_count_tv:
                intent = new Intent(this, UserCollectionsActivity.class);
                startActivity(intent);
                break;
            case R.id.following_count_tv:
                intent = new Intent(this, UserFollowingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
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
