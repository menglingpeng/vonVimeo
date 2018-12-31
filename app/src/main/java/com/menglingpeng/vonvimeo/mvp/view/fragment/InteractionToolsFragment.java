package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.view.activity.UpgradeActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class InteractionToolsFragment extends BaseFragment implements View.OnClickListener, TextWatcher{

    private Video video;
    private String videoId;
    private User user;
    private Context context;
    private Button titleUpgradeBt;
    private Button beforeVideoUpgradeBt;
    private EditText beforeVideoEt;
    private EditText duringVideoEt;
    private EditText afterVideoEt;
    private String beforeVideo;
    private String duringVideo;
    private String afterVideo;
    private Dialog saveSettingsDialog;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_interaction_tools;
    }

    @Override
    protected void initView() {
        context = getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        videoId = IdStringUtil.getId(video.getUri());
        user = video.getUser();
        titleUpgradeBt = (Button)rootView.findViewById(R.id.interaction_tools_title_upgrade_bt);
        beforeVideoUpgradeBt = (Button)rootView.findViewById(R.id.interaction_tools_settins_before_video_upgrade_bt);
        beforeVideoEt = (EditText)rootView.findViewById(R.id.interaction_tools_settins_before_video_et);
        duringVideoEt = (EditText)rootView.findViewById(R.id.interaction_tools_settins_during_video_et);
        afterVideoEt = (EditText)rootView.findViewById(R.id.interaction_tools_settins_after_video_et);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.interaction_tools_title_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.interaction_tools_settins_before_video_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        showSaveSettingsDialog();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        showSaveSettingsDialog();
    }

    private void showSaveSettingsDialog(){
        saveSettingsDialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_save_video_settings, null);
        Window window = saveSettingsDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.UploadChoosedialogStyle);
        window.getDecorView().setPadding(0, 0 , 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        saveSettingsDialog.setContentView(dialogView);
        Button cancelBt = (Button)dialogView.findViewById(R.id.dialog_save_video_settings_cancel_bt) ;
        Button saveBt = (Button)dialogView.findViewById(R.id.dialog_save_video_settings_save_bt) ;
        saveSettingsDialog.show();
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettingsDialog.cancel();
            }
        });
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beforeVideo = beforeVideoEt.getText().toString();
                duringVideo = duringVideoEt.getText().toString();
                afterVideo = afterVideoEt.getText().toString();
            }
        });
    }
}
