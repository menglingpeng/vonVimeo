package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class UserChannelsActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private String title;
    private String type;
    private Context context;
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
        floatingActionButton = (FloatingActionButton) findViewById(R.id.user_channles_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_channles_cdl);
        progressBar = (ProgressBar)findViewById(R.id.user_channles_pb);
        toolbar = (Toolbar) findViewById(R.id.user_channles_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton.setVisibility(FloatingActionButton.VISIBLE);
        replaceFragment(RecyclerFragment.newInstance(type));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateChannleDialog();
            }
        });
        initTabPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.channles_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_channels_sort_date:
                break;
            case R.id.user_channels_sort_alphabetical:
                break;
            case R.id.user_channels_sort_videos:
                break;
            case R.id.user_channels_sort_followers:
                break;
            case R.id.user_channels_thumb:
                break;
            case R.id.user_channels_detail:
                break;
            case R.id.channel_detail_all_checked:
                break;
            case R.id.channel_detail_no_checked:
                break;
            case R.id.channel_detail_delete_checked:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabPager() {
        tabLayout = (TabLayout)findViewById(R.id.user_channles_tl);
        viewPager = (ViewPager)findViewById(R.id.user_channles_vp);
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
        titlesList.add(getText(R.string.following).toString());
        titlesList.add(getText(R.string.moderated).toString());
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_FOLLOWING_CHANNElS_FOR_A_USER_SORT_BY_DATE_IN_VIEW_THUMB));
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_MODERATED_CHANNElS_FOR_A_USER_SORT_BY_DATE_IN_VIEW_THUMB));
        adapter.setFragments(fragmentsList, titlesList);
    }

    private void showCreateChannleDialog() {
        final TextInputEditText channleNameEt, channleDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_a_channle, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        channleNameEt = (TextInputEditText) dialogView.findViewById(R.id.channle_name_tiet);
        channleDescEt = (TextInputEditText) dialogView.findViewById(R.id.channle_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = channleNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, channleNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, channleDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserChannelsActivity.this, type,
                            Constants.REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        channleNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
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
        switch (type) {
            case Constants.REQUEST_CREATE_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.create_a_channle_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.create_a_channle_http_status_code_403));
                }
                break;
            case Constants.REQUEST_DELETE_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_403));
                }
                break;
            case Constants.REQUEST_ADD_A_VIDEO_TO_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.add_video_to_a_channle_http_status_code_200));
                }
                break;
            case Constants.REQUEST_DELETE_A_VIDEO_FROM_A_CHANNLE:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_404));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.delete_a_channle_http_status_code_404));
                }
                break;
            default:
                break;
        }
    }
}
