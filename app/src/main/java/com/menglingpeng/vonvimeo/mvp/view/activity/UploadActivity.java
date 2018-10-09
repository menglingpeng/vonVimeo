package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

public class UploadActivity extends BaseActivity implements RecyclerView, View.OnClickListener{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar progressBar;
    private String weeklyStorageCountTv;
    private String totalStorageCountTv;
    private Button uploadBt;
    private RadioGroup radioGroup;
    private RadioButton anyoneRb;
    private RadioButton iRb;
    private RadioButton followRb;
    private RadioButton chooseRb;
    private RadioButton passwordRb;
    private String title;
    private User user;
    private String userId;
    private String privacy;
    public static final int REQUEST_VIDEO_CODE = 1;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_upload;
    }

    @Override
    protected void initViews() {
        super.initViews();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.upload_cdl);
        toolbar = (Toolbar) findViewById(R.id.upload_tb);
        progressBar = (ProgressBar)findViewById(R.id.upload_storage_pb);
        weeklyStorageCountTv = (TextView)findViewById(R.id.weekly_upload_storage_count_iv);
        totalStorageCountTv = (TextView)findViewById(R.id.total_upload_storage_count_tv);
        radioGroup = (RadioGroup)findViewById(R.id.upload_privacy_settings_rg);
        anyoneRb = (RadioButton)findViewById(R.id.upload_privacy_settings_anyone_rb);
        iRb = (RadioButton)findViewById(R.id.upload_privacy_settings_only_i_rb);
        followRb = (RadioButton)findViewById(R.id.upload_privacy_settings_only_follow_rb);
        chooseRb = (RadioButton)findViewById(R.id.upload_privacy_settings_only_choose_rb);
        passwordRb = (RadioButton)findViewById(R.id.upload_privacy_settings_only_with_password_rb);
        uploadBt = (Button)findViewById(R.id.upload_bt);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        radioGroup.check(R.id.upload_privacy_settings_anyone_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.upload_privacy_settings_anyone_rb:
                        anyoneRb.setText(getString(R.sting.upload_privacy_settings_anyone_rb_text));
                        privacy = Constants.PRIVACY_ANYONE;

                        break;
                    case R.upload_privacy_settings_only_i_rb:
                        anyoneRb.setText((R.sting.upload_privacy_settings_only_i_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I;
                        break;
                    case R.upload_privacy_settings_only_follow_rb:
                        anyoneRb.setText((R.sting.upload_privacy_settings_only_follow_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_FOLLOW;
                        break;
                    case R.upload_privacy_settings_only_choose_rb:
                        anyoneRb.setText((R.sting.upload_privacy_settings_only_choose_rb_text));
                        privacy = Constants.PRIVACY_ONLY_I_CHOOSE;
                        break;
                    case R.id.upload_privacy_settings_only_with_password_rb:
                        passwordRb.setText((R.sting.upload_privacy_settings_only_with_password_rb_text));
                        privacy = Constants.PRIVACY_WITH_A_PASSWORD;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.upgrade_storage_tv:
                break;
            case R.id.upload_bt:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_VIDEO_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                /** 数据库查询操作。
                 * 第一个参数 uri：为要查询的数据库+表的名称。
                 * 第二个参数 projection ： 要查询的列。
                 * 第三个参数 selection ： 查询的条件，相当于SQL where。
                 * 第三个参数 selectionArgs ： 查询条件的参数，相当于 ？。
                 * 第四个参数 sortOrder ： 结果排序。
                 */
                Cursor cursor = cr.query(uri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        // 视频ID:MediaStore.Audio.Media._ID
                        int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        // 视频名称：MediaStore.Audio.Media.TITLE
                        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                        // 视频路径：MediaStore.Audio.Media.DATA
                        String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                        // 视频时长：MediaStore.Audio.Media.DURATION
                        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                        // 视频大小：MediaStore.Audio.Media.SIZE
                        long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));

                        // 视频缩略图路径：MediaStore.Images.Media.DATA
                        String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        // 缩略图ID:MediaStore.Audio.Media._ID
                        int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                        // 方法一 Thumbnails 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 ContentResolver，第二个参数为视频缩略图ID， 第三个参数kind有两种为：
                        // MICRO_KIND和MINI_KIND 字面意思理解为微型和迷你两种缩略模式，前者分辨率更低一些。
                        Bitmap bitmap1 = MediaStore.Video.Thumbnails.getThumbnail(cr, imageId,
                                MediaStore.Video.Thumbnails.MICRO_KIND, null);

                        // 方法二 ThumbnailUtils 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 视频/缩略图的位置，第二个依旧是分辨率相关的kind
                        Bitmap bitmap2 = ThumbnailUtils.createVideoThumbnail(imagePath, MediaStore.Video.Thumbnails.MICRO_KIND);
                        // 如果追求更好的话可以利用 ThumbnailUtils.extractThumbnail 把缩略图转化为的制定大小
//                        ThumbnailUtils.extractThumbnail(bitmap, width,height ,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

                    }
                    cursor.close();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

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

