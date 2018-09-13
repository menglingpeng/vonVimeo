package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.menglingpeng.designersshow.BaseActivity;
import com.menglingpeng.designersshow.R;
import com.menglingpeng.designersshow.mvp.interf.RecyclerView;
import com.menglingpeng.designersshow.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.designersshow.utils.Constants;
import com.menglingpeng.designersshow.utils.SharedPrefUtil;
import com.menglingpeng.designersshow.utils.SnackUI;
import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.UserAlbumsActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class ChooseAlbumActivity extends BaseActivity implements RecyclerView {

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_choose_album;
    }

    @Override
    protected void initViews() {
        super.initViews();
        toolbar = (Toolbar) findViewById(R.id.choose_bucket_tb);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.choose_album_cdl);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.choose_album_fab);
        toolbar.setTitle(R.string.choose_a_album);
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
                showCreateAlbumDialog();
            }
        });
        Intent intent = getIntent();
        String shotId = intent.getStringExtra(Constants.ALBUM_ID);
        replaceFragment(RecyclerFragment.newInstance(shotId, Constants.REQUEST_CHOOSE_ALBUM));
    }

    private void showCreateAlbumDialog() {
        final TextInputEditText albumNameEt;
        final TextInputEditText albumDescEt;
        final RadioGroup radioGroup;
        final RadioButton radioButton1;
        final RadioButton radioButton2;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.create_an_album_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        albumNameEt = (TextInputEditText) dialogView.findViewById(R.id.album_name_tiet);
        albumDescEt = (TextInputEditText) dialogView.findViewById(R.id.album_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.album_privacy_settings_rg);
        radioButton1 = (RadioButton)dialogView.findViewById(R.id.album_privacy_settings_anyone_rb);
        radioButton2  = (RadioButton)dialogView.findViewById(R.id.album_privacy_settings_people_with_password_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.album_privacy_settings_anyone_rb:
                        radioButton1.setText(Constants.PRIVACY_ANYONE);
                        break;
                    case R.id.album_privacy_settings_people_with_password_rb:
                        radioButton2.setText(Constants.PRIVACY_WITH_A_PASSWORD);
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
                String name = albumNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_album_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, albumNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, albumDescEt.getText().toString());
                    if(radioButton1.isChecked()) {
                        map.put(Constants.PRIVACY, radioButton1.getText().toString());
                    }else {
                        map.put(Constants.PRIVACY, radioButton2.getText().toString());
                    }
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserAlbumsActivity.this, type, Constants
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

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (type) {
            case Constants.REQUEST_CREATE_A_ALBUM:
                replaceFragment(RecyclerFragment.newInstance(Constants.REQUEST_CHOOSE_ALBUM));
                break;
        }

    }
}
