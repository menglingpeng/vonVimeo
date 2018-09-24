package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.Project;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserProjectDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout projectDetailCdl;
    private FloatingActionButton floatingActionButton;
    private String type;
    private String title;
    private static RecyclerFragment fragment;
    private Context context;
    private Project project;
    private Menu viewMenu;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_project_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        project = (Project)getIntent().getSerializableExtra(Constants.PROJECT);
        title = project.getName();
        projectDetailCdl = (CoordinatorLayout)findViewById(R.id.user_project_detail_cdl);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.user_project_detail_upload_video_fab);
        toolbar = (Toolbar) findViewById(R.id.user_project_detail_tb);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.project_detail_share:
                break;
            case R.id.project_detail_remove:
                type = Constants.REQUEST_REMOVE_A_VIDEO_FROM_A_PROJECT;

                break;
            case R.id.project_detail_sort_title:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_TITLE;
                replaceFragment(RecyclerFragment.newInstance(Constants.PROJECT_ID, type));
                break;
            case R.id.project_detail_sort_date_modified:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_MODIFIED;
                replaceFragment(RecyclerFragment.newInstance(Constants.PROJECT_ID, type));
                break;
            case R.id.project_detail_sort_date_added:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_ADDED;
                replaceFragment(RecyclerFragment.newInstance(Constants.PROJECT_ID, type));
                break;
            case R.id.project_detail_sort_duration:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DURATION;
                replaceFragment(RecyclerFragment.newInstance(Constants.PROJECT_ID, type));
                break;
            case R.id.project_detail_view_thumb:
                item.setIcon(getDrawable(R.drawable.ic_view_thumb_blue_600_24dp));
                break;
            case R.id.project_detail_view_detail:
                item.setIcon(getDrawable(R.drawable.ic_view_detail_blue_600_24dp))
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
