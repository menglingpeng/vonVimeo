package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.Message;

public class MessageActivity extends BaseActivity {

    private ImageView avatarIv;
    private TextView userNameTv;
    private TextView messageTitleTv;
    private TextView sendTimeTv;
    private Context context;
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
    }
}
