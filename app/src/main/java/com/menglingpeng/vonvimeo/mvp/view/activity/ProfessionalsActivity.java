package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class ProfessionalsActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView descTv;
    private ImageView backgroundIv;
    private Button getPROBt;
    private Context context;
    private User user;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_professionals;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.professionals_cdl);
        toolbar = (Toolbar) findViewById(R.id.professionals_tb);
        descTv = (TextView)findViewById(R.id.professionals_desc_tv);
        backgroundIv = (ImageView) findViewById(R.id.professionals_backgroud_iv);
        getPROBt = (Button) findViewById(R.id.professionals_get_pro_bt);
    }
}
