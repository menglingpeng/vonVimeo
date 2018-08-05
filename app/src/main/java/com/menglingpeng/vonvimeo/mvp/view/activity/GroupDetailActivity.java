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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.JsonUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;
import com.menglingpeng.vonvimeo.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class GroupDetailActivity extends BaseActivity implements RecyclerView{

    private User user;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView groupDetailBackgroundIv;
    private ImageView groupDetailAvatarIv;
    private TextView groupDetailNameTv;
    private TextView groupDetailDescTv;
    private RecyclerPresenter presenter;
    private ProgressBar progressBar;
    private TabLayout groupDetailTl;
    private ViewPager groupDetailVp;
    private Button joinGroupBt;
    private Button leaveGroupBt;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<RecyclerFragment> fragments;
    private TabPagerFragmentAdapter adapter;
    private Context context;
    private String title;
    private String type;
    private Boolean isJoined;
    private ArrayList<RecyclerFragment> fragmentsList;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_group_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.group_detail_cdl);
        toolbar = (Toolbar) findViewById(R.id.group_detail_tb);
        tabLayout = (TabLayout)findViewById(R.id.group_detail_tbl);
        viewPager = (ViewPager)findViewById(R.id.group_detail_vp);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTabPager();
    }

    private void initData(String requestMethod){

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

    private void initFragments() {
        List<String> titles = new ArrayList<>();

            titles.add(getString(R.string.home_popular));
            titles.add(getString(R.string.home_recent));
            fragments.add(new RecyclerFragment().newInstance(Constants.TAB_GROUP_DETAIL_VIDEOS));
            fragments.add(new RecyclerFragment().newInstance(Constants.TAB_GROUP_DETAIL_MEMBERS));

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

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (type){
            case Constants.REQUEST_CHECK_IF_USER_HAS_JOINED_A_GROUP:
                if (json.equals(Constants.CODE_404_NOT_FOUND)) {
                    isJoined = false;
                } else {
                    isJoined = true;
                }
                break;
            case Constants.REQUEST_DELETE_A_GROUP:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_group_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_group_http_status_code_403));
                }
                break;
            case Constants.REQUEST_JOIN_A_GROUP:
                if(json.equals(Constants.CODE_204_NO_CONTENT)){
                    leaveGroupBt.setVisibility(Button.VISIBLE);
                    joinGroupBt.setVisibility(Button.GONE);
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, TextUtil.setAfterBold(context, getString(
                            R.string.joined_group_successful), user.getName()));
                    leaveGroupBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type = Constants.REQUEST_LEAVE_A_GROUP;
                            initData(Constants.REQUEST_DELETE_MEIHOD);
                        }
                    });
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout, getString(
                            R.string.join_a_group_http_status_code_403));
                }
                break;
            case Constants.REQUEST_LEAVE_A_GROUP:
                if(json.equals(Constants.CODE_204_NO_CONTENT)){
                    leaveGroupBt.setVisibility(Button.GONE);
                    joinGroupBt.setVisibility(Button.VISIBLE);
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, TextUtil.setAfterBold(context, getString(
                            R.string.leaved_group_successful), user.getName()));
                    joinGroupBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type = Constants.REQUEST_JOIN_A_GROUP;
                            initData(Constants.REQUEST_PUT_MEIHOD);
                        }
                    });
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout, getString(
                            R.string.leave_a_group_http_status_code_403));
                }
                break;
            default:
                progressBar.setVisibility(ProgressBar.GONE);
                user = JsonUtils.parseJson(json, User.class);
                coordinatorLayout = (CoordinatorLayout)findViewById(R.id.group_detail_cdl);
                collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.group_detail_ctbl);
                collapsingToolbarLayout.setVisibility(CollapsingToolbarLayout.VISIBLE);
                /*collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);*/
                toolbar = (Toolbar) findViewById(R.id.profile_tb);
                groupDetailNameTv = (TextView) findViewById(R.id.group_detail_name_tv);
                groupDetailDescTv = (TextView) findViewById(R.id.group_detail_desc_tv);
                groupDetailBackgroundIv = (ImageView) findViewById(R.id.group_detail_backgroud_iv);
                groupDetailAvatarIv = (ImageView) findViewById(R.id.group_detail_avatar_iv);
                joinGroupBt = (Button) findViewById(R.id.join_group_bt);
                leaveGroupBt = (Button) findViewById(R.id.leave_group_bt);
                if (type.equals(Constants.REQUEST_SINGLE_USER)) {
                    if (isJoined) {
                        joinGroupBt.setVisibility(Button.VISIBLE);
                        leaveGroupBt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                type = Constants.REQUEST_UNFOLLOW_A_USER;
                                initData(Constants.REQUEST_DELETE_MEIHOD);
                            }
                        });
                    } else {
                        joinGroupBt.setVisibility(Button.VISIBLE);
                        joinGroupBt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                type = Constants.REQUEST_FOLLOW_A_USER;
                                initData(Constants.REQUEST_PUT_MEIHOD);
                            }
                        });
                    }
                }
                groupDetailTl = (TabLayout) findViewById(R.id.profile_tl);
                groupDetailTl.setVisibility(TabLayout.VISIBLE);
                groupDetailVp = (ViewPager) findViewById(R.id.profile_vp);
                fragmentsList = new ArrayList<>();
                setSupportActionBar(toolbar);
                //隐藏Toolbar的标题
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                toolbar.setNavigationIcon(R.drawable.ic_back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                groupDetailNameTv.setText(user.getName());
                TextUtil.setHtmlText(groupDetailTv, user.getBio());
                ImageLoader.loadBlurImage(getApplicationContext(), user.getAvatar_url(), groupDetailBackgroundIv);
                ImageLoader.loadCricleImage(getApplicationContext(), user.getAvatar_url(), groupDetailAvatarIv);
                groupDetailBackgroundIv.setAlpha(100);
        }
    }
}
