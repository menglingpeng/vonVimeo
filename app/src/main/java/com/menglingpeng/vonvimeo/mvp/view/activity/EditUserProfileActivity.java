package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
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
