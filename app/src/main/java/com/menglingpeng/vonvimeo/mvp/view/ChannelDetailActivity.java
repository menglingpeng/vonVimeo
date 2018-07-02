package com.menglingpeng.vonvimeo.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;

import org.w3c.dom.Text;

public class ChannelDetailActivity extends BaseActivity implements RecyclerView{


    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout channelDetailCtbl;
    private ImageView backgroundIv;
    private ImageView channelIconTv;
    private TextView channnelNameTv;
    private TextView channelDescTv;
    private Button startBt;
    private Toolbar toolbar;
    private String title;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_channel_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.channel_detail_cdl);
        channelDetailCtbl = (CollapsingToolbarLayout)findViewById(R.id.channel_detail_ctbl);
        backgroundIv = (ImageView)findViewById(R.id.channel_detail_backgroud_iv);
        channelIconTv = (ImageView)findViewById(R.id.channel_detail_avatar_iv);
        channnelNameTv = (TextView)findViewById(R.id.channel_detail_name_tv);
        channelDescTv = (TextView)findViewById(R.id.channel_detail_desc_tv);
        startBt = (Button)findViewById(R.id.channel_detail_start_bt);
        toolbar = (Toolbar) findViewById(R.id.channel_detail_tb);
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
