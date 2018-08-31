package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupsActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private TabPagerFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<RecyclerFragment> fragmentsList;
    private static final int SMOOTHSCROLL_TOP_POSITION = 50;
    private String viewType;
    private String groupType;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_group;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.choose_bucket_tb);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.choose_album_cdl);
        toolbar.setTitle(R.string.choose_a_group);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        viewType = Constants.VIEW_TYPE_THUMBNAILS;
        groupType = Constants.GROUP_TYPE_FEATURED;
        initTabPager();
    }

    private void initTabPager() {
        adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        initTabFragments();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                scrollToTop(fragmentsList.get(tab.getPosition()).getRecyclerView());
            }
        });

    }

    private void scrollToTop(android.support.v7.widget.RecyclerView list) {
        int lastPosition;
        if (null != list) {
            LinearLayoutManager manager = (LinearLayoutManager) list.getLayoutManager();
            lastPosition = manager.findLastVisibleItemPosition();
            if (lastPosition < SMOOTHSCROLL_TOP_POSITION) {
                list.smoothScrollToPosition(0);
            } else {
                list.scrollToPosition(0);
            }
        }
    }

    private void initTabFragments() {

        ArrayList<String> titlesList = new ArrayList<>();
        titlesList.add(getText(R.string.featured).toString());
        titlesList.add(getText(R.string.directory).toString());
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_FEATURED_GROUPS_SORT_BY_DATE_IN_THUMB_VIEW));
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_DIRECTORY_GROUPS_SORT_BY_DATE_IN_THUMB_VIEW));
        adapter.setFragments(fragmentsList, titlesList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.grops_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.groups_sort_date:
                if(groupType.equals(Constants.GROUP_TYPE_FEATURED)){
                    if(viewType.equals(Constants.VIEW_TYPE_THUMBNAILS)){

                    }else {

                    }
                }else {

                }
                break;
            case R.id.groups_sort_alphabetical:
                break;
            case R.id.groups_sort_videos:
                break;
            case R.id.groups_sort_members:
                break;
            case R.id.groups_sort_groups_thumbnails_view:
                break;
            case R.id.groups_sort_groups_detail_view:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
