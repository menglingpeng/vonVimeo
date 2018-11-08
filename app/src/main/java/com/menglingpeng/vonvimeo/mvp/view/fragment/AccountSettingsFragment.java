package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

public class AccountSettingsFragment extends BaseFragment implements View.OnClickListener{

    private User user;
    private Context context;
    private EditText nameEt;
    private TextView emailTv;
    private TextView emailEditTv;
    private RadioGroup pronounsRg;
    private Button generalSaveBt;
    private ImageView ownerAvatarIv;
    private TextView ownerNametv;
    private TextView ownerEmailTv;
    private EditText newPasswordEt;
    private EditText confirmPasswordEt;
    private Button passwordSaveBt;

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
        generalSaveBt = (Button) rootView.findViewById(R.id.account_settings_general_save_bt);
        newPasswordEt = (EditText)rootView.findViewById(R.id.account_settings_password_new_et);
        confirmPasswordEt = (EditText)rootView.findViewById(R.id.account_settings_password_confirm_et);
        passwordSaveBt = (Button) rootView.findViewById(R.id.account_settings_password_save_bt);
        ownerAvatarIv = (ImageView)rootView.findViewById(R.id.owner_avatar_iv);
        ownerNametv = (TextView)rootView.findViewById(R.id.owner_name_tv);
        ownerEmailTv = (TextView)rootView.findViewById(R.id.owner_email_tv);
    }

    @Override
    protected void initData() {
        nameEt.setText(user.getName());
        emailTv.setText(user.);
        ImageLoader.loadCricleImage(context, user.getPictures().getUri(), ownerAvatarIv);
        ownerNametv.setText(user.getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_settings_general_email_edit_tv:
                break;
            case R.id.account_settings_general_save_bt:
                break;
            case R.id.account_settings_password_save_bt:
                break;
            default:
                break;
        }
    }
}
