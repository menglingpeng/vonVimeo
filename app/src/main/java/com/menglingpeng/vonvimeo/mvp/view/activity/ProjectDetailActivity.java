package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

public class ProjectDetailActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private String type;
    private String title;
    private static RecyclerFragment fragment;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_project_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        toolbar = (Toolbar) findViewById(R.id.project_detail_tb);
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
        getMenuInflater().inflate(R.menu.project_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.project_detail_share:
                shareProject();
                break;
            case R.id.project_detail_remove:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareProject(){

    }


    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

        switch (requestType){
            case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT:
                break;
            case Constants.REQUEST_REMOVE_A_VIDEO_FROM_A_PROJECT:
                break;
            case Constants.REQUEST_REMOVE_VIDEOS_FROM_A_PROJECT:
                break;
            default:
                break;
        }
    }
}
