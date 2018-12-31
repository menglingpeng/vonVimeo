package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.activity.UpgradeOrderActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UpgradeSettingsFragment extends BaseFragment implements View.OnClickListener{

    private User user;
    private String userId;
    private String account;
    private Context context;
    private Button plusUpgradeBt;
    private TextView plusUpgradeTv;
    private Button proUpgradeBt;
    private Button businessUpgradeBt;
    private Button premiumUpgradeBt;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_upgrade;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        userId = IdStringUtil.getId(user.getUri());
        account = user.getAccount();
        plusUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_plus_upgrade_bt);
        proUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_pro_upgrade_bt);
        businessUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_business_upgrade_bt);
        premiumUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_premium_upgrade_bt);
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
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.upgrade_settings_plus_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PLUS);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            case R.id.upgrade_settings_pro_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PRO);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            case R.id.upgrade_settings_business_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_BUSSINESS);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            case R.id.upgrade_settings_premium_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeOrderActivity.class);
                intent.putExtra(Constants.UPGRADE_TYPE, Constants.UPGRADE_TYPE_PREMIUM);
                intent.putExtra(Constants.USER_ID, userId);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
