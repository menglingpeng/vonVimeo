package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.TimeUtils;

import org.w3c.dom.Text;

public class UserAboutActivity extends BaseActivity implements View.OnClickListener{

    public User user;
    public Toolbar toolbar;
    public ImageView avatarIv;
    public TextView userNameTv;
    public Button userLevelBt;
    public TextView joinedTimeTv;
    public TextView locationTv;
    public TextView bioTv;
    private String title;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_about;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        title = user.getName();
        toolbar = (Toolbar) findViewById(R.id.user_about_tb);
        avatarIv = (ImageView)findViewById(R.id.user_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.user_name_tv);
        userLevelBt = (Button)findViewById(R.id.user_level_bt);
        joinedTimeTv = (TextView)findViewById(R.id.joined_time_tv);
        locationTv = (TextView)findViewById(R.id.user_location_tv);
        bioTv = (TextView)findViewById(R.id.user_about_bio_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData(){
        ImageLoader.loadCricleImage(context, user.getPictures().getUri(), avatarIv);
        userNameTv.setText(user.getName());
        if(!user.getAccount().equals(Constants.ACCOUNT_BASIC)){
            userLevelBt.setVisibility(Button.VISIBLE);
            String buttonText = null;
            switch (user.getAccount()){
                case Constants.ACCOUNT_PLUS:
                    buttonText = getString(R.string.plus_user_level_bt_text);
                    break;
                case Constants.ACCOUNT_PRO:
                    buttonText = getString(R.string.pro_user_level_bt_text);
                    break;
                case Constants.ACCOUNT_BUSINESS:
                    buttonText = getString(R.string.business_user_level_bt_text);
                    break;
                case Constants.ACCOUNT_PREMIUM:
                    buttonText = getString(R.string.premium_user_level_bt_text);
                    break;    
                 default:
                     break;
            }
            userLevelBt.setText(buttonText);
        }
        joinedTimeTv.setText(TimeUtils.getTimeDifference(user.getCreated_time()));
        locationTv.setText(user.getLocation().toString());
        bioTv.setText(user.getBio().toString());
    }

    @Override
    public void onClick(View view) {

    }
}
