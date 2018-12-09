package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.CountDownTimerUtils;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UpgradeOrderActivity extends BaseActivity implements View.OnClickListener, CheckBox.OnCheckedChangeListener{


    private User user;
    private String userId;
    private Context context;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private TextView payRemainingTimeTv;
    private TextView orderDetailTv;
    private TextView totalPriceTv;
    private String vimeoPlan;
    private CheckBox chooseAlipayCb;
    private CheckBox chooseWechatpayCb;
    private CheckBox chooseOtherpayCb;
    private Spinner otherPaySpinner;
    private ListView otherPayLv;

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
        vimeoPlan = getIntent().getStringExtra(Constants.VIMEO_PLAN);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.upgrade_order_cdl);
        toolbar = (Toolbar) findViewById(R.id.upgrade_order_tb);
        payRemainingTimeTv = (TextView) findViewById(R.id.upgrade_order_pay_remaining_time_tv);
        orderDetailTv = (TextView) findViewById(R.id.upgrade_order_content_detail_tv);
        totalPriceTv = (TextView) findViewById(R.id.upgrade_order_content_total_price_tv);
        chooseAlipayCb = (CheckBox) findViewById(R.id.upgrade_order_payment_choose_alipay_cb);
        chooseWechatpayCb = (CheckBox) findViewById(R.id.upgrade_order_payment_choose_wechat_pay_cb);
        chooseOtherpayCb = (CheckBox) findViewById(R.id.upgrade_order_payment_choose_other_pay_cb);
        otherPayLv = (ListView) findViewById(R.id.upgrade_order_payment_choose_other_pay_lv);

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
        switch (vimeoPlan){
            case Constants.VIMEO_PLAN_PLUS:
                totalPriceTv.setText(String.valueOf(Constants.VIMEO_PLAN_PLUS_TOTAL_PRICE));
                break;
            case Constants.VIMEO_PLAN_PRO:
                totalPriceTv.setText(String.valueOf(Constants.VIMEO_PLAN_PRO_TOTAL_PRICE));
                break;
            case Constants.VIMEO_PLAN_BUSSINESS:
                totalPriceTv.setText(String.valueOf(Constants.VIMEO_PLAN_BUSSINESS_TOTAL_PRICE));
                break;
            default:
                break;

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upgrade_order_content_detail_tv:
                showOrderDetailDialog(vimeoPlan);
                break;
            default:
                break;
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

    private void showOrderDetailDialog(String vimeoPlan){
        TextView upgradePlanNameTv;
        TextView upgradePlanPriceTv;
        TextView membershipFirstTv;
        TextView membershipSecondTv;
        TextView membershipThirdTv;
        TextView membershipFourthTv;
        String  upgradePlanName;
        String upgradePlanPrice;
        String membershipFirst;
        String membershipSecond;
        String membershipThird;
        String membershipFourth;
        ImageView closeTv;
        final AlertDialog dialog = null;
        dialog = new Dialog(context, R.style.ThemeLoginDialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_upgrade_order_detail, null);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.LoginDialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        upgradePlanNameTv = (TextView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_title_tv);
        upgradePlanPriceTv = (TextView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_price_tv);
        membershipFirstTv = (TextView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_membership_first_tv);
        membershipSecondTv = (TextView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_membership_second_tv);
        membershipThirdTv = (TextView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_membership_third_tv);
        membershipFourthTv = (TextView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_membership_fourth_tv);
        closeTv = (ImageView)dialogView.findViewById(R.id.dialog_upgrade_order_detail_close_iv);
        switch (vimeoPlan){
            case Constants.VIMEO_PLAN_PLUS:
                upgradePlanName = getString(R.string.dialog_upgrade_order_detail_title_tv_vimeo_plus_text);
                upgradePlanPrice = getString(R.string.dialog_upgrade_order_detail_price_tv_vimeo_plus_text);
                membershipFirst = getString(R.string.dialog_upgrade_order_detail_membership_first_tv_vimeo_plus_text);
                membershipSecond = getString(R.string.dialog_upgrade_order_detail_membership_second_tv_vimeo_plus_text);
                membershipThird = getString(R.string.dialog_upgrade_order_detail_membership_third_tv_vimeo_plus_text);
                membershipFourth = getString(R.string.dialog_upgrade_order_detail_membership_fourth_tv_vimeo_plus_text);
                break;
            case Constants.VIMEO_PLAN_PRO:
                upgradePlanName = getString(R.string.dialog_upgrade_order_detail_title_tv_vimeo_pro_text);
                upgradePlanPrice = getString(R.string.dialog_upgrade_order_detail_price_tv_vimeo_pro_text);
                membershipFirst = getString(R.string.dialog_upgrade_order_detail_membership_first_tv_vimeo_pro_text);
                membershipSecond = getString(R.string.dialog_upgrade_order_detail_membership_second_tv_vimeo_pro_text);
                membershipThird = getString(R.string.dialog_upgrade_order_detail_membership_third_tv_vimeo_pro_text);
                membershipFourth = getString(R.string.dialog_upgrade_order_detail_membership_fourth_tv_vimeo_pro_text);
                break;
            case Constants.VIMEO_PLAN_BUSSINESS:
                upgradePlanName = getString(R.string.dialog_upgrade_order_detail_title_tv_vimeo_bussiness_text);
                upgradePlanPrice = getString(R.string.dialog_upgrade_order_detail_price_tv_vimeo_bussiness_text);
                membershipFirst = getString(R.string.dialog_upgrade_order_detail_membership_first_tv_vimeo_bussiness_text);
                membershipSecond = getString(R.string.dialog_upgrade_order_detail_membership_second_tv_vimeo_bussiness_text);
                membershipThird = getString(R.string.dialog_upgrade_order_detail_membership_third_tv_vimeo_bussiness_text);
                membershipFourth = getString(R.string.dialog_upgrade_order_detail_membership_fourth_tv_vimeo_bussiness_text);
                break;
            default:
                break;
                upgradePlanNameTv.setText(upgradePlanName);
                upgradePlanPriceTv.setText(upgradePlanPrice);
                membershipFirstTv.setText(membershipFirst);
                membershipSecondTv.setText(membershipSecond);
                membershipThirdTv.setText(membershipThird);
                membershipFourthTv.setText(membershipFourth);
        }
        dialog.show();
        closeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.upgrade_order_payment_choose_alipay_cb:
                break;
            case R.id.upgrade_order_payment_choose_wechat_pay_cb:
                break;
            case R.id.upgrade_order_payment_choose_other_pay_cb:
                break;
            default:
                break;
        }
    }

    private void initSpinner(){
        otherPaySpinner = (Spinner) findViewById(R.id.upgrade_order_payment_choose_other_pay_spinner);
        ArrayAdapter<String> arrayAdapter;
        String[] array = getResources().getStringArray(R.array.choose_other_payment);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_spinner_text, array);
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        otherPaySpinner.setAdapter(arrayAdapter);
        otherPaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    otherPaySpinner.setVisibility(Spinner.GONE);
                    otherPayLv.setVisibility(ListView.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
