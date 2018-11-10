package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.text.method.PasswordTransformationMethod;
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
    private TextView addBlockedPersonTv;
    private TextView membershipTv;
    private Button upgradeBt;
    private Button savePrivacyBt;

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
        addBlockedPersonTv = (TextView)rootView.findViewById(R.id.account_settings_privacy_add_blocked_person_tv);
        membershipTv = (TextView)rootView.findViewById(R.id.account_settings_privacy_membership_tv);
        upgradeBt = (Button)rootView.findViewById(R.id.account_settings_privacy_membership_upgrade_bt);
        savePrivacyBt = (Button)rootView.findViewById(R.id.account_settings_privacy_save_bt);
    }

    @Override
    protected void initData() {
        nameEt.setText(user.getName());
        emailTv.setText(user.);
        ImageLoader.loadCricleImage(context, user.getPictures().getUri(), ownerAvatarIv);
        ownerNametv.setText(user.getName());
        membershipTv.setText(user.getAccount());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_settings_general_email_edit_tv:
                showEditEmailDialog();
                break;
            case R.id.account_settings_general_save_bt:
                saveGeneralSettings();
                break;
            case R.id.account_settings_password_save_bt:
                savePsswordSettings();
                break;
            case R.id.account_settings_privacy_add_blocked_person_tv:
                showAddAPersonToBlockDialog();
                break;
            case R.id.account_settings_privacy_membership_upgrade_bt:
                break;
            case R.id.account_settings_privacy_save_bt:
                break;
            default:
                break;
        }
    }

    private void showEditEmailDialog(){
        final AlertDialog dialog;
        ImageView closeIv;
        final EditText newAddressEt;
        final EditText confirmAddressEt;
        final EditText vimeoPasswordEt;
        final String newAddress;
        final String confirmAddress;
        final String vimeoPassword;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_my_email, null);
        builder.setView(dialogView);
        dialog = builder.create();
        closeIv = (ImageView) dialogView.findViewById(R.id.dialog_edit_email_close_iv);
        newAddressEt = (EditText) dialogView.findViewById(R.id.dialog_edit_email_new_address_et);
        confirmAddressEt = (EditText) dialogView.findViewById(R.id.dialog_edit_email_confirm_address_et);
        vimeoPasswordEt = (EditText) dialogView.findViewById(R.id.dialog_edit_email_vimeo_password_et);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.sting.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newAddress = newAddressEt.getText().toString();
                confirmAddress = confirmAddressEt.getText().toString();
                vimeoPassword = vimeoPasswordEt.getText().toString();
            }
        });
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirmAddressEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                }else {
                    //失去焦点验证两次输入的Email address是否一致。
                    newAddress = newAddressEt.getText().toString();
                    confirmAddress = confirmAddressEt.getText().toString();

                    if(newAddress.equals(confirmAddress)){

                    }else {

                    }
                }
            }
        });
        vimeoPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        dialog.show();
    }

    private void saveGeneralSettings(){

    }

    private void savePsswordSettings(){

    }

    private void showAddAPersonToBlockDialog(){
        final AlertDialog dialog;
        ImageView closeIv;
        final EditText userIdOrURLEt;
        final String userIdOrURL;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_a_person_to_block, null);
        builder.setView(dialogView);
        dialog = builder.create();
        closeIv = (ImageView) dialogView.findViewById(R.id.dialog_add_a_person_to_block_close_iv);
        userIdOrURLEt = (EditText) dialogView.findViewById(R.id.dialog_add_a_person_to_block_id_url_et);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.sting.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
