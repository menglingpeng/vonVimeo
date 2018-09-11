package com.menglingpeng.vonvimeo.mvp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.usb.UsbRequest;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class UserAlbumsActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String type;
    private Context context;
    private String userId;
    private User user;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_album;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User) getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
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
        floatingActionButton.setVisibility(FloatingActionButton.VISIBLE);
        type = Constants.REQUEST_LIST_ALL_ALBUMS_OF_A_USER_SORT_BY_DATE;
        replaceFragment(RecyclerFragment.newInstance(userId, type));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateAlbumDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_album_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.albums_sort_date:
                type = Constants.REQUEST_LIST_ALL_ALBUMS_OF_A_USER_SORT_BY_DATE;
                break;
            case R.id.album_albums_sort_alphabetical:
                type = Constants.REQUEST_LIST_ALL_ALBUMS_OF_A_USER_SORT_BY_DATE;
                break;
            case R.id.albums_sort_videos:
                type = Constants.REQUEST_LIST_ALL_ALBUMS_OF_A_USER_SORT_BY_DATE;
                break;
            case R.id.albums_sort_duration:
                type = Constants.REQUEST_LIST_ALL_ALBUMS_OF_A_USER_SORT_BY_DATE;
                break;
            default:
                break;
        }
        replaceFragment(RecyclerFragment.newInstance(userId, type));
        return super.onOptionsItemSelected(item);
    }

    private void showCreateAlbumDialog() {
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

    private void showEditAlbumDialog() {
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
                    RecyclerPresenter presenter = new RecyclerPresenter(UserAlbumsActivity.this, type, Constants
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
        albumNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showDeleteAlbumDialog(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.create_a_bucket);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.sting.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

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
        switch (requestType){
            case Constants.REQUEST_CREATE_A_ALBUM:
                if(json.indexOf(Constants.CODE_200_OK) != -1) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.create_an_album_http_status_code_200));
                }else if (json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.create_an_album_http_status_code_403));
                }
                break;
            case Constants.REQUEST_DELETE_A_ALBUM:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.delete_an_album_http_status_code_204));
                }else if (json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.delete_an_album_http_status_code_403));
                }else if (json.indexOf(Constants.CODE_404_NOT_FOUND) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.delete_an_album_http_status_code_404));
                }
                break;
            case Constants.REQUEST_ADD_A_VIDEO_TO_AN_ALBUM:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.add_a_video_to_an_album_http_status_code_204));
                }else if (json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.add_a_video_to_an_album_http_status_code_403));
                }else if (json.indexOf(Constants.CODE_404_NOT_FOUND) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.add_a_video_to_an_album_http_status_code_404));
                }
                break;
            case Constants.REQUEST_REMOVE_A_VIDEO_FROM_AN_ALBUM:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.remove_a_video_from_an_album_http_status_code_204));
                }else if (json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.remove_a_video_from_an_album_http_status_code_4104));
                }else if (json.indexOf(Constants.CODE_404_NOT_FOUND) != -1){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(
                            R.string.remove_a_video_from_an_album_http_status_code_404));
                }
                break;

            default:
                break;
        }
    }
}
