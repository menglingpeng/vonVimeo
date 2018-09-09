package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class WatchLaterVideosActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private String title;
    private TextView likesCountTv;
    private TextView collectionCountTv;
    private TextView followingCountTv;
    private ProgressBar progressBar;
    private User user;
    private String type;
    private int collectionsCounts;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_watch_later_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title = new StringBuilder().append(getIntent().getStringExtra(Constants.NAME)).append(getString(R.string.s))
                .append(getString(R.string.likes)).toString();
        toolbar = (Toolbar) findViewById(R.id.user_watch_later_tb);
        likesCountTv = (TextView)findViewById(R.id.likes_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.collections_count_tv);
        followingCountTv = (TextView)findViewById(R.id.following_count_tv);
        progressBar = (ProgressBar)findViewById(R.id.user_watch_later_pb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        likesCountTv.setText(user.getMetadata().getConnections().getLikes().toString());
        collectionsCounts = Integer.valueOf(user.getMetadata().getConnections().getAlbums().getTotal()) + Integer.valueOf(
                user.getMetadata().getConnections().getChannels().getTotal()) + Integer.valueOf(
                        user.getMetadata().getConnections().getGroups().getTotal());
        collectionCountTv.setText(String.valueOf(collectionsCounts));
        followingCountTv.setText(user.getMetadata().getConnections().getFollowing().getTotal());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_watch_later_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.watch_later_videos_sort_date:
                type = Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_DATE;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.watch_later_videos_sort_alphabetical:
                type = Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_ALPHABETICAL;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.watch_later_videos_sort_plays:
                type = Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_PLAYS;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.watch_later_videos_sort_likes:
                type = Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_LIKES;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.watch_later_videos_sort_comments:
                type = Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_COMMENTS;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.watch_later_videos_sort_duration:
                type = Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_DURATION;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.user_watch_later_clear_all:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
