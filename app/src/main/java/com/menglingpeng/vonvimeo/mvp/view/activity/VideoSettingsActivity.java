package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

public class VideoSettingsActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String currentType;
    private Toolbar toolbar;
    private String toolbarTitle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_video_settins;
    }

    @Override
    protected void initViews() {
        super.initViews();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        switch (currentType) {
            case Constants.MENU_GENERAL:
                toolbarTitle = getString(R.string.video_seetings_nav_general_menu);
                break;
            case Constants.MENU_COLLABORATION:
                toolbarTitle = getString(R.string.video_seetings_nav_collaboration_menu);
                break;
            case Constants.MENU_EMBED:
                toolbarTitle = getString(R.string.video_seetings_nav_embed_menu);
                break;
            case Constants.MENU_INTERACTION:
                toolbarTitle = getString(R.string.video_seetings_nav_interaction_menu);
                break;
            case Constants.MENU_DISTRIBUTION:
                toolbarTitle = getString(R.string.video_seetings_nav_distribution_menu);
                break;
            case Constants.MENU_STATS:
                toolbarTitle = getString(R.string.video_seetings_nav_stats_menu);
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
        return false;
    }
}
