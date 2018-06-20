package com.menglingpeng.vonvimeo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private String toolbarTitle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RelativeLayout navHeaderRl;
    private ImageView navAvatarIv;
    private TextView navNameTv;
    private TextView navDescTv;
    private Button loginDialogLoginBt;
    private ProgressBar loginDialogPb;
    private TabLayout tabLayout;
    private ViewPager viewPager;


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

        initNavigationView();
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
}
