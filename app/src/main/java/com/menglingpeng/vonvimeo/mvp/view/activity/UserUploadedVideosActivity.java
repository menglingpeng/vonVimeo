package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.io.File;

public class UserUploadedVideosActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private String soryType;
    private String type;
    private Context context;
    public static final int REQUEST_VIDEO_CODE = 1;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_uploaded_videos;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.upload_video_fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.upload_videos_cdl);
        toolbar = (Toolbar) findViewById(R.id.upload_videos_tb);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton.setVisibility(FloatingActionButton.VISIBLE);
        replaceFragment(RecyclerFragment.newInstance(type));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_VIDEO_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_uploaded_videos_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.uploaded_videos_share:
                shareToWeichat();
                break;
            case R.id.uploaded_videos_delete:
                showDeleteUploadedVideoDialog();
            case R.id.project_detail_sort_title:
                if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER)) {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER_SORY_BY_TITLE;
                }else {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_TITLE;
                }
                replaceFragment(RecyclerFragment.newInstance(type));

                break;
            case R.id.uploaded_videos_sort_date_modified:
                if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER)) {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER_SORY_BY_DATE_MODIFIED;
                }else {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DATE_MODIFIED;

                }
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.uploaded_videos_sort_date_added:
                if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER)) {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER_SORY_BY_DATE_ADDED;
                }else {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DATE_ADDED;
                }
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            case R.id.uploaded_videos_sort_duration:
                if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER)) {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER_SORY_BY_DURATION;
                }else {
                    type = Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DURATION;
                }
                replaceFragment(RecyclerFragment.newInstance(type));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareToWeichat(){

    }

    private void showDeleteUploadedVideoDialog(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.create_a_bucket);
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

        dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_VIDEO_CODE) {
            if (resultCode == RESULT_OK) {
                File videoFile = null;
                int videoId;
                String title;
                String videoPath;
                int duration;
                long size;
                String imagePath;
                int imageId;
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
                        videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        // 视频名称：MediaStore.Audio.Media.TITLE
                        title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                        // 视频路径：MediaStore.Audio.Media.DATA
                        videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                        // 视频时长：MediaStore.Audio.Media.DURATION
                        duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                        // 视频大小：MediaStore.Audio.Media.SIZE
                        size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));

                        // 视频缩略图路径：MediaStore.Images.Media.DATA
                        imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        // 缩略图ID:MediaStore.Audio.Media._ID
                        imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                        /*方法一 Thumbnails 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        第一个参数为 ContentResolver，第二个参数为视频缩略图ID，
                        第三个参数kind有两种为：MICRO_KIND和MINI_KIND 字面意思理解为微型和迷你两种缩略模式，
                        前者分辨率更低一些。*/
                        Bitmap bitmap1 = MediaStore.Video.Thumbnails.getThumbnail(cr, imageId,
                                MediaStore.Video.Thumbnails.MICRO_KIND, null);

                        // 方法二 ThumbnailUtils 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 视频/缩略图的位置，第二个依旧是分辨率相关的kind
                        Bitmap bitmap2 = ThumbnailUtils.createVideoThumbnail(imagePath,
                                MediaStore.Video.Thumbnails.MICRO_KIND);
                        // 如果追求更好的话可以利用 ThumbnailUtils.extractThumbnail 把缩略图转化为的制定大小
//                        ThumbnailUtils.extractThumbnail(bitmap, width,height ,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                        videoFile = new File(videoPath);
                    }
                    cursor.close();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
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
        switch (requestType){
            case Constants.REQUEST_DELETE_A_VIDEO_UPLOADED_BY_USER:
                if(json.indexOf(Constants.CODE_204_NO_CONTENT) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.string.delete_a_video_http_status_code_204));
                }else if(json.indexOf(Constants.CODE_403_FORBIDDEN) != -1){
                    SnackbarUtils.showSnackShort(context ,coordinatorLayout, getString(
                            R.string.delete_a_video_http_status_code_403));
                }
                break;
            case Constants.REQUEST_UPLOADED_A_VIDEO:

                break;
            case R.id.project_detail_sort_title:
                break;
            case R.id.uploaded_videos_sort_duration:
                break;

            default:
                break;
        }
    }
}
