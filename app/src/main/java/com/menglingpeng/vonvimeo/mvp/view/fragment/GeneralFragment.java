package com.menglingpeng.vonvimeo.mvp.view.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.activity.UpgradeActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

public class GeneralFragment extends BaseFragment implements RecyclerView, View.OnClickListener, Switch.OnCheckedChangeListener{

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
    private RadioButton withPrivateLinkWatchRb;
    private RadioButton hideTheVideoRb;
    private RadioGroup commentRg;
    private RadioButton anyoneCommentRb;
    private RadioButton noOneCommentRb;
    private RadioButton followCommentRb;
    private Button watchUpgradeBt;
    private Button embedUpgradeBt;
    private Button commentUpgradeBt;
    private Switch downloadSwich;
    private Switch collectionsSwitch;
    private Button downloadUpgradeBt;
    private Video video;
    private User user;
    private String privacy;
    private String videoId;
    private Dialog editThumbnailDialog;
    private ImageView createAlbumIv;
    private ImageView createChannelIv;
    private ImageView createGroupIv;
    private TextView noAlbumsTv;
    private TextView noChannelTv;
    private TextView noGroupTv;
    private ListView albumsLv;
    private ListView channelsLv;
    private ListView groupsLv;
    private Button portfoliosUpgradeBt;
    private Button replaceVideoBt;
    private Button deleteVideoBt;
    private Context context;
    private String type;
    private int albumsCount;
    private int channelsCount;
    private int groupsCount;

