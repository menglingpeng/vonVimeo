package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.UserProjectActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class EditUserProfileActivity extends BaseActivity implements RecyclerView , View.OnClickListener{

   private Toolbar toolbar;
   private CoordinatorLayout coordinatorLayout;
   private FloatingActionButton uploadFab;
   private Context context;
   private TextView avatarTv;
   private ImageView avatarIv;
   private TextView bioDescTv;
   private EditText bioEt;
   private String bio;
   private TextView aboutTv;
   private EditText aboutEt;
   private String about;
   private TextView locationTv;
   private EditText locationEt;
   private String location;
   private TextView urlTv;
   private EditText urlEt;
   private String vimeoURL;
   private TextView websiteTv;
   private Button websiteBt;
   private Button saveBt;
   private String type;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_edit_user_profile;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.edit_user_cdl);
        toolbar = (Toolbar)findViewById(R.id.edit_user_tb);
        avatarTv = (TextView)findViewById(R.id.user_avatar_desc_tv);
        avatarIv = (ImageView)findViewById(R.id.user_avatar_iv);
        bioDescTv = (TextView)findViewById(R.id.edit_user_bio_tv);
        bioEt = (EditText)findViewById(R.id.edit_user_bio_et);
        aboutTv = (TextView)findViewById(R.id.edit_user_about_tv);
        aboutEt = (EditText)findViewById(R.id.edit_user_about_et);
        locationTv = (TextView)findViewById(R.id.edit_user_location_tv);
        locationEt = (EditText)findViewById(R.id.edit_user_location_et);
        urlTv = (TextView)findViewById(R.id.edit_user_vimeo_url_tv);
        urlEt = (EditText)findViewById(R.id.edit_user_vimeo_url_et);
        websiteTv = (TextView)findViewById(R.id.edit_user_website_tv);
        websiteBt = (Button)findViewById(R.id.edit_user_website_bt);
        saveBt = (Button)findViewById(R.id.edit_user_save_bt);

        uploadFab = (FloatingActionButton)findViewById(R.id.upload_picture_fab);
        toolbar.setTitle(getString(R.string.edit_user_profile_toobar_title));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        uploadFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData(){
        bio = bioEt.getText().toString();
        about = aboutEt.getText().toString();
        location = locationEt.getText().toString();
        vimeoURL = urlEt.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_user_save_bt:
                editUser();
                break;
            case R.id.edit_user_website_bt:
                showAddWebsiteDialog();
                break;
            default:
                break;

        }
    }

    private void showAddWebsiteDialog(){
        final TextInputEditText websiteNameEt;
        final TextInputEditText websiteURLEt;
        final TextInputEditText websiteDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_a_new_link, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        websiteNameEt = (TextInputEditText) dialogView.findViewById(R.id.website_name_tiet);
        websiteDescEt = (TextInputEditText) dialogView.findViewById(R.id.website_desc_tiet);
        websiteURLEt = (TextInputEditText)dialogView.findViewById(R.id.website_url_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = websiteNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, websiteNameEt.getText().toString());
                    map.put(Constants.URL, websiteURLEt.getText().toString());
                    map.put(Constants.DESCRIPTION, websiteDescEt.getText().toString());
                    type = Constants.REQUEST_REQUEST_CREATE_A_PROJECT;
                    RecyclerPresenter presenter = new RecyclerPresenter(EditUserProfileActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        websiteNameEt.setFocusable(true);
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


    private void editUser(){

    }
}
