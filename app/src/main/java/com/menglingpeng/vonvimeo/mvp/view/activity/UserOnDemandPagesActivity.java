package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserOnDemandPagesActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;
    private User user;
    private String sortType;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_project;
    }

    @Override
    protected void initViews() {
        super.initViews();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.demand_cdl);
        toolbar = (Toolbar) findViewById(R.id.demand_tb);
        title = user.getName();
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        type = Constants.REQUEST_GET_ALL_VIDEOS_OF_A_USER_ON_DEMAND_PAGES;
        replaceFragment(RecyclerFragment.newInstance(type));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.channles_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_on_demand_pages_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.user_on_demand_pages_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.user_on_demand_pages_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.user_on_demand_pages_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.user_on_demand_pages_thumb:
                break;
            case R.id.user_on_demand_pages_detail:
                break;
            default:
                break;
        }
        if(type.indexOf(Constants.AUTH_USER) != -1) {
            replaceFragment(RecyclerFragment.newInstance(type));
        }else {
            replaceFragment(RecyclerFragment.newInstance(Constants.USER_ID, type));
        }
        return super.onOptionsItemSelected(item);
    }
}
