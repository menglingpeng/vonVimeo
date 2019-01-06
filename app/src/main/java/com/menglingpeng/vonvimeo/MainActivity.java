package com.menglingpeng.vonvimeo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.SearchActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String currentType;
    private Toolbar toolbar;
    private String toolbarTitle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RelativeLayout navHeaderRl;
    private LinearLayout exploreLl;
    private LinearLayout projectSpinnerLl;
    private ImageView navAvatarIv;
    private TextView navNameTv;
    private TextView navDescTv;
    private Button loginDialogLoginBt;
    private ProgressBar loginDialogPb;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<RecyclerFragment> fragments;
    private TabPagerFragmentAdapter adapter;
    private Spinner manageSpinner;
    private Spinner watchSpinner;
    private Spinner myVideoSpinner;
    private static RecyclerFragment currentFragment = null;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        super.initViews();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        exploreLl = (LinearLayout)findViewById(R.id.explore_ll) ;
        initToolbar();
        initNavigationView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Constants.ACTIVITY, Constants.ACTIVITY_MAIN);
                startActivity(intent);
                break;
            case R.id.main_more:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        switch (currentType) {
            case Constants.MENU_HOME:
                toolbarTitle = getString(R.string.app_name);
                break;
            case Constants.MENU_EXPLORE:
                toolbarTitle = getString(R.string.nav_explore_menu);
                break;
            case Constants.MENU_MY_LIKES:
                toolbarTitle = getString(R.string.nav_my_likes_menu);
                break;
            case Constants.MENU_MY_ALBUMS:
                toolbarTitle = getString(R.string.nav_album_menu);
                break;
            case Constants.MENU_MY_PROJECTS:
                toolbarTitle = getString(R.string.nav_projects_menu);
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


    private void initNavigationView() {
        View headerView = navigationView.getHeaderView(0);
        navHeaderRl = (RelativeLayout) headerView.findViewById(R.id.nav_view_header_rl);
        navAvatarIv = (ImageView) headerView.findViewById(R.id.login_avatar_iv);
        navNameTv = (TextView) headerView.findViewById(R.id.nav_view_name_tv);
        navDescTv = (TextView) headerView.findViewById(R.id.nav_view_desc_tv);
        //设置打开和关闭Drawer的特效
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string
                .nav_drawer_open, R.string.nav_drawer_close);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        navigationView.inflateMenu(R.menu.nav_content_menu);
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
        return false;
    }

    private void initTabPager() {
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

    private void scrollToTop(RecyclerView list) {
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

    private void initExplorSpinner() {
        exploreLl.setVisibility(LinearLayout.VISIBLE);
        manageSpinner = (Spinner) findViewById(R.id.manage_spinner);
        watchSpinner = (Spinner) findViewById(R.id.watch_spinner);
        ArrayAdapter<String> sortAdapter, listAdapter;
        String[] sortArray = getResources().getStringArray(R.array.sort);
        String[] listArray = getResources().getStringArray(R.array.list);
        sortAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_spinner_text, sortArray);
        listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_spinner_text, listArray);
        listAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        sortAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        manageSpinner.setAdapter(sortAdapter);
        watchSpinner.setAdapter(listAdapter);
        manageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_MY_VIDEOS));
                        break;
                    case 1:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_VIEW_STATS));
                        break;
                    case 2:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_SELL_VIDEOS));
                        break;
                    case 3:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_VIDEO_SCHOOL));
                        break;
                    case 4:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_UPGRADE));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        watchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_STAFF_PICKS));
                        break;
                    case 1:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_CATEGORITES));
                        break;
                    case 2:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_CHANNELS));
                        break;
                    case 3:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_GROUPS));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initMyVideoSpinner(){

        myVideoSpinner.setVisibility(LinearLayout.VISIBLE);
        myVideoSpinner = (Spinner) findViewById(R.id.my_video_spinner);
        ArrayAdapter<String> myVideoSpinnerAdatper;
        String[] myVideoSpinnerArray = getResources().getStringArray(R.array.my_video);
        myVideoSpinnerAdatper = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_spinner_text,
                myVideoSpinnerArray);
        myVideoSpinnerAdatper.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        myVideoSpinner.setAdapter(myVideoSpinnerAdatper);
        myVideoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_TITLE));
                        break;
                    case 1:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_DATE_MODIFIED));
                        break;
                    case 2:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_DATE_ADDED));
                        break;
                    case 3:
                        replaceFragment(newFragment(Constants.REQUEST_LIST_DURATION));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private RecyclerFragment newFragment(String type) {
        currentFragment = RecyclerFragment.newInstance(type);
        return currentFragment;
    }
}
