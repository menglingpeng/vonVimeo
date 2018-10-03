package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

public class UserPortfolioDetailActivity extends BaseActivity implements View.OnClickListener, RecyclerView {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private ImageView avatarIv;
    private TextView userNameTv;
    private TextView portfoliosDescTv;
    private Button onDemandPagesBt;
    private Button videosBt;
    private ProgressBar progressBar;
    private String type;
    private User user;
    private String sortType;
    private Context context;
    private String userId;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_portfolio_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_portfolios_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_portfolios_tb);
        avatarIv = (ImageView)findViewById(R.id.user_portfolios_user_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.user_portfolios_user_name_tv);
        portfoliosDescTv = (TextView)findViewById(R.id.user_portfolios_desc_tv);
        onDemandPagesBt = (Button)findViewById(R.id.user_portfolios_on_demand_pages_bt);
        videosBt = (Button)findViewById(R.id.user_portfolios_videos_bt);
        progressBar = (ProgressBar)findViewById(R.id.user_portfolios_pb);
        title = user.getName();
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageLoader.loadCricleImage(context, user.getPictures().getUri(), avatarIv);
        userNameTv.setText(user.getName());
        type = Constants.REQUEST_GET_ALL_VIDEOS_IN_PORTFOLIO_OF_A_USR;
        replaceFragment(RecyclerFragment.newInstance(Constants.USER_ID, userId));

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.user_portfolios_user_avatar_iv:
                intent = new Intent(this, UserProfileActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            case R.id.user_portfolios_user_name_tv:
                intent = new Intent(this, UserProfileActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            case R.id.user_portfolios_on_demand_pages_bt:
                intent = new Intent(this, UserOnDemandPagesActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            case R.id.user_portfolios_videos_bt:
                intent = new Intent(this, UserUploadedVideosActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            default:
                break;
                startActivity(intent);
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }

}
