package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
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

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class FeedManagerActivity extends BaseActivity implements RecyclerView {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;
    private Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<RecyclerFragment> fragments;
    private TabPagerFragmentAdapter adapter;
    private static RecyclerFragment currentFragment = null;
    private User user;
    private String userId;
    private String sortType;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_feed_manager;
    }

    @Override
    protected void initViews() {
        super.initViews();
        toolbar = (Toolbar) findViewById(R.id.user_following_tb);
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
        getMenuInflater().inflate(R.menu.user_likes_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_following_sort_date:
                sortType = Constants.REQUEST_GET_FOLLOWINGS_OF_AUTH_USER_SORY_BY_ALPHABETICAL;
                break;
            case R.id.user_following_sort_alphabetical:
                sortType = Constants.REQUEST_GET_FOLLOWINGS_OF_AUTH_USER_SORY_BY_DATE;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
                scrollToTop(fragments.get(tab.getPosition()).getRecyclerView());
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
        titlesList.add(getText(R.string.following).toString());
        titlesList.add(getText(R.string.online).toString());
        titlesList.add(getText(R.string.followers).toString());
        titlesList.add(getText(R.string.suggested).toString());
        if (type.equals(Constants.REQUEST_AUTH_USER)) {
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER));
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_USER_ALBUMS));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_CHANNELS));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_GROUPS));
        } else {
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_FOLLOWING_FOR_A_USER));
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_ONLINE_FOLLOWING_FOR_A_USER));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_FOLLOWERS_FOR_A_USER));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_SUGGESTED_FOR_A_USER));
        }
        adapter.setFragments(fragments, titlesList);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
