package com.menglingpeng.vonvimeo.mvp.view;

import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class UserProjectActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_project;
    }

    @Override
    protected void initViews() {
        super.initViews();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.auth_user_project_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.user_projects_cdl);
        toolbar = (Toolbar) findViewById(R.id.user_projects_tb);
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
                showCreateProjectDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_project_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.project_edit:
                showEditProjectDialog();
                break;
            case R.id.project_delete:
                showDeleteProjectDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCreateProjectDialog() {
        final TextInputEditText projectNameEt, projectDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.create_a_bucket_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        projectNameEt = (TextInputEditText) dialogView.findViewById(R.id.bucket_name_tiet);
        projectDescEt = (TextInputEditText) dialogView.findViewById(R.id.bucket_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = projectNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, projectNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, projectDescEt.getText().toString());
                    type = Constants.REQUEST_REQUEST_CREATE_A_PROJECT;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserProjectActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        projectNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showEditProjectDialog() {
        final TextInputEditText projectNameEt, projectDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.create_a_bucket_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        projectNameEt = (TextInputEditText) dialogView.findViewById(R.id.project_name_tiet);
        projectDescEt = (TextInputEditText) dialogView.findViewById(R.id.project_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = projectNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, projectNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, projectDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserProjectActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        projectNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showDeleteProjectDialog() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.create_a_bucket);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        
        
    }
    
    private void addVodeoToProject() {

        final TextInputEditText projectNameEt, projectDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.create_a_bucket_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        projectNameEt = (TextInputEditText) dialogView.findViewById(R.id.project_name_tiet);
        projectDescEt = (TextInputEditText) dialogView.findViewById(R.id.project_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = projectNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, projectNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, projectDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserProjectActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });

        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        projectNameEt.setFocusable(true);
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
