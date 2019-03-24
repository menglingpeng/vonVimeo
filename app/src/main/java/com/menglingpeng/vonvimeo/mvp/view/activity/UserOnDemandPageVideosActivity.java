package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.OnDemandPage;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.SearchActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserOnDemandPageVideosActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private ProgressBar progressBar;
    private OnDemandPage onDemandPage;
    private FloatingActionButton ftBt;
    private Context context;
    private String onDemandId;
    private String sortType;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_on_demand_page_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.on_demand_page_videos_cdl);
        toolbar = (Toolbar) findViewById(R.id.on_demand_page_videos_tb);
        progressBar = (ProgressBar)findViewById(R.id.on_demand_page_videos_pb);
        ftBt = (FloatingActionButton) findViewById(R.id.on_demand_page_videos_ftb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replaceFragment(RecyclerFragment.newInstance(Constants.ON_DEMAND_ID, onDemandId));
        ftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.user_on_demand_page_videos_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_on_demand_page_videos_search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Constants.ACTIVITY, Constants.ACTIVITY_VIMEO_ONDEMAND_PAGES);
                startActivity(intent);
                break;
            case R.id.user_on_demand_pages_thumb:
                break;
            case R.id.user_on_demand_pages_detail:
                break;
            case R.id.user_on_demand_page_videos_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.user_on_demand_page_videos_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.user_on_demand_page_videos_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.user_on_demand_page_videos_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
