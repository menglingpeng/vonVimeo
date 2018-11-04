package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.Message;
import com.menglingpeng.vonvimeo.utils.Constants;

public class MessageActivity extends BaseActivity {

    private String title;
    private String type;
    private Context context;
    private Toolbar toolbar;
    private TextView descTv;
    private CoordinatorLayout coordinatorLayout;
    private ImageView avatarIv;
    private TextView userNameTv;
    private TextView messageTitleTv;
    private TextView sendTimeTv;
    private Message message;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_message;
    }

    @Override
    protected void initViews() {
        super.initViews();

        context = getApplicationContext();
        message = (Message) getIntent().getSerializableExtra(Constants.MESSAGE);
        avatarIv = (ImageView)findViewById(R.id.sent_message_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.sent_message_user_name_tv);
        messageTitleTv = (TextView)findViewById(R.id.sent_message_title_tv);
        sendTimeTv = (TextView)findViewById(R.id.sent_message_send_time_tv);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.my_messages_cdl);
        descTv = (TextView)findViewById(R.id.my_messages_desc_tv);
        toolbar = (Toolbar) findViewById(R.id.my_messages_tb);
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
