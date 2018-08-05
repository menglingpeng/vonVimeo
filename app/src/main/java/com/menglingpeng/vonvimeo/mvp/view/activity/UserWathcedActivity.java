package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

public class UserWathcedActivity extends BaseActivity implements RecyclerView{

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private String userName;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_watched;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        userName = getIntent().getStringExtra(Constants.NAME);
        String title = new StringBuilder().append(userName).append(getString(R.string.s)).append(getString(R.string
                .watched_history)).toString();
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.user_watched_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_watched_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replaceFragment(RecyclerFragment.newInstance(getIntent().getStringExtra(Constants.ID), Constants
                    .REQUEST_LIST_ALL_VIDOES_THAT_A_USER_HAS_WATCHED));
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_watched_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.watched_share:
                break;
            case R.id.watched_delete:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (requestType){
            case Constants.REQUEST_DELETE_A_USER_WATCH_HISTORY:
                if (json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout,
                            getString(R.sting.delete_a_user_watch_history_http_status_code_204));
                }
                break;
            case Constants.REQUEST_DELETE_A_VIDEO_FROM_YOUR_WATCH_HISTORY:
                if (json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout,
                            getString(R.sting.delete_a_video_from_your_watch_history_http_status_code_204));
                }
                break;
            default:
                break;
        }
    }
}
