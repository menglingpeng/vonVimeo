package com.menglingpeng.vonvimeo.mvp.view;

import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackUtils;

import java.util.HashMap;

public class UserAlbumActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = android.R.layout.activity_user_album;
    }

    @Override
    protected void initViews() {
        super.initViews();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.auth_user_album_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_albums_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_albums_tb);
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

    private void showCreateAlbumDialog() {
        final TextInputEditText bucketNameEt, bucketDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.create_a_bucket_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        bucketNameEt = (TextInputEditText) dialogView.findViewById(R.id.bucket_name_tiet);
        bucketDescEt = (TextInputEditText) dialogView.findViewById(R.id.bucket_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = bucketNameEt.getText().toString();
                if (name.equals("")) {
                    SnackUI.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, bucketNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, bucketDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserAlbumActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        bucketNameEt.setFocusable(true);
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
