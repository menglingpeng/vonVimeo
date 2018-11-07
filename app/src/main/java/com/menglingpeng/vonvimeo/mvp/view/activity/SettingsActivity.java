package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class SettingsActivity extends BaseActivity {

    private String currentType;
    private Context context;
    private User user;
    private String userId;
    private Toolbar toolbar;
    private String toolbarTitle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static final int REQUEST_PICTURE_CODE = 1;
    private Dialog shareVideoDialog;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_settins;
    }

    @Override
    protected void initViews() {
        super.initViews();

        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        drawerLayout = (DrawerLayout) findViewById(R.id.settings_dl);
        navigationView = (NavigationView) findViewById(R.id.settings_nv);
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        switch (currentType) {
            case Constants.MENU_SETTINGS_ACCOUNT:
                toolbarTitle = getString(R.string.seetings_nav_account_menu);
                break;
            case Constants.MENU_SETTINGS_PROFILE:
                toolbarTitle = getString(R.string.seetings_nav_profile_menu);
                break;
            case Constants.MENU_SETTINGS_NOTIFICATIONS:
                toolbarTitle = getString(R.string.seetings_nav_notifiactions_menu);
                break;
            case Constants.MENU_SETTINGS_VIEWING_PREFERENCES:
                toolbarTitle = getString(R.string.seetings_nav_viewing_preferences_menu);
                break;
            case Constants.MENU_SETTINGS_MARKETING:
                toolbarTitle = getString(R.string.seetings_nav_marketing_menu);
                break;
            case Constants.MENU_SETTINGS_CONNECTED_APPS:
                toolbarTitle = getString(R.string.seetings_nav_connected_aps_menu);
                break;
            case Constants.MENU_SETTINGS_BILLING:
                toolbarTitle = getString(R.string.seetings_nav_billing_menu);
                break;
            case Constants.MENU_SETTINGS_VIDEOS:
                toolbarTitle = getString(R.string.seetings_nav_videos_menu);
                break;
            case Constants.MENU_SETTINGS_ON_DEMAND:
                toolbarTitle = getString(R.string.seetings_nav_on_demand_menu);
                break;
            case Constants.MENU_UPGRADE:
                toolbarTitle = getString(R.string.seetings_nav_upgrade_menu);
                break;
            default:
                break;
        }
        toolbar = (Toolbar) findViewById(R.id.settings_tl);
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
        navigationView.inflateMenu(R.menu.settings_nav_content_menu);
        navigationView.getMenu().getItem(0).setChecked(true);
        //API 23.0.0 method
        //navigationView.setCheckedItem(R.id.nav_home);
        //修改NavigationView选中的Icon和Text颜色，默认是跟随主题颜色。
        ColorStateList csl = getResources().getColorStateList(R.color.navigationview_menu_item_color);
        navigationView.setItemIconTintList(csl);
        navigationView.setItemTextColor(csl);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
