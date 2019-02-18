package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class UserOnDemandPagesActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private ImageView avatarIv;
    private TextView userNameTv;
    private TextView demandDescTv;
    private ProgressBar progressBar;
    private String type;
    private User user;
    private String sortType;
    private Context context;
    private String userId;
    private String onDemandId;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_on_demand_pages;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.demand_cdl);
        toolbar = (Toolbar) findViewById(R.id.demand_tb);
        avatarIv = (ImageView)findViewById(R.id.demand_user_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.demand_user_name_tv);
        demandDescTv = (TextView)findViewById(R.id.demand_desc_tv);
        progressBar = (ProgressBar)findViewById(R.id.demand_pb);
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
        type = Constants.REQUEST_GET_ALL_VIDEOS_OF_A_USER_ON_DEMAND_PAGES;
        replaceFragment(RecyclerFragment.newInstance(Constants.USER_ID, userId));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.user_on_demand_pages_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_on_demand_pages_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.user_on_demand_pages_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.user_on_demand_pages_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.user_on_demand_pages_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.user_on_demand_pages_thumb:
                break;
            case R.id.user_on_demand_pages_detail:
                break;
            case R.id.user_on_demand_pages_all_checked:
                if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)){

                }
                break;
            case R.id.user_on_demand_pages_no_checked:
                if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)){

                }
                break;
            case R.id.user_on_demand_pages_delete_checked:
                if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)){

                }
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
