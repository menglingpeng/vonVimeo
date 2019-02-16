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
    private TextView portfolioDescTv;
    private Button onDemandPagesBt;
    private Button videosBt;
    private ProgressBar progressBar;
    private String type;
    private String viewType;
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
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.portfolio_detail_cdl);
        toolbar = (Toolbar) findViewById(R.id.portfolio_detail_tb);
        avatarIv = (ImageView)findViewById(R.id.portfolio_detail_user_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.portfolio_detail_user_name_tv);
        portfolioDescTv = (TextView)findViewById(R.id.portfolio_detail_desc_tv);
        onDemandPagesBt = (Button)findViewById(R.id.portfolio_detail_on_demand_pages_bt);
        videosBt = (Button)findViewById(R.id.portfolio_detail_videos_bt);
        progressBar = (ProgressBar)findViewById(R.id.portfolio_detail_pb);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.user_portfolio_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_portfolio_detail_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.user_portfolio_detail_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.user_portfolio_detail_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.user_portfolio_detail_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.user_portfolio_detail_thumb:
                viewType = Constants.VIEW_TYPE_THUMBNAILS;
                break;
            case R.id.user_portfolio_detail_detail:
                viewType = Constants.VIEW_TYPE_DETAIL;
                break;
            case R.id.user_portfolio_detail_all_checked:
                break;
            case R.id.user_portfolio_detail_no_checked:
                break;
            case R.id.user_portfolio_detail_delete_checked:
                break;
            default:
                break;
        }
        if(type.indexOf(Constants.AUTH_USER) != -1) {
            replaceFragment(RecyclerFragment.newInstance(type));
        }else {
            replaceFragment(RecyclerFragment.newInstance(Constants.USER_ID, type));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.user_portfolio_user_avatar_iv:
                intent = new Intent(this, UserProfileActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            case R.id.user_portfolio_user_name_tv:
                intent = new Intent(this, UserProfileActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            case R.id.user_portfolio_on_demand_pages_bt:
                intent = new Intent(this, UserOnDemandPagesActivity.class);
                intent.putExtra(Constants.USER, user);
                break;
            case R.id.user_portfolio_videos_bt:
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
