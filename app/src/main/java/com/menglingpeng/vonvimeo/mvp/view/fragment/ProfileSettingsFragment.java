package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class ProfileSettingsFragment extends BaseFragment {

    private User user;
    private String userId;
    private Context context;
    private ImageView pictureIv;
    private TextView uploadNewImageTv;
    private Dialog uploadChooseDialog;
    private String currentPhotoPath;
    private String uploadFilePath;
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 111;
    private static final int REQUEST_PICK_IMAGE_PERMISSION = 333;
    private static final int REQ_TAKE_PHOTO = 222;
    private static final int REQ_PICK_IMAGE = 444;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_profile_settings;
    }

    @Override
    protected void initView() {
        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        userId = IdStringUtil.getId(user.getUri());
        pictureIv = (ImageView)rootView.findViewById(R.id.profile_settings_profile_picture_iv);
        uploadNewImageTv = (TextView)rootView.findViewById(R.id.profile_settings_profile_picture_upload_new_image_tv);
        uploadNewImageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUploadChooseDialog();
            }
        });
    }

    @Override
    protected void initData() {

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
                    if(takePhoto.resolveActivity(context.getPackageManager()) != null){
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
        String storagePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        //创建临时照片文件
        File photoFile = new File(storagePath + File.separator + photoFileName + ".JPG");
        currentPhotoPath = photoFile.getAbsolutePath();
        return  photoFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    Cursor cursor = context.getContentResolver().query(selectedImage, imagePathColumns, null,
                            null, null);
                    cursor.moveToFirst();
                    uploadFilePath = getString(cursor.getColumnIndex(imagePathColumns[0]));
                }
                break;
        }
        File file = new File(uploadFilePath);
        uploadFileNmae = file.getName();
    }
}
