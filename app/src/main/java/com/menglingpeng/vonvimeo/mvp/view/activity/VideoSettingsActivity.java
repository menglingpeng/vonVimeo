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
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.view.fragment.CollaborationFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.DistributionFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.EmbedFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.GeneralFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.GenresFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.InteractionToolsFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.StatsFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class VideoSettingsActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String currentType;
    private Toolbar toolbar;
    private String toolbarTitle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static final int REQUEST_PICTURE_CODE = 1;
    private Dialog shareVideoDialog;
    private Context context;
    private Video video;
    private String videoId;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_video_settins;
    }

    @Override
    protected void initViews() {
        super.initViews();

        context = getApplicationContext();
        video = (Video)getIntent().getSerializableExtra(Constants.VIDEO);
        videoId = IdStringUtil.getId(video.getUri());
        drawerLayout = (DrawerLayout) findViewById(R.id.video_settings_dl);
        navigationView = (NavigationView) findViewById(R.id.video_settings_nv);
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        switch (currentType) {
            case Constants.MENU_GENERAL:
                toolbarTitle = getString(R.string.video_seetings_nav_general_menu);
                break;
            case Constants.MENU_COLLABORATION:
                toolbarTitle = getString(R.string.video_seetings_nav_collaboration_menu);
                break;
            case Constants.MENU_EMBED:
                toolbarTitle = getString(R.string.video_seetings_nav_embed_menu);
                break;
            case Constants.MENU_INTERACTION:
                toolbarTitle = getString(R.string.video_seetings_nav_interaction_menu);
                break;
            case Constants.MENU_DISTRIBUTION:
                toolbarTitle = getString(R.string.video_seetings_nav_distribution_menu);
                break;
            case Constants.MENU_STATS:
                toolbarTitle = getString(R.string.video_seetings_nav_stats_menu);
                break;
            case Constants.MENU_UPGRADE:
                toolbarTitle = getString(R.string.video_seetings_nav_upgrade_menu);
                break;
            default:
                break;
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        navigationView.inflateMenu(R.menu.video_settings_nav_content_menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.video_settings_nav_general:
                currentType = Constants.MENU_GENERAL;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new GeneralFragment());
                break;
            case R.id.video_settings_nav_collaboration:
                currentType = Constants.MENU_COLLABORATION;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new CollaborationFragment());
                break;
            case R.id.video_settings_nav_embed:
                currentType = Constants.MENU_EMBED;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new EmbedFragment());
                break;
            case R.id.video_settings_nav_interaction_tools:
                currentType = Constants.MENU_INTERACTION;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new InteractionToolsFragment());
                break;
            case R.id.video_settings_nav_distribution:
                currentType = Constants.MENU_DISTRIBUTION;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new DistributionFragment());
                break;
            case R.id.video_settings_nav_stats:
                currentType = Constants.MENU_STATS;
                drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new StatsFragment());
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICTURE_CODE) {
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
                        // 图片ID:MediaStore.Images.Media._ID
                        int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                        // 图片名称：MediaStore.Images.Media.TITLE
                        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE));
                        // 图片路径：MediaStore.Images.Media.DATA
                        String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        // 图片大小：MediaStore.Images.Media.SIZE
                        long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));


                    }
                    cursor.close();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showSaveSettingsDialog(){
        TextView copyVideoLinkTv;
        TextView copyEmbedCodeTv;
        TextView copyReviewPageLinkTv;
        TextView publishToSocialTv;
        shareVideoDialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_video_settings_share, null);
        Window window = shareVideoDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.UploadChoosedialogStyle);
        window.getDecorView().setPadding(0, 0 , 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        shareVideoDialog.setContentView(dialogView);
        copyVideoLinkTv = (TextView)dialogView.findViewById(R.id.share_copy_video_link_tv);
        copyEmbedCodeTv = (TextView)dialogView.findViewById(R.id.share_copy_revidew_page_link_tv);
        copyReviewPageLinkTv = (TextView)dialogView.findViewById(R.id.share_copy_embed_code_tv);
        publishToSocialTv = (TextView)dialogView.findViewById(R.id.share_publish_to_social_tv);
        shareVideoDialog.show();
        copyVideoLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = video.getLink();
            }
        });
        copyEmbedCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        copyReviewPageLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        publishToSocialTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
