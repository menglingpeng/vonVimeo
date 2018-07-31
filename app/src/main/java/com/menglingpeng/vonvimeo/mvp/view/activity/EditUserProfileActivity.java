package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.UserProjectActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class EditUserProfileActivity extends BaseActivity implements RecyclerView , View.OnClickListener{

   private Toolbar toolbar;
   private CoordinatorLayout coordinatorLayout;
   private FloatingActionButton uploadFab;
   private Context context;
   private TextView avatarTv;
   private ImageView avatarIv;
   private TextView bioDescTv;
   private Button uploadAvatarbt;
   private EditText bioEt;
   private String bio;
   private TextView aboutTv;
   private EditText aboutEt;
   private String about;
   private TextView locationTv;
   private EditText locationEt;
   private String location;
   private TextView urlTv;
   private EditText urlEt;
   private String vimeoURL;
   private TextView websiteTv;
   private Button websiteBt;
   private Button saveBt;
   private String type;

    private Dialog uploadChooseDialog;
    private String currentPhotoPath;
    private String uploadFilePath;
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 111;
    private static final int REQUEST_PICK_IMAGE_PERMISSION = 333;
    private static final int REQ_TAKE_PHOTO = 222;
    private static final int REQ_PICK_IMAGE = 444;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_edit_user_profile;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.edit_user_cdl);
        toolbar = (Toolbar)findViewById(R.id.edit_user_tb);
        avatarTv = (TextView)findViewById(R.id.user_avatar_desc_tv);
        avatarIv = (ImageView)findViewById(R.id.user_avatar_iv);
        uploadAvatarbt = (Button)findViewById(R.id.user_avatar_upload_bt);
        bioDescTv = (TextView)findViewById(R.id.edit_user_bio_tv);
        bioEt = (EditText)findViewById(R.id.edit_user_bio_et);
        aboutTv = (TextView)findViewById(R.id.edit_user_about_tv);
        aboutEt = (EditText)findViewById(R.id.edit_user_about_et);
        locationTv = (TextView)findViewById(R.id.edit_user_location_tv);
        locationEt = (EditText)findViewById(R.id.edit_user_location_et);
        urlTv = (TextView)findViewById(R.id.edit_user_vimeo_url_tv);
        urlEt = (EditText)findViewById(R.id.edit_user_vimeo_url_et);
        websiteTv = (TextView)findViewById(R.id.edit_user_website_tv);
        websiteBt = (Button)findViewById(R.id.edit_user_website_bt);
        saveBt = (Button)findViewById(R.id.edit_user_save_bt);

        uploadFab = (FloatingActionButton)findViewById(R.id.upload_picture_fab);
        toolbar.setTitle(getString(R.string.edit_user_profile_toobar_title));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        uploadFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData(){
        bio = bioEt.getText().toString();
        about = aboutEt.getText().toString();
        location = locationEt.getText().toString();
        vimeoURL = urlEt.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_avatar_upload_bt:
                showUploadChooseDialog();
                break;
            case R.id.edit_user_save_bt:
                editUser();
                break;
            case R.id.edit_user_website_bt:
                showAddWebsiteDialog();
                break;
            default:
                break;

        }
    }

    private void showUploadChooseDialog(){
        uploadChooseDialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_upload_user_picture_choose, null);
        Window window = uploadChooseDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.UploadChoosedialogStyle);
        window.getDecorView().setPadding(0, 0 , 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        uploadChooseDialog.setContentView(dialogView);
        Button bt_gallery = (Button)dialogView.findViewById(R.id.button_gallery) ;
        Button bt_camera = (Button)dialogView.findViewById(R.id.button_camera) ;
        uploadChooseDialog.show();
        bt_camera.setOnClickListener(new UploadChooseListener());
        bt_gallery.setOnClickListener(new UploadChooseListener());
    }

    class UploadChooseListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.button_gallery :
                    //申请图库权限
                    if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                            PackageManager.PERMISSION_GRANTED){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                    REQUEST_PICK_IMAGE_PERMISSION);
                        }
                    }
                    //调用图库
                    Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickImage, REQUEST_PICK_IMAGE_PERMISSION);
                    uploadChooseDialog.dismiss();
                    break;

                case R.id.button_camera :
                    //申请相机权限
                    if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.
                            PERMISSION_GRANTED){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest
                                    .permission.WRITE_EXTERNAL_STORAGE}, REQUEST_TAKE_PHOTO_PERMISSION);
                        }
                    }
                    //调用相机
                    Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //确保设备有相机应用接收Intent
                    if(takePhoto.resolveActivity(getPackageManager()) != null){
                        File photoFile = createPhotoFile();
                        if(photoFile != null){
                            //FileProvider 是一个特殊的 ContentProvider 的子类，
                            //它使用 content:// Uri 代替了 file:/// Uri. ，更便利而且安全的为另一个app分享文件
                            Uri photoUri = FileProvider.getUriForFile(context,
                                    "com.menglingpeng.vonvimeo.fileprovider", photoFile);
                            takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            startActivityForResult(takePhoto, REQ_TAKE_PHOTO);
                        }
                    }
                    uploadChooseDialog.dismiss();
                    break;
            }
        }
    }

    private File createPhotoFile(){
        //使用拍照时间命名
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String photoFileName = "IMG" + "_" +timeStamp ;
        //getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
        String storagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        //创建临时照片文件
        File photoFile = new File(storagePath + File.separator + photoFileName + ".JPG");
        currentPhotoPath = photoFile.getAbsolutePath();
        return  photoFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String uploadFileNmae = null;
        switch (requestCode){
            //获取照片的路径
            case REQ_TAKE_PHOTO :
                if(resultCode == RESULT_OK){
                    uploadFilePath = currentPhotoPath;
                }
                break;
            //获取选择图片路径
            case REQ_PICK_IMAGE :
                if(resultCode == RESULT_OK && data != null){
                    Uri selectedImage = data.getData();
                    String[] imagePathColumns ={MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, imagePathColumns, null,
                            null, null);
                    cursor.moveToFirst();
                    uploadFilePath = getString(cursor.getColumnIndex(imagePathColumns[0]));
                }
                break;
        }
        File file = new File(uploadFilePath);
        uploadFileNmae = file.getName();
    }

    private void showAddWebsiteDialog(){
        final TextInputEditText websiteNameEt;
        final TextInputEditText websiteURLEt;
        final TextInputEditText websiteDescEt;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_a_new_link, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        websiteNameEt = (TextInputEditText) dialogView.findViewById(R.id.website_name_tiet);
        websiteDescEt = (TextInputEditText) dialogView.findViewById(R.id.website_desc_tiet);
        websiteURLEt = (TextInputEditText)dialogView.findViewById(R.id.website_url_tiet);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = websiteNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_bucket_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, websiteNameEt.getText().toString());
                    map.put(Constants.URL, websiteURLEt.getText().toString());
                    map.put(Constants.DESCRIPTION, websiteDescEt.getText().toString());
                    type = Constants.REQUEST_REQUEST_CREATE_A_PROJECT;
                    RecyclerPresenter presenter = new RecyclerPresenter(EditUserProfileActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_bucket_text));
                }

            }
        });
        websiteNameEt.setFocusable(true);
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

    }


    private void editUser(){

    }
}
