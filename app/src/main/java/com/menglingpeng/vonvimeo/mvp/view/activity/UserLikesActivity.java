package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.designersshow.BaseActivity;
import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.utils.Constants;
import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ShareAndOpenInBrowserUtil;

public class UserLikesActivity extends BaseActivity implements RecyclerView, View.OnClickListener{

    private Toolbar toolbar;
    private String type;
    private String title;
    private TextView likesCountTv;
    private TextView collectionCountTv;
    private TextView followingCountTv;
    private User user;
    private Video video;

    private static RecyclerFragment fragment;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_likes;
    }

    @Override
    protected void initViews() {
        super.initViews();
        type = getIntent().getStringExtra(Constants.TYPE);
        title = new StringBuilder().append(getIntent().getStringExtra(Constants.NAME)).append(getString(R.string.s))
                .append(getString(R.string.likes)).toString();
        toolbar = (Toolbar) findViewById(R.id.user_likes_tb);
        likesCountTv = (TextView)findViewById(R.id.likes_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.collection_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.following_count_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replaceFragment(newFragment(type));
    }

    private RecyclerFragment newFragment(String type) {
        if (type.equals(Constants.REQUEST_LIST_LIKES_FOR_A_USER)) {
            fragment = RecyclerFragment.newInstance(type);
        } else {
            fragment = RecyclerFragment.newInstance(getIntent().getStringExtra(Constants.ID), type);
        }
        return fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_likes_share:
                shareProject();
                break;
            case R.id.user_likes_remove:
                type = Constants.REQUEST_REMOVE_A_VIDEO_FROM_A_PROJECT;
                break;
            case R.id.project_detail_sort_title:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_TITLE;
                replaceFragment(RecyclerFragment.newInstance(type));

                break;
            case R.id.user_likes_sort_date:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_MODIFIED;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.user_likes_sort_alphabetical:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_ADDED;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.project_detail_sort_duration:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DURATION;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.user_likes_sort_plays:
                type = Constants.REQUEST_GET_ALL_VIDEOS_LIKED_BY_AUTH_USER_SORY_BY_PLAYS;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.user_likes_sort_likes:
                type = Constants.REQUEST_GET_ALL_VIDEOS_LIKED_BY_AUTH_USER_SORY_BY_LIKES;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.user_likes_sort_comments:
                type = Constants.REQUEST_GET_ALL_VIDEOS_LIKED_BY_AUTH_USER_SORY_BY_COMMENTS;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareProject(){
        String shareText;
        shareText = new StringBuffer().append(project.getName()).append("/n").append(project.getUri()).append("/n").
                append(getString(R.string.share_footer_text));
        ShareAndOpenInBrowserUtil.share(context , shareText);
    }

    public static RecyclerFragment getFragment() {
        return fragment;
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
