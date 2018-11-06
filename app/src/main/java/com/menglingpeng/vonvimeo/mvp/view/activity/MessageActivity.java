package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.Message;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

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
    private User user;

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

    private void replyMessage(){

        ImageView avatarIv;
        TextView userNameTv;
        final EditText messageEt;
        final String message;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_reply_a_message, null);
        builder.setTitle(R.string.reply_message);
        builder.setView(dialogView);
        avatarIv = (ImageView) dialogView.findViewById(R.id.reply_a_message_user_picture_iv);
        userNameTv = (TextView) dialogView.findViewById(R.id.reply_a_message_user_name_tv);
        messageEt = (EditText) dialogView.findViewById(R.id.reply_a_message_et);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                message = messageEt.getText().toString();
            }
        });
        dialog = builder.create();
        ImageLoader.loadCricleImage(context, user.getPictures().getUri(),  avatarIv);
        userNameTv.setText(user.getName());
        avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
            }
        });
        userNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
