package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.UserAlbumActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class UserCollectionsActivity extends BaseActivity implements View.OnClickListener, RecyclerView {

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private String type;
    private String title;
    private TextView portfoliosDescTv;
    private TextView likesCountTv;
    private TextView collectionCountTv;
    private TextView followingCountTv;
    private User user;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_collections;
    }

    @Override
    protected void initViews() {
        super.initViews();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_following_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_collections_tb);
        portfoliosDescTv = (TextView)findViewById(R.id.protfolios_desc_tv);
        likesCountTv = (TextView)findViewById(R.id.likes_count_tv);
        collectionCountTv = (TextView)findViewById(R.id.collection_count_tv);
        followingCountTv = (TextView)findViewById(R.id.following_count_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.likes_count_tv:
                intent = new Intent(this, UserLikesActivity.class);
                startActivity(intent);
                break;
            case R.id.collection_count_tv:
                intent = new Intent(this, UserCollectionsActivity.class);
                startActivity(intent);
                break;
            case R.id.following_count_tv:
                intent = new Intent(this, UserFollowingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void showCreateNewAlbumDialog() {
        final TextInputEditText albumNameEt, albumDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.create_a_bucket_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        albumNameEt = (TextInputEditText) dialogView.findViewById(R.id.bucket_name_tiet);
        albumDescEt = (TextInputEditText) dialogView.findViewById(R.id.bucket_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = albumNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, albumNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, albumDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserAlbumActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        albumNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showCreateNewChannleDialog() {
        final TextInputEditText channleNameEt;
        final TextInputEditText channleDescEt;
        final RadioGroup radioGroup;
        final RadioButton anyoneRb;
        final RadioButton moderatorsRb;
        final RadioButton moderatorsAndChooseRb;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_a_channle, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        channleNameEt = (TextInputEditText) dialogView.findViewById(R.id.channle_name_tiet);
        channleDescEt = (TextInputEditText) dialogView.findViewById(R.id.channle_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.group_privacy_settings_rg);
        anyoneRb = (RadioButton)dialogView.findViewById(R.id.channle_privacy_settings_anyone_rb);
        moderatorsRb = (RadioButton)dialogView.findViewById(R.id.channle_privacy_settings_moderators_rb);
        moderatorsAndChooseRb = (RadioButton)dialogView.findViewById(R.id.
                channle_privacy_settings_moderators_and_choose_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.group_privacy_settings_anyone_rb:
                        break;
                    case R.id.channle_privacy_settings_moderators_rb:
                        break;
                    case R.id.channle_privacy_settings_moderators_and_choose_rb:
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

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