    public static final int REQUEST_PICTURE_CODE = 1;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_general;
    }

    @Override
    protected void initView() {

        context =getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        videoId = IdStringUtil.getId(video.getUri());
        user = video.getUser();
        albumsCount = Integer.valueOf(user.getMetadata().getConnections().getAlbums().getTotal());
        channelsCount = Integer.valueOf(user.getMetadata().getConnections().getChannels().getTotal());
        groupsCount = Integer.valueOf(user.getMetadata().getConnections().getGroups().getTotal());
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
        withPrivateLinkWatchRb = (RadioButton)rootView.findViewById(R.id.
                general_privacy_watch_settings_people_with_the_private_link_rb_rb);
        hideTheVideoRb = (RadioButton)rootView.findViewById(R.id.general_privacy_watch_settings_hide_the_video_rb);
        commentRg = (RadioGroup)rootView.findViewById(R.id.general_privacy_comment_settings_rg);
        anyoneCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_anyone_rb);
        noOneCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_no_one_rb);
        followCommentRb = (RadioButton)rootView.findViewById(R.id.general_privacy_comment_settings_only_follow_rb);
        commentUpgradeBt = (Button)rootView.findViewById(R.id.general_privacy_commentsettings_upgrade_bt);
        embedUpgradeBt = (Button)rootView.findViewById(R.id.general_privacy_embed_settings_upgrade_bt);
        watchUpgradeBt = (Button)rootView.findViewById(R.id.general_privacy_watch_settings_upgrade_bt);
        downloadSwich = (Switch)rootView.findViewById(R.id.general_privacy_settings_download_switch);
        deleteVideoBt = (Button) rootView.findViewById(R.id.general_privacy_settings_download_switch_upgrade_bt);
        collectionsSwitch = (Switch)rootView.findViewById(R.id.general_privacyt_settings_collections_switch);
        createAlbumIv = (ImageView)rootView.findViewById(R.id.general_collections_albums_settings_create_iv);
        createChannelIv = (ImageView)rootView.findViewById(R.id.general_collections_channels_settings_create_iv);
        createGroupIv = (ImageView)rootView.findViewById(R.id.general_collections_groups_settings_create_iv);
        noAlbumsTv = (TextView)rootView.findViewById(R.id.general_collections_albums_settings_no_check_tv);
        noChannelTv = (TextView)rootView.findViewById(R.id.general_collections_channels_settings_no_check_tv);
        noGroupTv = (TextView)rootView.findViewById(R.id.general_collections_groups_settings_no_check_tv);
        albumsLv = (ListView)rootView.findViewById(R.id.general_collections_albums_settings_check_lv);
        channelsLv = (ListView)rootView.findViewById(R.id.general_collections_channels_settings_check_lv);
        groupsLv = (ListView)rootView.findViewById(R.id.general_collections_groups_settings_check_lv);
        portfoliosUpgradeBt = (Button)rootView.findViewById(R.id.general_collections_portfolios_settings_upgrade_bt);
        replaceVideoBt = (Button)rootView.findViewById(R.id.general_replace_video_bt);
        deleteVideoBt = (Button)rootView.findViewById(R.id.general_delete_video_bt);
    }

    @Override
    protected void initData() {

        titleEt.setText(video.getName());
        descEt.setText(video.getDescription());
        ImageLoader.load(this, video.getPictures().getUri(), thumb1Iv, false);
        if(albumsCount != 0){
            noAlbumsTv.setVisibility(TextView.GONE);
            albumsLv.setVisibility(ListView.VISIBLE);
        }
        if(channelsCount != 0){
            noChannelTv.setVisibility(TextView.GONE);
            channelsLv.setVisibility(ListView.VISIBLE);
        }
        if(groupsCount != 0){
            noGroupTv.setVisibility(TextView.GONE);
            groupsLv.setVisibility(ListView.VISIBLE);
        }
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
        Intent intent = null;
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
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PICTURE_CODE);
                break;
            case R.id.edit_thumb_random_tv:
                break;
            case R.id.general_collections_albums_settings_create_iv:
                showCreateAlbumDialog();
                break;
            case R.id.general_collections_channels_settings_create_iv:
                showCreateChannelDialog();
                break;
            case R.id.general_collections_groups_settings_create_iv:
                showCreateGroupDialog();
                break;
            case R.id.general_privacy_watch_settings_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.general_privacy_embed_settings_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.general_privacy_comment_settings_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.general_collections_portfolios_settings_upgrade_bt:
                intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.general_replace_video_bt:
                showReplaceVideoDialog();
                break;
            case R.id.general_delete_video_bt:
                showDeleteVideoDialog();
                break;
            default:
                break;
        }
    }

    private void showCreateAlbumDialog() {
        final TextInputEditText albumNameEt;
        final TextInputEditText albumDescEt;
        final RadioGroup radioGroup;
        final RadioButton radioButton1;
        final RadioButton radioButton2;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.create_an_album_dialog_message, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        albumNameEt = (TextInputEditText) dialogView.findViewById(R.id.album_name_tiet);
        albumDescEt = (TextInputEditText) dialogView.findViewById(R.id.album_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.album_privacy_settings_rg);
        radioButton1 = (RadioButton)dialogView.findViewById(R.id.album_privacy_settings_anyone_rb);
        radioButton2  = (RadioButton)dialogView.findViewById(R.id.album_privacy_settings_people_with_password_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.album_privacy_settings_anyone_rb:
                        radioButton1.setText(Constants.PRIVACY_ANYONE);
                        break;
                    case R.id.album_privacy_settings_people_with_password_rb:
                        radioButton2.setText(Constants.PRIVACY_WITH_A_PASSWORD);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = albumNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string
                            .the_name_of_album_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, albumNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, albumDescEt.getText().toString());
                    if(radioButton1.isChecked()) {
                        map.put(Constants.PRIVACY, radioButton1.getText().toString());
                    }else {
                        map.put(Constants.PRIVACY, radioButton2.getText().toString());
                    }
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(getActivity(), type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, context);
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        albumNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showCreateChannelDialog() {
        final TextInputEditText channleNameEt, channleDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_a_channle, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        channleNameEt = (TextInputEditText) dialogView.findViewById(R.id.channle_name_tiet);
        channleDescEt = (TextInputEditText) dialogView.findViewById(R.id.channle_desc_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = channleNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, channleNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, channleDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(this, type,
                            Constants.REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, context);
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        channleNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showCreateGroupDialog() {
        final TextInputEditText groupNameEt;
        final TextInputEditText groupDescEt;
        final RadioGroup radioGroup;
        final RadioButton anyoneRb;
        final RadioButton membersRb;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_a_group, null);
        builder.setTitle(R.string.create_a_group);
        builder.setView(dialogView);
        groupNameEt = (TextInputEditText) dialogView.findViewById(R.id.group_name_tiet);
        groupDescEt = (TextInputEditText) dialogView.findViewById(R.id.group_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.group_privacy_settings_rg);
        anyoneRb = (RadioButton)dialogView.findViewById(R.id.group_privacy_settings_anyone_rb);
        membersRb = (RadioButton)dialogView.findViewById(R.id.group_privacy_settings_member_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.group_privacy_settings_anyone_rb:
                        break;
                    case R.id.group_privacy_settings_members_rb:
                        break;
                    default:
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = groupNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string
                            .the_name_of_group_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, groupNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, groupDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_A_ALBUM;
                    RecyclerPresenter presenter = new RecyclerPresenter(getActivity(), type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, context);
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string
                            .snack_create_a_album_text));
                }

            }
        });
        groupNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showReplaceVideoDialog(){

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_replace_video_title);
        builder.setMessage(R.string.dialog_replace_video_msg);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.sting.replace, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog = builder.create();
        dialog.show();
    }

    private void showDeleteVideoDialog(){

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_delete_video_title);
        builder.setMessage(R.string.dialog_delete_video_msg);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.sting.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (requestType){
            case Constants.REQUEST_LIST_ALL_ALBUMS_OF_A_USER_SORT_BY_DATE:
                break;
            case Constants.REQUEST_LIST_CHANNELS:
                break;
            case Constants.REQUEST_LIST_GROUPS:
                break;
            case Constants.REQUEST_DELETE_A_VIDEO:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){

                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){

                }
                break;
            default:
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.general_privacy_settings_download_switch:
                break;
            case R.id.general_privacyt_settings_collections_switch:
                break;
                default:
                    break;
        }
    }
}
