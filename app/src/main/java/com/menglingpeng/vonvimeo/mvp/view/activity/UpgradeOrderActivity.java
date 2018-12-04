package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.CountDownTimerUtils;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UpgradeOrderActivity extends BaseActivity implements View.OnClickListener{


    private User user;
    private String userId;
    private Context context;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private TextView payRemainingTimeTv;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_upgrade_order;
    }

    @Override
    protected void initViews() {
        super.initViews();

        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.upgrade_order_cdl);
        toolbar = (Toolbar) findViewById(R.id.upgrade_order_tb);
        payRemainingTimeTv = (TextView) findViewById(R.id.upgrade_order_pay_remaining_time_tv);
        toolbar.setTitle(getString(R.string.activity_upgrade_order_title));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        CountDownTimerUtils.timerStart();
        CountDownTimerUtils.setCountDownTimer(payRemainingTimeTv);
        //订单超时响应
        if(payRemainingTimeTv.getText().equals("00:00")){
            showOrderTimeoutDialog();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    private void showOrderTimeoutDialog(){

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.order_timeout_title);
        builder.setMessage(R.string.order_timeout_message);
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog = builder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);

        //点击其他区域消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

}
