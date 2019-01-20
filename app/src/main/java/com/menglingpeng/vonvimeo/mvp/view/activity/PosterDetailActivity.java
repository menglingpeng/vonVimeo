package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.SearchActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

public class PosterDetailActivity extends BaseActivity implements RecyclerView{

    private String title;
    private Context context;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private String sortType;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_poster_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.poster_detail_cdl);
        toolbar = (Toolbar) findViewById(R.id.poster_detail_tb);
        progressBar = (ProgressBar)findViewById(R.id.poster_detail_pb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.genre_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.poster_detail_thumb_view:
                break;
            case R.id.poster_detail_detail_view:
                break;
            case R.id.genre_detail_search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Constants.ACTIVITY, Constants.ACTIVITY_GENRE_DETAIL);
                startActivity(intent);
                break;
            case R.id.poster_detail_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.poster_detail_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.poster_detail_sort_plays:
                sortType = Constants.TYPE_PLAYS;
                break;
            case R.id.poster_detail_sort_likes:
                sortType = Constants.TYPE_LIKES;
                break;
            case R.id.poster_detail_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.poster_detail_sort_duration:
                sortType = Constants.TYPE_DURATION;
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
