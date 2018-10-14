package com.menglingpeng.vonvimeo.mvp.view.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;

public class GeneralFragment extends BaseFragment implements RecyclerView, View.OnClickListener{

    private EditText titleEt;
    private EditText descEt;
    private ImageView thumb1Iv;
    private ImageView thumb2Iv;
    private Button editThumbBt;
    private RadioGroup watchRg;
    private RadioButton anyoneWatchRb;
    private RadioButton iWatchRb;
    private RadioButton followWatchRb;
    private RadioButton chooseWatchRb;
    private RadioButton passwordWatchRb;
    private RadioGroup commentRg;
    private RadioButton anyoneCommentRb;
    private RadioButton noOneCommentRb;
    private RadioButton followCommentRb;
    private Video video;
    private String privacy;
    private Dialog editThumbnailDialog;
    public static final int REQUEST_PICTURE_CODE = 1;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_general;
    }

    @Override
    protected void initView() {

        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        titleEt = (EditText)rootView.findViewById(R.id.general_title_et);
        thumb1Iv = (ImageView)rootView.findViewById(R.id.general_thumb_1_iv);
        thumb2Iv = (ImageView)rootView.findViewById(R.id.general_thumb_2_iv);
        editThumbBt = (Button)rootView.findViewById(R.id.general_edit_thumbnail_bt);
        descEt = (EditText)rootView.findViewById(R.id.general_desc_et);
        watchRg = (RadioGroup)rootView.findViewById(R.id.general_privacy_watch_settings_rg);
        anyoneWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_anyone_rb);
        iWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_i_rb);
        followWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_follow_rb);
        chooseWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_choose_rb);
        passwordWatchRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_only_with_password_rb);
        commentRg = (RadioGroup)rootView.findViewById(R.id.general_privacy_comment_settings_rg);
        anyoneCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_anyone_rb);
        noOneCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_no_one_rb);
        followCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_only_follow_rb);
    }

    @Override
    protected void initData() {

        titleEt.setText(video.getName());
        descEt.setText(video.getDescription());
        ImageLoader.load(this, video.getPictures().getUri(), thumb1Iv, false);
        watchRg.check(R.id.general_privacy_watch_settings_anyone_rb);
        watchRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.upload_privacy_settings_anyone_rb:
                        anyoneWatchRb.setText(getString(R.sting.general_privacy_watch_settings_anyone_rb_text));
                        privacy = Constants.PRIVACY_ANYONE;

                        break;
                    case R.upload_privacy_settings_only_i_rb:
                        iWatchRb.setText((R.sting.general_privacy_watch_settings_only_i_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I;
                        break;
                    case R.upload_privacy_settings_only_follow_rb:
                        followWatchRb.setText((R.sting.general_privacy_watch_settings_only_follow_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    case R.upload_privacy_settings_only_choose_rb:
                        chooseWatchRb.setText((R.sting.general_privacy_watch_settings_only_choose_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_CHOOSE;
                        break;
                    case R.id.upload_privacy_settings_only_with_password_rb:
                        passwordWatchRb.setText((R.sting.general_privacy_watch_settings_only_with_password_rb_text));
                        privacy = Constants.PRIVACY_WITH_A_PASSWORD;
                        break;
                    default:
                        break;
                }
            }
        });
        commentRg.check(R.id.general_privacy_comment_settings_anyone_rb);
        commentRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.upload_privacy_settings_anyone_rb:
                        anyoneCommentRb.setText(getString(R.sting.general_privacy_comment_settings_anyone_rb_text));
                        privacy = Constants.PRIVACY_ANYONE;

                        break;
                    case R.upload_privacy_settings_only_i_rb:
                        noOneCommentRb.setText((R.sting.general_privacy_comment_settings_no_one_rb));
                        privacy = Constants.PRIVACY_NO_ONE;
                        break;
                    case R.upload_privacy_settings_only_follow_rb:
                        followCommentRb.setText((R.sting.general_privacy_comment_settings_only_follow_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showEditThumbnailDialog(){
        TextView selectFromVideoTv;
        TextView uploadTv;
        TextView randomTv;
        editThumbnailDialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_thumbnail_in_general_fragment, null);
        Window window = editThumbnailDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.UploadChoosedialogStyle);
        window.getDecorView().setPadding(0, 0 , 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        editThumbnailDialog.setContentView(dialogView);
        selectFromVideoTv = (TextView)dialogView.findViewById(R.id.edit_thumb_select_from_video_tv);
        uploadTv = (TextView)dialogView.findViewById(R.id.edit_thumb_upload_tv);
        randomTv = (TextView)dialogView.findViewById(R.id.edit_thumb_random_tv);
        editThumbnailDialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.general_thumb_1_iv:
                break;
            case R.id.general_thumb_2_iv:
                break;
            case R.id.general_edit_thumbnail_bt:
                showEditThumbnailDialog();
                break;
            case R.id.edit_thumb_select_from_video_tv:
                break;
            case R.id.edit_thumb_upload_tv:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PICTURE_CODE);
                break;
            case R.id.edit_thumb_random_tv:
                break;
            default:
                break;
        }
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }

}
