package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UpgradeSettingsFragment extends BaseFragment implements View.OnClickListener{

    private User user;
    private String userId;
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
        plusUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_plus_upgrade_bt);
        proUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_pro_upgrade_bt);
        businessUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_business_upgrade_bt);
        premiumUpgradeBt = (Button)rootView.findViewById(R.id.upgrade_settings_premium_upgrade_bt);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upgrade_settings_plus_upgrade_bt:
                break;
            case R.id.upgrade_settings_pro_upgrade_bt:
                break;
            case R.id.upgrade_settings_business_upgrade_bt:
                break;
            case R.id.upgrade_settings_premium_upgrade_bt:
                break;
            default:
                break;
        }
    }
}
