package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.BankCardTextWatcher;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

public class AddCardActivity extends BaseActivity implements View.OnClickListener{

    private String title;
    private Context context;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private TextView cardHolderNameTv;
    private EditText cardNumberEt;
    private Button verificateBt;
    private String cardId;
    private BankCardTextWatcher bankCardTextWatcher;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_add_card;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        title = getString(R.string.activity_add_card_title);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.add_card_cdl);
        toolbar = (Toolbar) findViewById(R.id.add_card_tb);
        cardHolderNameTv = (TextView) findViewById(R.id.cardholder_name_tv);
        cardNumberEt = (EditText) findViewById(R.id.card_number_et);
        verificateBt = (Button) findViewById(R.id.verficate_card_number_bt);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cardNumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bankCardTextWatcher.bind(cardNumberEt);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verficate_card_number_bt:
                cardId = cardNumberEt.getText().toString();
                if(checkCardNumber(cardId)){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout,
                            getText(R.string.snackbar_verificate_card_number_success));
                }else {
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout,
                            getText(R.string.snackbar_verificate_card_number_error));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    private boolean checkCardNumber(String cardId){

        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
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
}
