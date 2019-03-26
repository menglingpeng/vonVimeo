package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.OnDemandPage;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.SearchActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

public class UserOnDemandPageVideosActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private OnDemandPage onDemandPage;
    private Context context;
    private String onDemandId;
    private String sortType;
    private int checkedCounts;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_on_demand_page_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.on_demand_page_videos_cdl);
        toolbar = (Toolbar) findViewById(R.id.on_demand_page_videos_tb);
        progressBar = (ProgressBar)findViewById(R.id.on_demand_page_videos_pb);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.on_demand_page_videos_fab);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replaceFragment(RecyclerFragment.newInstance(Constants.ON_DEMAND_ID, onDemandId));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.user_on_demand_page_videos_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_on_demand_page_videos_search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Constants.ACTIVITY, Constants.ACTIVITY_VIMEO_ONDEMAND_PAGES);
                startActivity(intent);
                break;
            case R.id.user_on_demand_pages_thumb:
                break;
            case R.id.user_on_demand_pages_detail:
                break;
            case R.id.user_on_demand_page_videos_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.user_on_demand_page_videos_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.user_on_demand_page_videos_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.user_on_demand_page_videos_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.user_on_demand_pages_all_checked:

                break;
            case R.id.user_on_demand_pages_no_checked:
                break;
            case R.id.user_on_demand_pages_delete_checked:
                remove(type);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void remove(String type){
        String title = null;
        if(checkedCounts == 1) {
            title = getString(R.string.dilog_remove_a_video_title);
            type = Constants.REQUEST_REMOVE_A_VIDEO_FROM_AN_ON_DEMAND_PAGE;

        }else if(checkedCounts > 1){
            title = getString(R.string.dilog_remove_a_list_of_videos_title);
            type = Constants.REQUEST_REMOVE_A_LIST_OF_VIDEOS_FROM_AN_ON_DEMAND_PAGE;
        }
        showRemoveDialog(title, type);
    }

    private void showRemoveDialog(String title, String type){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
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
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

        switch (requestType){
            case Constants.REQUEST_REMOVE_A_VIDEO_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_LIST_OF_VIDEOS_FROM_AN_ON_DEMAND_PAGE:
                break;
            default:
                break;
        }
    }
}
