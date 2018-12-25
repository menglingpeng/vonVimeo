package com.menglingpeng.vonvimeo.mvp.view.payment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.activity.AddCardActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UpgradeOrderActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.ArrayList;

public class SupportedBankCardActivity extends BaseActivity implements RecyclerView{

    private String title;
    private String type;
    private Context context;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerFragmentAdapter adapter;
    private ArrayList<RecyclerFragment> fragmentsList;
    private Dialog dialog;
    private Dialog textsizeDialog;

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_channle;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.supported_bank_card_cdl);
        toolbar = (Toolbar) findViewById(R.id.supported_bank_card_tb);
        progressBar = (ProgressBar) findViewById(R.id.supported_bank_card_pb)
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTabPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.supported_bank_card_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() = R.id.operation_menu){
            showOperationDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabPager() {
        tabLayout = (TabLayout) findViewById(R.id.supported_bank_card_tl);
        viewPager = (ViewPager) findViewById(R.id.supported_bank_card_vp);
        adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        initTabFragments();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                scrollToTop(fragmentsList.get(tab.getPosition()).getRecyclerView());
            }
        });

    }

    private void scrollToTop(android.support.v7.widget.RecyclerView list) {
        int lastPosition;
        if (null != list) {
            LinearLayoutManager manager = (LinearLayoutManager) list.getLayoutManager();
            lastPosition = manager.findLastVisibleItemPosition();
            if (lastPosition < SMOOTHSCROLL_TOP_POSITION) {
                list.smoothScrollToPosition(0);
            } else {
                list.scrollToPosition(0);
            }
        }
    }

    private void initTabFragments() {

        ArrayList<String> titlesList = new ArrayList<>();
        titlesList.add(getText(R.string.featured).toString());
        titlesList.add(getText(R.string.directory).toString());
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_FEATURED_CHANNElS_SORT_BY_DATE));
        fragmentsList.add(RecyclerFragment.newInstance(
                Constants.REQUEST_LIST_ALL_DIRECTORY_CHANNElS_SORT_BY_DATE));
        adapter.setFragments(fragmentsList, titlesList);
    }

    private void showOperationDialog(){
        ImageView wechatIv;
        ImageView wechatCircleIv;
        ImageView homeIv;
        ImageView refreshIv;
        ImageView textsizeTv;
        TextView cancleTv;
        dialog = new Dialog(context, R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_supported_bank_card_operation, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        dialog.setContentView(dialogView);
        wechatIv = (ImageView) dialogView.findViewById(R.id.wechat_iv);
        wechatCircleIv = (ImageView) dialogView.findViewById(R.id.wechat_circle_iv);
        homeIv = (ImageView) dialogView.findViewById(R.id.back_home_iv);
        refreshIv = (ImageView) dialogView.findViewById(R.id.refresh_iv);
        textsizeTv = (ImageView) dialogView.findViewById(R.id.textsize_iv);
        cancleTv = (TextView) dialogView.findViewById(R.id.cancel_tv);
        dialog.show();
        wechatIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        wechatCircleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        homeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backHome();
            }
        });
        refreshIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        textsizeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextSizeDialog();
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    private void backHome(){
        Intent intent = new Intent(this, AddCardActivity.class);
        setResult(RESULT_OK, intent);
    }

    private void refresh(){

    }

    private void showTextSizeDialog(){
        SeekBar seekBar;
        int min = 12;
        int recommend = 20;
        int max = 25;
        textsizeDialog = new Dialog(context, R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_supported_bank_card_textsize_settings, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        textsizeDialog.setContentView(dialogView);
        seekBar = (SeekBar) dialogView.findViewById(R.id.textsize_sb);
        textsizeDialog.create();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            super.onDestroy();
            this.finish();
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
