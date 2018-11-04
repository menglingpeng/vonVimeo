package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;

public class MyActActivity extends BaseActivity {

    private String title;
    private String type;
    private Context context;
    private Toolbar toolbar;
    private TextView descTv;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_my_act;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        title = getString(R.string.my_messages_activity_title);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.my_activity_cdl);
        descTv = (TextView)findViewById(R.id.my_activity_desc_tv);
        toolbar = (Toolbar) findViewById(R.id.my_activity_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        descTv.setText(R.string.my_activity_desc_tv_text);
        replaceFragment(RecyclerFragment.newInstance(type));
    }
}
