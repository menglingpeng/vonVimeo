package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryDetailActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private TabLayout profileTl;
    private ViewPager profileVp;
    private String type;
    private TabPagerFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<RecyclerFragment> fragmentsList;
    private Context context;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_category_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        String title = new StringBuilder().append(userName).append(getString(R.string.s)).append(getString(R.string
                .watched_history)).toString();
        toolbar = (Toolbar) findViewById(R.id.category_detail__tb);

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
