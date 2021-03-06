package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.SearchActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SearchViewUtils;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VimeoOndemandPagesActivity extends BaseActivity implements RecyclerView,
        NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView demandDescTv;
    private ProgressBar progressBar;
    private String toolbarTitle;
    private String currentType;
    private String sortType;
    private MenuItem menuItem;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<RecyclerFragment> fragments;
    private TabPagerFragmentAdapter adapter;
    private Boolean backPressed = false;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_vimeo_ondemand_pages;
    }

    @Override
    protected void initViews() {
        super.initViews();
        drawerLayout = (DrawerLayout) findViewById(R.id.vimeo_demand_pages_dl);
        navigationView = (NavigationView) findViewById(R.id.vimeo_demand_pages_nv);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.vimeo_demand_pages_cdl);
        toolbar = (Toolbar) findViewById(R.id.vimeo_demand_pages_tb);
        demandDescTv = (TextView)findViewById(R.id.vimeo_demand_pages_desc_tv);
        progressBar = (ProgressBar)findViewById(R.id.vimeo_demand_pages_pb);
        menuItem = toolbar.findViewById(R.id.vimeo_on_demand_pages_search);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        switch (currentType) {
            case Constants.MENU_REGIONS:
                toolbarTitle = getString(R.string.vimeo_on_demand_pages_nav_regions_menu_text);
                break;
            case Constants.MENU_SEASONS:
                toolbarTitle = getString(R.string.vimeo_on_demand_pages_nav_seasons_menu_text);
                break;
            case Constants.MENU_GENRES:
                toolbarTitle = getString(R.string.vimeo_on_demand_pages_nav_genres_menu_text);
                break;
            case Constants.MENU_POSTERS:
                toolbarTitle = getString(R.string.vimeo_on_demand_pages_nav_posters_menu_text);
                break;
            case Constants.MENU_UPGRADE:
                toolbarTitle = getString(R.string.video_seetings_nav_upgrade_menu);
                break;
            default:
                break;
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(toolbarTitle);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.vimeo_on_demand_pages_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.vimeo_on_demand_pages_search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Constants.ACTIVITY, Constants.ACTIVITY_VIMEO_ONDEMAND_PAGES);
                startActivity(intent);
                break;
            case R.id.vimeo_on_demand_pages_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.vimeo_on_demand_pages_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.vimeo_on_demand_pages_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.vimeo_on_demand_pages_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.vimeo_on_demand_pages_thumb:
                break;
            case R.id.vimeo_on_demand_pages_detail:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initNavigationView() {
        View headerView = navigationView.getHeaderView(0);
        //设置打开和关闭Drawer的特效
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string
                .nav_drawer_open, R.string.nav_drawer_close);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        navigationView.inflateMenu(R.menu.video_settings_nav_content_menu);
        navigationView.getMenu().getItem(0).setChecked(true);
        //API 23.0.0 method
        //navigationView.setCheckedItem(R.id.nav_home);
        //修改NavigationView选中的Icon和Text颜色，默认是跟随主题颜色。
        ColorStateList csl = getResources().getColorStateList(R.color.navigationview_menu_item_color);
        navigationView.setItemIconTintList(csl);
        navigationView.setItemTextColor(csl);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.vimeo_on_demand_pages_nav_seasons:
                currentType = Constants.MENU_SEASONS;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(RecyclerFragment.newInstance(currentType));
                break;
            case R.id.vimeo_on_demand_pages_nav_genres:
                currentType = Constants.MENU_GENRES;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(RecyclerFragment.newInstance(currentType));
                break;
            case R.id.vimeo_on_demand_pages_nav_regions:
                currentType = Constants.MENU_REGIONS;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(RecyclerFragment.newInstance(currentType));
                break;
            case R.id.vimeo_on_demand_pages_nav_posters:
                currentType = Constants.MENU_POSTERS;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(RecyclerFragment.newInstance(currentType));
                break;
            case R.id.vimeo_on_demand_pages_nav_backgrouds:
                currentType = Constants.MENU_POSTERS;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(RecyclerFragment.newInstance(currentType));
                break;
            case R.id.vimeo_on_demand_pages_nav_promotionss:
                currentType = Constants.MENU_POSTERS;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(RecyclerFragment.newInstance(currentType));
                break;
            case R.id.nav_upgrade:
                currentType = Constants.MENU_UPGRADE;
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(this, UpgradeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return false;
    }

    private void initTabPager() {
        tabLayout = (TabLayout)findViewById(R.id.vimeo_demand_pages_tl);
        viewPager = (ViewPager)findViewById(R.id.vimeo_demand_pages_vp);
        tabLayout.setVisibility(TabLayout.VISIBLE);
        viewPager.setVisibility(ViewPager.VISIBLE);
        fragments = new ArrayList<>();
        adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        initFragments();
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

    private void initFragments() {
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.videos));
        titles.add(getString(R.string.members));
        titles.add(getString(R.string.moderators));
        fragments.add(new RecyclerFragment().newInstance(Constants.ID, Constants.TAB_VIMEO_ONDEMAND_PAGES_VIDEOS));
        fragments.add(new RecyclerFragment().newInstance(Constants.ID, Constants.TAB_VIMEO_ONDEMAND_PAGES_FOLLOW_VIDEOS));
        fragments.add(new RecyclerFragment().newInstance(Constants.ID, Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS));

        adapter.setFragments(fragments, titles);
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
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if(SharedPrefUtils.getState(Constants.DOUBLE_BACK_TO_EXIT)) {
                doubleBackToQuit();
            }else {
                super.onBackPressed();
            }
        }
    }

    private void doubleBackToQuit() {
        if (backPressed) {
            super.onBackPressed();
        }
        backPressed = true;
        SnackbarUtils.showSnackShort(getApplicationContext(), drawerLayout, getResources().getString(R.string
                .double_back_quit));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        }, 2000);
    }
}
