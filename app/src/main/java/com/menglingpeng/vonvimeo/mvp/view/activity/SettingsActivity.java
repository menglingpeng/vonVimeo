package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class SettingsActivity extends BaseActivity {

    private String currentType;
    private Context context;
    private User user;
    private String userId;
    private Toolbar toolbar;
    private String toolbarTitle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProgressBar progressBar;
    private String weeklyStorageCountTv;
    private String totalStorageCountTv;
    public static final int REQUEST_PICTURE_CODE = 1;
    public static final int REQUEST_CUSTOM_LOGOS_CODE = 2;
    private Dialog shareVideoDialog;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_settins;
    }

    @Override
    protected void initViews() {
        super.initViews();

        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        drawerLayout = (DrawerLayout) findViewById(R.id.settings_dl);
        navigationView = (NavigationView) findViewById(R.id.settings_nv);
        progressBar = (ProgressBar)findViewById(R.id.upload_storage_pb);
        weeklyStorageCountTv = (TextView)findViewById(R.id.weekly_upload_storage_count_iv);
        totalStorageCountTv = (TextView)findViewById(R.id.total_upload_storage_count_tv);
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        switch (currentType) {
            case Constants.MENU_SETTINGS_ACCOUNT:
                toolbarTitle = getString(R.string.seetings_nav_account_menu);
                break;
            case Constants.MENU_SETTINGS_PROFILE:
                toolbarTitle = getString(R.string.seetings_nav_profile_menu);
                break;
            case Constants.MENU_SETTINGS_NOTIFICATIONS:
                toolbarTitle = getString(R.string.seetings_nav_notifiactions_menu);
                break;
            case Constants.MENU_SETTINGS_VIEWING_PREFERENCES:
                toolbarTitle = getString(R.string.seetings_nav_viewing_preferences_menu);
                break;
            case Constants.MENU_SETTINGS_MARKETING:
                toolbarTitle = getString(R.string.seetings_nav_marketing_menu);
                break;
            case Constants.MENU_SETTINGS_CONNECTED_APPS:
                toolbarTitle = getString(R.string.seetings_nav_connected_aps_menu);
                break;
            case Constants.MENU_SETTINGS_BILLING:
                toolbarTitle = getString(R.string.seetings_nav_billing_menu);
                break;
            case Constants.MENU_SETTINGS_VIDEOS:
                toolbarTitle = getString(R.string.seetings_nav_videos_menu);
                break;
            case Constants.MENU_SETTINGS_ON_DEMAND:
                toolbarTitle = getString(R.string.seetings_nav_on_demand_menu);
                break;
            case Constants.MENU_UPGRADE:
                toolbarTitle = getString(R.string.seetings_nav_upgrade_menu);
                break;
            default:
                break;
        }
        toolbar = (Toolbar) findViewById(R.id.settings_tl);
        toolbar.setTitle(toolbarTitle);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });
    }

    private void initNavigationView() {
        View headerView = navigationView.getHeaderView(0);
        //设置打开和关闭Drawer的特效
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string
                .nav_drawer_open, R.string.nav_drawer_close);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        navigationView.inflateMenu(R.menu.settings_nav_content_menu);
        navigationView.getMenu().getItem(0).setChecked(true);
        //API 23.0.0 method
        //navigationView.setCheckedItem(R.id.nav_home);
        //修改NavigationView选中的Icon和Text颜色，默认是跟随主题颜色。
        ColorStateList csl = getResources().getColorStateList(R.color.navigationview_menu_item_color);
        navigationView.setItemIconTintList(csl);
        navigationView.setItemTextColor(csl);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CUSTOM_LOGOS_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                /** 数据库查询操作。
                 * 第一个参数 uri：为要查询的数据库+表的名称。
                 * 第二个参数 projection ： 要查询的列。
                 * 第三个参数 selection ： 查询的条件，相当于SQL where。
                 * 第三个参数 selectionArgs ： 查询条件的参数，相当于 ？。
                 * 第四个参数 sortOrder ： 结果排序。
                 */
                Cursor cursor = cr.query(uri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        // 视频ID:MediaStore.Audio.Media._ID
                        int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        // 视频名称：MediaStore.Audio.Media.TITLE
                        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                        // 视频路径：MediaStore.Audio.Media.DATA
                        String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                        // 视频时长：MediaStore.Audio.Media.DURATION
                        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                        // 视频大小：MediaStore.Audio.Media.SIZE
                        long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));

                        // 视频缩略图路径：MediaStore.Images.Media.DATA
                        String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        // 缩略图ID:MediaStore.Audio.Media._ID
                        int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                        // 方法一 Thumbnails 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 ContentResolver，第二个参数为视频缩略图ID， 第三个参数kind有两种为：
                        // MICRO_KIND和MINI_KIND 字面意思理解为微型和迷你两种缩略模式，前者分辨率更低一些。
                        Bitmap bitmap1 = MediaStore.Video.Thumbnails.getThumbnail(cr, imageId,
                                MediaStore.Video.Thumbnails.MICRO_KIND, null);

                        // 方法二 ThumbnailUtils 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 视频/缩略图的位置，第二个依旧是分辨率相关的kind
                        Bitmap bitmap2 = ThumbnailUtils.createVideoThumbnail(imagePath, MediaStore.Video.Thumbnails.MICRO_KIND);
                        // 如果追求更好的话可以利用 ThumbnailUtils.extractThumbnail 把缩略图转化为的制定大小
//                        ThumbnailUtils.extractThumbnail(bitmap, width,height ,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

                    }
                    cursor.close();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
