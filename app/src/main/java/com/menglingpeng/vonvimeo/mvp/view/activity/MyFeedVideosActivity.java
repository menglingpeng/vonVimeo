package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class MyFeedVideosActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar progressBar;
    private String title;
    private String type;
    private Menu viewMenu;
    private SearchView searchView;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_my_feed_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.auth_user_project_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_projects_cdl);
        progressBar = (ProgressBar)findViewById(R.id.my_feed_pb);
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
        floatingActionButton.setVisibility(FloatingActionButton.VISIBLE);
        replaceFragment(RecyclerFragment.newInstance(type));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_detail_toolbar_overflow_menu, menu);
        MenuItem searchItme = menu.findItem(R.id.project_detail_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItme);
        //设置最大宽度
        searchView.setMaxWidth(500);
        //设置是否显示搜索框展开时的提交按钮
        searchView.setSubmitButtonEnabled(true);
        //设置输入框提示语
        searchView.setQueryHint(getString(R.string.search_my_videos));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.project_detail_sort_all:

                break;
            case R.id.project_detail_sort_date_likes:
                type = Constants.TYPE_LIKES;
                break;
            case R.id.project_detail_sort_uploads:
                type = Constants.TYPE_UPLOADS;
                break;
            case R.id.project_detail_sort_appearances:
                type = Constants.TYPE_APPEARS;
                break;
            case R.id.project_detail_sort_channels:
                type = Constants.TYPE_CHANNELS;
                break;
            case R.id.project_detail_sort_groups:
                type = Constants.TYPE_GROUPS;
                break;
            case R.id.project_detail_sort_categories:
                type = Constants.TYPE_CATEGORIES;
                break;
            case R.id.project_detail_sort_tags:
                type = Constants.TYPE_TAGS;
                break;
            case R.id.project_detail_view_thumb:
                item.setIcon(getDrawable(R.drawable.ic_view_thumb_blue_600_24dp));
                break;
            case R.id.project_detail_view_detail:
                item.setIcon(getDrawable(R.drawable.ic_view_detail_blue_600_24dp));
                break;
            default:
                break;
            replaceFragment(RecyclerFragment.newInstance(Constants.PROJECT_ID, type));
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
