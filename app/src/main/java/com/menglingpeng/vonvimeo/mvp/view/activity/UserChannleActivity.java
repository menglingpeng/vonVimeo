package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

public class UserChannleActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_channle;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.auth_user_channle_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_channles_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_channles_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton.setVisibility(FloatingActionButton.VISIBLE);
        replaceFragment(RecyclerFragment.newInstance(type));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (type) {
            case Constants.REQUEST_CREATE_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.create_a_channle_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.create_a_channle_http_status_code_403));
                }
                break;
            case Constants.REQUEST_DELETE_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_403));
                }
                break;
            case Constants.REQUEST_ADD_A_VIDEO_TO_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.add_video_to_a_channle_http_status_code_200));
                }
                break;
            case Constants.REQUEST_DELETE_A_VIDEO_FROM_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_404));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_404));
                }
                break;
            default:
                break;
        }
    }
}
