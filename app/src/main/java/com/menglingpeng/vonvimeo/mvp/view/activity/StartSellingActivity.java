package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.ConditionVariable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class StartSellingActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView demandDescTv;
    private ImageView backgroundIv;
    private Button watchBt;
    private TextView joinPROTv;
    private Button joinPROBt;
    private User user;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_start_selling;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.start_selling_cdl);
        toolbar = (Toolbar) findViewById(R.id.start_selling_tb);
        demandDescTv = (TextView)findViewById(R.id.start_selling_desc_tv);
        backgroundIv = (ImageView) findViewById(R.id.start_selling_iv);
        watchBt = (Button) findViewById(R.id.start_selling_watch_video_bt);
        joinPROTv = (TextView) findViewById(R.id.start_selling_join_pro_tv);
        joinPROBt = (Button) findViewById(R.id.start_selling_join_pro_bt);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_selling_iv:
                break;
            case R.id.start_selling_watch_video_bt:
                break;
            case R.id.start_selling_join_pro_tv:
                joinVimeoPro();
                break;
            case R.id.start_selling_join_pro_bt:
                joinVimeoPro();
                break;
            default:
                break;
        }
    }

    private void joinVimeoPro(){
        Intent intent = new Intent(this, UpgradeActivity.class);
        intent.putExtra(Constants.USER , user);
        startActivity(intent);
    }
}
