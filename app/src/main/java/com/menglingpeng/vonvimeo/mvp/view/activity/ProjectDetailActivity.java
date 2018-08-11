package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.Project;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ShareAndOpenInBrowserUtil;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

public class ProjectDetailActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CoordinatorLayout projectDetailCdl;
    private TextView descTv;
    private String type;
    private String title;
    private static RecyclerFragment fragment;
    private Context context;
    private Project project;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_project_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        title = project.getName();
        projectDetailCdl = (CoordinatorLayout)findViewById(R.id.project_detail_cdl);
        descTv = (TextView)findViewById(R.id.project_detail_desc_tv);
        toolbar = (Toolbar) findViewById(R.id.project_detail_tb);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.project_detail_share:
                shareProject();
                break;
            case R.id.project_detail_remove:
                type = Constants.REQUEST_REMOVE_A_VIDEO_FROM_A_PROJECT;
                showRemoveVideoFromProject();
                break;
            case R.id.project_detail_sort_title:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_TITLE;
                replaceFragment(RecyclerFragment.newInstance(type));

                break;
            case R.id.project_detail_sort_date_modified:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_MODIFIED;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.project_detail_sort_date_added:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_ADDED;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.project_detail_sort_duration:
                type = Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DURATION;
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareProject(){
        String shareText;
        shareText = new StringBuffer().append(project.getName()).append("/n").append(project.getUri()).append("/n").
                append(getString(R.string.share_footer_text));
        ShareAndOpenInBrowserUtil.share(context , shareText);
    }

    private void showRemoveVideoFromProject(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_remove_video_from_project_title);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.sting.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog = builder.create();
        dialog.show();
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
            case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT:
                break;
            case Constants.REQUEST_REMOVE_A_VIDEO_FROM_A_PROJECT:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context, getString(
                            R.string.remove_a_video_from_a_project_http_status_code_204));
                }else if (json.indexOf(Constants.CODE_404_NOT_FOUND) != -1){
                    SnackbarUtils.showSnackShort(context, getString(
                            R.string.remove_a_video_from_a_project_http_status_code_404));
                }
                break;
            case Constants.REQUEST_REMOVE_VIDEOS_FROM_A_PROJECT:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context, getString(
                            R.string.remove_a_video_from_a_project_http_status_code_204));
                }else if (json.indexOf(Constants.CODE_404_NOT_FOUND) != -1){
                    SnackbarUtils.showSnackShort(context, getString(
                            R.string.remove_a_video_from_a_project_http_status_code_404));
                }
                break;
            default:
                break;
        }
    }
}
