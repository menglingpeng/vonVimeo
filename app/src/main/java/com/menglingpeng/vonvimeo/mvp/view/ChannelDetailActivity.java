package com.menglingpeng.vonvimeo.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.Channel;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.ShareAndOpenInBrowserUtil;

import java.util.ArrayList;
import java.util.HashMap;


public class ChannelDetailActivity extends BaseActivity implements RecyclerView{


    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout channelDetailCtbl;
    private ImageView backgroundIv;
    private ImageView channelIconTv;
    private TextView channnelNameTv;
    private TextView channelDescTv;
    private Button startBt;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private String title;
    private String type;
    private Context context;
    private Channel channel;
    private TabPagerFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<RecyclerFragment> fragmentsList;
    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_channel_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        channel = (Channel)getIntent().getSerializableExtra(Constants.CHANNLE);
        title = channel.getName();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.channel_detail_cdl);
        channelDetailCtbl = (CollapsingToolbarLayout)findViewById(R.id.channel_detail_ctbl);
        backgroundIv = (ImageView)findViewById(R.id.channel_detail_backgroud_iv);
        channelIconTv = (ImageView)findViewById(R.id.channel_detail_avatar_iv);
        channnelNameTv = (TextView)findViewById(R.id.channel_detail_name_tv);
        channelDescTv = (TextView)findViewById(R.id.channel_detail_desc_tv);
        startBt = (Button)findViewById(R.id.channel_detail_start_bt);
        toolbar = (Toolbar) findViewById(R.id.channel_detail_tb);
        progressBar = (ProgressBar)findViewById(R.id.channel_detail_pb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageLoader.load(channel, channel.getHeader().getUri(), channelIconTv, true);
        channelDescTv.setText(channel.getDescription());
        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        initTabPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channel_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.channel_detail_search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Constants.ACTIVITY, Constants.ACTIVITY_CHANNEL_DETAIL);
                startActivity(intent);
                break;
            case R.id.channel_detail_share:
                break;
            case R.id.channel_detail_follow:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareChannel(){
        String shareText = null;
        ShareAndOpenInBrowserUtil.share(context, shareText);
    }

    private void initTabPager() {
        tabLayout = (TabLayout)findViewById(R.id.channel_detail_tl);
        viewPager = (ViewPager)findViewById(R.id.channel_detail_vp);
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
        titlesList.add(getText(R.string.videos.toString());
        titlesList.add(getText(R.string.followers).toString());

        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_VIDEOS_IN_A_CHANNEL));
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_FOLLOWERS_OF_A_CHANNEL));
        adapter.setFragments(fragmentsList, titlesList);
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

    }
}
