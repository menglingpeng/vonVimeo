package com.menglingpeng.vonvimeo.mvp.view;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.Channel;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.ShareAndOpenInBrowserUtil;


public class ChannelDetailActivity extends BaseActivity implements RecyclerView{


    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout channelDetailCtbl;
    private ImageView backgroundIv;
    private ImageView channelIconTv;
    private TextView channnelNameTv;
    private TextView channelDescTv;
    private Button startBt;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private String title;
    private String type;
    private Context context;
    private Channel channel;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_channel_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        channel = (Channel)getIntent().getSerializableExtra(Constants.CHANNLE);
        title = channel.getName();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.channel_detail_cdl);
        channelDetailCtbl = (CollapsingToolbarLayout)findViewById(R.id.channel_detail_ctbl);
        backgroundIv = (ImageView)findViewById(R.id.channel_detail_backgroud_iv);
        channelIconTv = (ImageView)findViewById(R.id.channel_detail_avatar_iv);
        channnelNameTv = (TextView)findViewById(R.id.channel_detail_name_tv);
        channelDescTv = (TextView)findViewById(R.id.channel_detail_desc_tv);
        startBt = (Button)findViewById(R.id.channel_detail_start_bt);
        toolbar = (Toolbar) findViewById(R.id.channel_detail_tb);
        progressBar = (ProgressBar)findViewById(R.id.channel_detail_pb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageLoader.load(channel, channel.getHeader().getUri(), channelIconTv, true);
        channelDescTv.setText(channel.getDescription());
        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        replaceFragment(RecyclerFragment.newInstance(type));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channel_detail_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.channel_detail_share:
                break;
            case R.id.channel_detail_follow:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareChannel(){
        String shareText = null;
        ShareAndOpenInBrowserUtil.share(context, shareText);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
