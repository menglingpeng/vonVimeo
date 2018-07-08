package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class UserProfileActivity extends BaseActivity {

    private String type;
    private User user = null;
    private String userId;
    private CoordinatorLayout profileCdl;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView profileBackgroundIv;
    private ImageView profileAvatarIv;
    private TextView profileNameTv;
    private TextView profileDescTv;
    private RecyclerPresenter presenter;
    private ProgressBar progressBar;
    private TabLayout profileTl;
    private ViewPager profileVp;
    private Button followBt;
    private Button unfollowBt;
    private TabPagerFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<RecyclerFragment> fragmentsList;
    private Context context;
    private Boolean isFollowing;
    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {

        context = getApplicationContext();
        type = getIntent().getStringExtra(Constants.TYPE);
        userId = getIntent().getStringExtra(Constants.ID);
        map = new HashMap<>();
        layoutId = R.layout.activity_user_profile;

    }

    @Override
    protected void initViews() {
        super.initViews();
        progressBar = (ProgressBar) findViewById(R.id.profile_pb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_profile_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(type.equals(Constants.REQUEST_SINGLE_USER)){
            menu.findItem(R.id.profile_logout).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (type.equals(Constants.REQUEST_AUTH_USER)) {
            switch (item.getItemId()){
                case R.id.profile_share:
                    break;
                case R.id.profile_logout:
                    break;
                default:
                    break;
            }
        } else {
            if (item.getItemId() == R.id.profile_share) {
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabPager() {
        adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        initTabFragments();
        profileVp.setAdapter(adapter);
        profileTl.setupWithViewPager(profileVp);
        if(user.getShots_count() != 0){
            profileVp.setCurrentItem(1);
        }
        profileTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                profileVp.setCurrentItem(tab.getPosition());
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
        titlesList.add(getText(R.string.detail).toString());
        titlesList.add(getText(R.string.explore_spinner_list_shots).toString());
        titlesList.add(getText(R.string.followers).toString());
        if (type.equals(Constants.REQUEST_AUTH_USER)) {
            fragmentsList.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_DETAIL_FOR_AUTH_USER));
            fragmentsList.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_VIDEOS_FOR_AUTH_USER));
            fragmentsList.add(RecyclerFragment.newInstance(Constants.REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER));
        } else {
            fragmentsList.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_DETAIL_FOR_A_USER));
            fragmentsList.add(RecyclerFragment.newInstance(user, Constants.REQUEST_LIST_VIDEOS_FOR_A_USER));
            fragmentsList.add(RecyclerFragment.newInstance(userId, Constants.REQUEST_LIST_FOLLOWERS_FOR_A_USER));
        }
        adapter.setFragments(fragmentsList, titlesList);
    }
}
