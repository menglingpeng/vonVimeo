package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;

public class AddCardActivity extends BaseActivity {

    private String title;
    private Context context;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private TextView cardHolderNameTv;
    private EditText cardNumberEt;
    private Button verificateBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_add_card;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        title = getString(R.string.activity_add_card_title);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.add_card_cdl);
        toolbar = (Toolbar) findViewById(R.id.add_card_tb);
        cardHolderNameTv = (TextView) findViewById(R.id.cardholder_name_tv);
        cardNumberEt = (EditText) findViewById(R.id.card_number_et);
        verificateBt = (Button) findViewById(R.id.verficate_card_number_bt);
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
}
