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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class UserMessageActivity extends BaseActivity {


    private String title;
    private String type;
    private Context context;
    private Toolbar toolbar;
    private TextView descTv;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private TabPagerFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<RecyclerFragment> fragmentsList;
    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_channle;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        title = getString(R.string.my_messages_activity_title);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_messages_cdl);
        descTv = (TextView)findViewById(R.id.user_messages_desc_tv);
        progressBar = (ProgressBar)findViewById(R.id.user_messages_pb);
        toolbar = (Toolbar) findViewById(R.id.user_messagestb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        descTv.setText(R.string.my_messages_desc_tv_text);
        replaceFragment(RecyclerFragment.newInstance(type));
        initTabPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_messages_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_messages_inbox:
                break;
            case R.id.user_messages_unread:
                break;
            case R.id.user_messages_read:
                break;
            case R.id.user_messages_sent:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabPager() {
        tabLayout = (TabLayout)findViewById(R.id.user_messages_tl);
        viewPager = (ViewPager)findViewById(R.id.user_messages_vp);
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
        titlesList.add(getText(R.string.private_messages).toString());
        titlesList.add(getText(R.string.comments).toString());
        titlesList.add(getText(R.string.my_people).toString());
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_GET_ALL_PRIVATE_MESSAGES_OF_AUTH_USR));
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_GET_ALL_COMMENTS_OF_AUTH_USR));
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER));
        adapter.setFragments(fragmentsList, titlesList);
    }
}
