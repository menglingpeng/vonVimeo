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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;

public class EditUserProfileActivity extends BaseActivity implements RecyclerView {

   private Toolbar toolbar;
   private CoordinatorLayout coordinatorLayout;
   private FloatingActionButton uploadFab;
   private Context context;
   private TextView avatarTv;
   private ImageView avatarIv;
   private TextView bioDescTv;
   private EditText bioEt;
   private TextView locationTv;
   private EditText locationEt;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
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
