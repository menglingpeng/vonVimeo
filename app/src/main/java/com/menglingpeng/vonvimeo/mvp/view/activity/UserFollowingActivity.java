package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class UserFollowingActivity extends BaseActivity implements RecyclerView, View.OnClickListener{

    private Toolbar toolbar;
    private String type;
    private String userName;
    private String sortType;
    private TextView likesCountTv;
    private TextView collectionCountTv;
    private TextView followingCountTv;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<RecyclerFragment> fragments;
    private TabPagerFragmentAdapter adapter;
    private static RecyclerFragment currentFragment = null;
    private User user;
    private String userId;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_following;
    }

    @Override
    protected void initViews() {
        super.initViews();
        type = getIntent().getStringExtra(Constants.TYPE);
        userName = getIntent().getStringExtra(Constants.NAME);
        String title = new StringBuilder().append(userName).append(getString(R.string.s)).append(getString(R.string
                .following)).toString();
        toolbar = (Toolbar) findViewById(R.id.user_following_tb);
        likesCountTv = (TextView)findViewById(R.id.likes_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.collection_count_tv);
        followingCountTv = (TextView)findViewById(R.id.following_count_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (type.equals(Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER)) {
            replaceFragment(RecyclerFragment.newInstance(type));
        } else {
            replaceFragment(RecyclerFragment.newInstance(getIntent().getStringExtra(Constants.ID), Constants
                    .REQUEST_LIST_FOLLOWING_FOR_A_USER));
        }

        initTabPager();
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
            case R.id.user_following_all_checked:
                if(type.equals(Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER)){

                }else if(type.equals(Constants.REQUEST_LIST_SUGGESTED_FOR_AUTH_USER)){

                }
                break;
            case R.id.user_following_no_checked:
                if(type.equals(Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER)){

                }else if(type.equals(Constants.REQUEST_LIST_SUGGESTED_FOR_AUTH_USER)){

                }
                break;
            case R.id.user_following_delete_checked:
                if(type.equals(Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER)){

                }else if(type.equals(Constants.REQUEST_LIST_SUGGESTED_FOR_AUTH_USER)){

                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.likes_count_tv:
                intent = new Intent(this, UserLikesActivity.class);
                startActivity(intent);
                break;
            case R.id.collection_count_tv:
                intent = new Intent(this, UserCollectionsActivity.class);
                startActivity(intent);
                break;
            case R.id.following_count_tv:
                intent = new Intent(this, UserFollowingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initTabPager() {
        tabLayout = (TabLayout)findViewById(R.id.following_tl);
        viewPager = (ViewPager)findViewById(R.id.following_vp);
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
        if (type.equals(Constants.REQUEST_AUTH_USER)) {
            titlesList.add(getText(R.string.following).toString());
            titlesList.add(getText(R.string.online).toString());
            titlesList.add(getText(R.string.followers).toString());
            titlesList.add(getText(R.string.suggested).toString());
        }else {
            titlesList.add(getText(R.string.following).toString());
            titlesList.add(getText(R.string.online).toString());
            titlesList.add(getText(R.string.followers).toString());
        }
        if (type.equals(Constants.REQUEST_AUTH_USER)) {
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER));
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_ONLINE_FOLLOWING_FOR_AUTH_USER));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_SUGGESTED_FOR_AUTH_USER));
        } else {
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_FOLLOWING_FOR_A_USER));
            fragments.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_ONLINE_FOLLOWING_FOR_A_USER));
            fragments.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_FOLLOWERS_FOR_A_USER));
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
        switch (requestType){
            case Constants.REQUEST_UNFOLLOW_A_USER:
                break;
            case Constants.REQUEST_FOLLOW_A_LIST_OF_USERS:
                break;
            default:
                break;
        }
    }
}
