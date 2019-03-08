package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;

public class OnDemandPageGenresActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_on_demand_page_genres;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.genres_tb);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.genres_cdl);
        toolbar.setTitle(R.string.genres);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.genres_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() = R.id.genres_edit){

        }
        return super.onOptionsItemSelected(item);
    }
}
