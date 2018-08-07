package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import com.menglingpeng.vonvimeo.mvp.model.Category;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.JsonUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;
import com.menglingpeng.vonvimeo.utils.TextUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryDetailActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CollapsingToolbarLayout categoryCtl;
    private CoordinatorLayout categoryCdl;
    private ImageView categoryDetailBackgroundIv;
    private ImageView categoryDetailAvatarIv;
    private TextView categoryDetailNameTv;
    private TextView categoryDetailDescTv;
    private RecyclerPresenter presenter;
    private ProgressBar progressBar;
    private TabLayout profileTl;
    private ViewPager profileVp;
    private String type;
    private String categoryName;
    private TabPagerFragmentAdapter adapter;
    private HashMap<String, String> map;
    private ArrayList<RecyclerFragment> fragmentsList;
    private Button followBt;
    private Button unfollowBt;
    private Context context;
    private boolean isFollowing;
    private Category category;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_category_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();

        String title = new StringBuilder().append(categoryName).append(getString(R.string.s)).append(getString(R.string
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
                .REQUEST_GET_A_CATEGORY));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.category_detail_share){

        }
    }

    private void initTabPager() {
        adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        initTabFragments();
        profileVp.setAdapter(adapter);
        profileTl.setupWithViewPager(profileVp);
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

    private void initTabFragments() {

        ArrayList<String> titlesList = new ArrayList<>();
        titlesList.add(getText(R.string.videos).toString());
        titlesList.add(getText(R.string.channles).toString());
        titlesList.add(getText(R.string.groups).toString());

        fragmentsList.add(RecyclerFragment.newInstance(Constants.REQUEST_GET_ALL_VIDEOS_IN_A_CATEGORY));
        fragmentsList.add(RecyclerFragment.newInstance(Constants.REQUEST_GET_ALL_CHANNLES_FOR_A_CATEGORY));
        fragmentsList.add(RecyclerFragment.newInstance(Constants.REQUEST_GET_ALL_GROUPS_FOR_A_CATEGORY));

        adapter.setFragments(fragmentsList, titlesList);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (requestType){
            case Constants.REQUEST_CHECK_IF_A_USER_FOLLOWS_A_CATEGORY:
                if (json.equals(Constants.CODE_204_NO_CONTENT)) {
                    isFollowing = true;
                } else {
                    isFollowing = false;
                }
                break;
            case Constants.REQUEST_SUBSCRIBE_A_USER_TO_A_CATEGORY:
                if (json.indexOf(Constants.CODE_204_NO_CONTENT) != -1) {
                    unfollowBt.setVisibility(Button.VISIBLE);
                    followBt.setVisibility(Button.GONE);
                    SnackbarUtils.showSnackShort(context,categoryCdl, TextUtil.setAfterBold(context, getString(
                            R.string.followed_successful), category.getName()));
                    unfollowBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type = Constants.REQUEST_UNFOLLOW_A_USER;
                        }
                    });
                } else {
                    SnackbarUtils.showErrorSnackShort(context, categoryCdl, getString(R.string.
                            follow_a_user_http_status_code_403));
                }
                break;
            case Constants.REQUEST_UNSUBSCRIBE_A_USER_FROM_A_CATEGORY:
                if(json.equals(Constants.CODE_204_NO_CONTENT)){
                    unfollowBt.setVisibility(Button.GONE);
                    followBt.setVisibility(Button.VISIBLE);
                    SnackbarUtils.showSnackShort(context, categoryCdl, TextUtil.setAfterBold(context, getString(
                            R.string.unfollowed_successful), category.getName()));
                    followBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            type = Constants.REQUEST_FOLLOW_A_USER;

                        }
                    });
                }
                break;
             default:
                 progressBar.setVisibility(ProgressBar.GONE);
                 category = JsonUtils.parseJson(json, Category.class);
                 categoryCdl = (CoordinatorLayout) findViewById(R.id.category_detail_cdl);
                 categoryCdl = (CollapsingToolbarLayout) findViewById(R.id.category_detail_ctbl);
                 categoryCdl.setVisibility(CollapsingToolbarLayout.VISIBLE);
                /*collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);*/
                 toolbar = (Toolbar) findViewById(R.id.category_detail_tb);
                 categoryDetailNameTv = (TextView) findViewById(R.id.category_detail_name_tv);
                 categoryDetailDescTv = (TextView) findViewById(R.id.category_detail_desc_tv);
                 categoryDetailBackgroundIv = (ImageView) findViewById(R.id.category_detail_backgroud_iv);
                 categoryDetailAvatarIv = (ImageView) findViewById(R.id.category_detail_avatar_iv);
                 followBt = (Button) findViewById(R.id.category_detail_follow_bt);
                 unfollowBt = (Button) findViewById(R.id.category_detail_unfollow_bt);
                 if (type.equals(Constants.REQUEST_SINGLE_USER)) {
                     if (isFollowing) {
                         unfollowBt.setVisibility(Button.VISIBLE);
                         unfollowBt.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 type = Constants.REQUEST_UNFOLLOW_A_USER;
                                 initData(Constants.REQUEST_DELETE_MEIHOD);
                             }
                         });
                     } else {
                         followBt.setVisibility(Button.VISIBLE);
                         followBt.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 type = Constants.REQUEST_FOLLOW_A_USER;
                                 initData(Constants.REQUEST_PUT_MEIHOD);
                             }
                         });
                     }
                 }
                 profileTl = (TabLayout) findViewById(R.id.profile_tl);
                 profileTl.setVisibility(TabLayout.VISIBLE);
                 profileVp = (ViewPager) findViewById(R.id.profile_vp);
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
                 categoryDetailNameTv.setText(category.getName());
                 TextUtil.setHtmlText(categoryDetailDescTv, category.get);
                 break;

        }

    }
}
