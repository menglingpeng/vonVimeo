package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UpgradeActivity extends BaseActivity implements View.OnClickListener{


    private String title;
    private Context context;
    private User user;
    private String userId;
    private String account;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private Button plusUpgradeBt;
    private Button proUpgradeBt;
    private Button businessUpgradeBt;
    private Button premiumUpgradeBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_upgrade;
    }

    @Override
    protected void initViews() {
        super.initViews();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        context = getApplicationContext();
        userId = IdStringUtil.getId(user.getUri());
        account = user.getAccount();
        title = getString(R.string.activity_add_card_title);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.upgrade_cdl);
        toolbar = (Toolbar) findViewById(R.id.upgrade_tb);
        plusUpgradeBt = (Button)findViewById(R.id.upgrade_plus_upgrade_bt);
        proUpgradeBt = (Button)findViewById(R.id.upgrade_pro_upgrade_bt);
        businessUpgradeBt = (Button)findViewById(R.id.upgrade_business_upgrade_bt);
        premiumUpgradeBt = (Button)findViewById(R.id.upgrade_premium_upgrade_bt);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch (account){
            case Constants.ACCOUNT_BASIC:
                break;
            case Constants.ACCOUNT_PRO:
                proUpgradeBt.setClickable(false);
                break;
            case Constants.ACCOUNT_PLUS:
                proUpgradeBt.setClickable(false);
                plusUpgradeBt.setClickable(false);
                break;
            case Constants.ACCOUNT_BUSINESS:
                proUpgradeBt.setClickable(false);
                plusUpgradeBt.setClickable(false);
                businessUpgradeBt.setClickable(false);
                break;
            case Constants.ACCOUNT_PREMIUM:
                proUpgradeBt.setClickable(false);
                plusUpgradeBt.setClickable(false);
                businessUpgradeBt.setClickable(false);
                premiumUpgradeBt.setClickable(false);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.upgrade_plus_upgrade_bt:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PLUS);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            case R.id.upgrade_pro_upgrade_bt:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PRO);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            case R.id.upgrade__business_upgrade_bt:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_BUSSINESS);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            case R.id.upgrade_premium_upgrade_bt:
                intent = new Intent(this, UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PREMIUM);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
