package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerCommonFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.CommonFragment;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfessionalsActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView descTv;
    private ImageView backgroundIv;
    private Button getPROBt;
    private Context context;
    private User user;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private Button bottomGetProBt;
    private TabPagerCommonFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<CommonFragment> fragmentsList;
    private static final int SMOOTHSCROLL_TOP_POSITION = 50;
    private ListView listView;



    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_professionals;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.professionals_cdl);
        toolbar = (Toolbar) findViewById(R.id.professionals_tb);
        descTv = (TextView)findViewById(R.id.professionals_desc_tv);
        backgroundIv = (ImageView) findViewById(R.id.professionals_backgroud_iv);
        getPROBt = (Button) findViewById(R.id.professionals_get_pro_bt);
        bottomGetProBt = (Button) findViewById(R.id.professionals_bottom_get_vimeo_pro_bt);
        progressBar = (ProgressBar)findViewById(R.id.professionals_pb);
        listView = (ListView) findViewById(R.id.professionals_creative_tools_lv);
        initTabPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.professionals_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.professionals_compare_plans:
                intent = new Intent(this, UpgradeActivity.class);
                break;
            case R.id.professionals_vimeo_pro:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PRO);
                break;
            case R.id.professionals_vimeo_plus:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PLUS);
                break;
            case R.id.professionals_vimeo_business:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_BUSSINESS);
                break;
            case R.id.professionals_vimeo_live:
                break;
            case R.id.professionals_vimeo_ott:
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    private void initTabPager() {
        tabLayout = (TabLayout)findViewById(R.id.professionals_tl);
        viewPager = (ViewPager)findViewById(R.id.professionals_vp);
        adapter = new TabPagerCommonFragmentAdapter(getSupportFragmentManager());
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
            }
        });
    }

    private void initTabFragments() {

        ArrayList<String> titlesList = new ArrayList<>();
        titlesList.add(getText(R.string.features.toString());
        titlesList.add(getText(R.string.pricing).toString());

        fragmentsList.add(CommonFragment.newInstance(
                Constants.COMMON_FRAGMENT_FEATURES));
        fragmentsList.add(CommonFragment.newInstance(
                Constants.COMMON_FRAGMENT_PRICING));
        adapter.setFragments(fragmentsList, titlesList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.professionals_get_pro_bt:
                getVimeoPro();
                break;
            case R.id.professionals_bottom_get_vimeo_pro_bt:
                getVimeoPro();
                break;
            default:
                break;
        }
    }

    private void getVimeoPro(){
        Intent intent = new Intent(this, UpgradeActivity.class);
        intent.putExtra(Constants.USER , user);
        startActivity(intent);
    }
}
