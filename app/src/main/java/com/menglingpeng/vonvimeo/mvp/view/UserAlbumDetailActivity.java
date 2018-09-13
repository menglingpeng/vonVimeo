package com.menglingpeng.vonvimeo.mvp.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.Album;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

public class UserAlbumDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private ImageView avatarIv;
    private TextView userNameTv;
    private TextView videosCountTv;
    private Album album;
    private String title;
    private Context context;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_album_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        album = (Album)getIntent().getSerializableExtra(Constants.ALBUM);
        title = album.getName();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.album_detail_cdl);
        toolbar = (Toolbar) findViewById(R.id.id.album_detail_tb);
        avatarIv = (ImageView)findViewById(R.id.album_detail_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.album_detail_user_name_tv);
        videosCountTv = (TextView)findViewById(R.id.album_detail_videos_count_tv);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageLoader.loadCricleImage(context,album.getUser().getPictures().getUri(), avatarIv );
        userNameTv.setText(album.getUser().getName());
        videosCountTv.setText(album.getMetadata().getConnections().getVideos().getTotal());
    }
}
