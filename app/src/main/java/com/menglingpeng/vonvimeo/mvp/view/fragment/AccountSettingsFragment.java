package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class AccountSettingsFragment extends BaseFragment implements View.OnClickListener{

    private User user;
    private Context context;
    private EditText nameEt;
    private TextView emailTv;
    private TextView emailEditTv;
    private RadioGroup pronounsRg;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_account_settings;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        nameEt = (EditText)rootView.findViewById(R.id.account_settings_general_name_et);
        emailTv = (TextView)rootView.findViewById(R.id.account_settings_general_email_tv);
        emailEditTv = (TextView)rootView.findViewById(R.id.account_settings_general_email_edit_tv);
        pronounsRg = (RadioGroup)rootView.findViewById(R.id.account_settings_general_pronouns_rg);
    }

    @Override
    protected void initData() {
        nameEt.setText(user.getName());
        emailTv.setText(user.);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_settings_general_email_edit_tv:
                break;
            default:
                break;
        }
    }
}
