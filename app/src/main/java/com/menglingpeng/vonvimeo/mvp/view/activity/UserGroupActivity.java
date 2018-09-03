package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class UserGroupActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar progressBar;
    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private String viewType;
    private String groupType;
    private String type;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_group;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.choose_bucket_tb);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_group_cdl);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.user_group_fab);
        toolbar.setTitle(R.string.choose_a_group);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateGroupDialog();
            }
        });
        Intent intent = getIntent();
        String shotId = intent.getStringExtra(Constants.ALBUM_ID);
        replaceFragment(RecyclerFragment.newInstance(shotId, Constants.REQUEST_CHOOSE_ALBUM));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.grops_toolbar_overflow_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.groups_search);
        searchView= (SearchView) MenuItemCompat.getActionView(searchItem);
        searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
        searchView.setQueryHint(getString(R.string.groups_search_view_hint_text));
        //设置搜索框无输入时，隐藏关闭按钮，有输入显示关闭按钮
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.groups_sort_date:
                if(groupType.equals(Constants.GROUP_TYPE_FEATURED)){
                    if(viewType.equals(Constants.VIEW_TYPE_THUMBNAILS)){

                    }else {

                    }
                }else {

                }
                break;
            case R.id.groups_sort_alphabetical:
                break;
            case R.id.groups_sort_videos:
                break;
            case R.id.groups_sort_members:
                break;
            case R.id.groups_sort_groups_thumbnails_view:
                break;
            case R.id.groups_sort_groups_detail_view:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCreateGroupDialog() {
        final TextInputEditText groupNameEt;
        final TextInputEditText groupDescEt;
        final RadioGroup radioGroup;
        final RadioButton anyoneRb;
        final RadioButton membersRb;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_a_group, null);
        builder.setTitle(R.string.create_a_group);
        builder.setView(dialogView);
        groupNameEt = (TextInputEditText) dialogView.findViewById(R.id.group_name_tiet);
        groupDescEt = (TextInputEditText) dialogView.findViewById(R.id.group_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.group_privacy_settings_rg);
        anyoneRb = (RadioButton)dialogView.findViewById(R.id.group_privacy_settings_anyone_rb);
        membersRb = (RadioButton)dialogView.findViewById(R.id.group_privacy_settings_member_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.group_privacy_settings_anyone_rb:
                        break;
                    case R.id.group_privacy_settings_members_rb:
                        break;
                    default:
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = groupNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_group_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, groupNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, groupDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserGroupActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_album_text));
                }

            }
        });
        groupNameEt.setFocusable(true);
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
            case Constants.REQUEST_CREATE_A_GROUP:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.create_a_group_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.create_a_group_http_status_code_403));
                }
                break;
            case Constants.REQUEST_ADD_A_VIDEO_TO_A_GROUP:
                if(json.indexOf(Constants.CODE_200_OK) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.add_video_to_a_group_http_status_code_200));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.add_video_to_a_group_http_status_code_403));
                }
                break;
            case Constants.REQUEST_DELETE_A_VIDEO_FROM_A_GROUP:
                break;
            default:
                break;
        }
    }

}
