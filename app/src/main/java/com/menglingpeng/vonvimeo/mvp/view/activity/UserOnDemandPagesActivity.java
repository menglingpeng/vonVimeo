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
import android.provider.ContactsContract;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.model.OnDemandPage;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.ProfileSettingsFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class UserOnDemandPagesActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private ImageView avatarIv;
    private TextView userNameTv;
    private TextView demandDescTv;
    private ProgressBar progressBar;
    private String type;
    private User user;
    private String sortType;
    private Context context;
    private String userId;
    private String onDemandId;
    private int checkedCounts;
    private String regionId;
    private String genreId;
    private String backgroundId;
    private OnDemandPage onDemandPage;
    private FloatingActionButton ftBt;
    private Dialog createPosterDialog;
    private String currentPhotoPath;
    private String uploadFilePath;
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 111;
    private static final int REQUEST_PICK_IMAGE_PERMISSION = 333;
    private static final int REQ_TAKE_PHOTO = 222;
    private static final int REQ_PICK_IMAGE = 444;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_user_on_demand_pages;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        user = (User)getIntent().getSerializableExtra(Constants.USER);
        userId = IdStringUtil.getId(user.getUri());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.demand_cdl);
        toolbar = (Toolbar) findViewById(R.id.demand_tb);
        avatarIv = (ImageView)findViewById(R.id.demand_user_avatar_iv);
        userNameTv = (TextView)findViewById(R.id.demand_user_name_tv);
        demandDescTv = (TextView)findViewById(R.id.demand_desc_tv);
        progressBar = (ProgressBar)findViewById(R.id.demand_pb);
        ftBt = (FloatingActionButton) findViewById(R.id.demand_ftb);
        title = user.getName();
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageLoader.loadCricleImage(context, user.getPictures().getUri(), avatarIv);
        userNameTv.setText(user.getName());
        type = Constants.REQUEST_GET_ALL_VIDEOS_OF_A_USER_ON_DEMAND_PAGES;
        replaceFragment(RecyclerFragment.newInstance(Constants.USER_ID, userId));
        ftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case  Constants.TAB_VIMEO_ONDEMAND_PAGES_POSTERS:
                        break;
                    case  Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS:
                        break;
                    case  Constants.TAB_VIMEO_ONDEMAND_PAGES_BACKGROUNDS:
                        break;
                     default:
                         break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.user_on_demand_pages_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_on_demand_pages_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.user_on_demand_pages_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.user_on_demand_pages_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.user_on_demand_pages_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.user_on_demand_pages_thumb:
                break;
            case R.id.user_on_demand_pages_detail:
                break;
            case R.id.user_on_demand_pages_all_checked:
                if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_VIDEOS)){

                } else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_BACKGROUNDS)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_PROMOTIONS)){

                }
                break;
            case R.id.user_on_demand_pages_no_checked:
                if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_VIDEOS)){

                } else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_BACKGROUNDS)){

                }else if(type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_PROMOTIONS)){

                }
                break;
            case R.id.user_on_demand_pages_delete_checked:
                remove(type);
                break;
            default:
                break;
        }
        if(type.indexOf(Constants.AUTH_USER) != -1) {
            replaceFragment(RecyclerFragment.newInstance(type));
        }else {
            replaceFragment(RecyclerFragment.newInstance(Constants.USER_ID, type));
        }
        return super.onOptionsItemSelected(item);
    }

    private void remove(String type){
        String title = null;
        if(checkedCounts == 1) {
            if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_VIDEOS)) {
                title = getString(R.string.dilog_remove_a_video_title);
                type = Constants.REQUEST_REMOVE_A_VIDEO_FROM_AN_ON_DEMAND_PAGE;
            } else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)) {
                title = getString(R.string.dilog_remove_a_genre_title);
                type = Constants.REQUEST_REMOVE_A_GENRE_FROM_AN_ON_DEMAND_PAGE;
            } else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)) {
                title = getString(R.string.dilog_remove_a_region_title);
                type = Constants.REQUEST_REMOVE_A_REGION_FROM_AN_ON_DEMAND_PAGE;
            } else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_BACKGROUNDS)) {
                title = getString(R.string.dilog_remove_a_background_title);
                type = Constants.REQUEST_REMOVE_A_BACKGROUND_FROM_AN_ON_DEMAND_PAGE;
            }else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_PROMOTIONS)) {
                title = getString(R.string.dilog_remove_a_promotion_title);
                type = Constants.REQUEST_REMOVE_A_PROMOTION_FROM_AN_ON_DEMAND_PAGE;
            }

        }else if(checkedCounts > 1){
            if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_VIDEOS)) {
                title = getString(R.string.dilog_remove_a_list_of_videos_title);
                type = Constants.REQUEST_REMOVE_A_LIST_OF_VIDEOS_FROM_AN_ON_DEMAND_PAGE;
            } else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_GENRES)) {
                title = getString(R.string.dilog_remove_a_list_of_genres_title);
                type = Constants.REQUEST_REMOVE_A_LIST_OF_GENRES_FROM_AN_ON_DEMAND_PAGE;
            } else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_REGIONS)) {
                title = getString(R.string.dilog_remove_a_list_of_regions_title);
                type = Constants.REQUEST_REMOVE_A_LIST_OF_REGIONS_FROM_AN_ON_DEMAND_PAGE;
            } else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_BACKGROUNDS)) {
                title = getString(R.string.dilog_remove_a_list_of_backgrounds_title);
                type = Constants.REQUEST_REMOVE_A_LIST_OF_BACKGROUNDS_FROM_AN_ON_DEMAND_PAGE;
            }else if (type.equals(Constants.TAB_VIMEO_ONDEMAND_PAGES_PROMOTIONS)) {
                title = getString(R.string.dilog_remove_a_list_of_promotions_title);
                type = Constants.REQUEST_REMOVE_A_LIST_OF_PROMOTIONS_FROM_AN_ON_DEMAND_PAGE;
            }
        }
        showRemoveDialog(title, type);
    }

    private void showCreateOnDemandPageDialog() {
        final TextInputEditText onDemandPageNameEt;
        final TextInputEditText onDemandPageDescEt;
        final RadioGroup radioGroup;
        final RadioButton filmRb;
        final RadioButton seriesRb;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_an_on_demand_page, null);
        builder.setTitle(R.string.dialog_create_an_on_demand_page_title);
        builder.setView(dialogView);
        onDemandPageNameEt = (TextInputEditText) dialogView.findViewById(R.id.on_demand_page_name_tiet);
        onDemandPageDescEt = (TextInputEditText) dialogView.findViewById(R.id.on_demand_page_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.on_demand_page_type_settings_rg);
        filmRb = (RadioButton)dialogView.findViewById(R.id.on_demand_page_type_settings_film_rb);
        seriesRb = (RadioButton)dialogView.findViewById(R.id.on_demand_page_type_settings_series_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.on_demand_page_type_settings_film_rb:
                        break;
                    case R.id.on_demand_page_type_settings_series_rb:
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
                String name = onDemandPageNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_group_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, onDemandPageNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, onDemandPageDescEt.getText().toString());
                    type = Constants.REQUEST_CREATE_AN_ON_DEMAND_PAGE;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserOnDemandPagesActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_album_text));
                }

            }
        });
        onDemandPageNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showEditOnDemandPageDialog() {
        final TextInputEditText onDemandPageNameEt;
        final TextInputEditText onDemandPageDescEt;
        final RadioGroup radioGroup;
        final RadioButton filmRb;
        final RadioButton seriesRb;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_an_on_demand_page, null);
        builder.setTitle(R.string.dialog_edit_an_on_demand_page_title);
        builder.setView(dialogView);
        onDemandPageNameEt = (TextInputEditText) dialogView.findViewById(R.id.edit_on_demand_page_name_tiet);
        onDemandPageDescEt = (TextInputEditText) dialogView.findViewById(R.id.edit_on_demand_page_desc_tiet);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.edit_on_demand_page_type_settings_rg);
        filmRb = (RadioButton)dialogView.findViewById(R.id.edit_on_demand_page_type_settings_film_rb);
        seriesRb = (RadioButton)dialogView.findViewById(R.id.edit_on_demand_page_type_settings_series_rb);
        onDemandPageDescEt.setText(onDemandPage.getDescription());
        onDemandPageNameEt.setText(onDemandPage.getName());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.edit_on_demand_page_type_settings_film_rb:
                        break;
                    case R.id.edit_on_demand_page_type_settings_series_rb:
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
                String name = onDemandPageNameEt.getText().toString();
                if (name.equals("")) {
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .the_name_of_group_is_not_null));
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    map.put(Constants.NAME, onDemandPageNameEt.getText().toString());
                    map.put(Constants.DESCRIPTION, onDemandPageDescEt.getText().toString());
                    type = Constants.REQUEST_EDIT_AN_ON_DEMAND_PAGE;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserOnDemandPagesActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_album_text));
                }

            }
        });
        onDemandPageNameEt.setFocusable(true);
        dialog = builder.create();
        dialog.show();
    }

    private void showEditPosterDialog() {
        final RadioGroup radioGroup;
        final RadioButton trueRb;
        final RadioButton falseRb;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_a_poster_of_an_on_demand_page, null);
        builder.setTitle(R.string.dialog_edit_a_poster_of_an_on_demand_page_title);
        builder.setView(dialogView);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.edit_poster_of_an_on_demand_page_active_settings_rg);
        trueRb = (RadioButton)dialogView.findViewById(R.id.edit_poster_of_an_on_demand_page_active_settings_true_rb);
        falseRb = (RadioButton)dialogView.findViewById(R.id.edit_poster_of_an_on_demand_page_active_settings_false_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.edit_poster_of_an_on_demand_page_active_settings_true_rb:
                        break;
                    case R.id.edit_poster_of_an_on_demand_page_active_settings_false_rb:
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
                    HashMap<String, String> map = new HashMap<>();
                    map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                    type = Constants.REQUEST_EDIT_A_POSTER_OF_AN_ON_DEMAND_PAGE;
                    RecyclerPresenter presenter = new RecyclerPresenter(UserOnDemandPagesActivity.this, type, Constants
                            .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                    presenter.loadJson();
                    SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                            .snack_create_a_album_text));
                }

        });
        dialog = builder.create();
        dialog.show();
    }

    private void showCreatePromotionDialog() {
        final RadioGroup downloadSettingsRg;
        final RadioButton trueRb;
        final RadioButton falseRb;
        final RadioGroup typeSettingsRg;
        final RadioButton batchRb;
        final RadioButton singleRb;

        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_a_promotion, null);
        builder.setTitle(R.string.dialog_create_a_promotion_title);
        builder.setView(dialogView);
        downloadSettingsRg = (RadioGroup)dialogView.findViewById(R.id.promotion_download_settings_rg);
        trueRb = (RadioButton)dialogView.findViewById(R.id.promotion_download_settings_true_rb);
        falseRb = (RadioButton)dialogView.findViewById(R.id.promotion_download_settings_false_rb);
        typeSettingsRg = (RadioGroup)dialogView.findViewById(R.id.promotion_type_settings_rg);
        batchRb = (RadioButton)dialogView.findViewById(R.id.promotion_type_settings_batch_rb);
        singleRb = (RadioButton)dialogView.findViewById(R.id.promotion_type_settings_single_rb);
        downloadSettingsRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.promotion_download_settings_true_rb:
                        break;
                    case R.id.promotion_download_settings_false_rb:
                        break;
                    default:
                        break;
                }
            }
        });
        typeSettingsRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.promotion_type_settings_batch_rb:
                        break;
                    case R.id.promotion_type_settings_single_rb:
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
                HashMap<String, String> map = new HashMap<>();
                map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                type = Constants.REQUEST_CREATE_A_PROMOTION;
                RecyclerPresenter presenter = new RecyclerPresenter(UserOnDemandPagesActivity.this, type, Constants
                        .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                presenter.loadJson();
                SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                        .snack_create_a_album_text));
            }

        });
        dialog = builder.create();
        dialog.show();
    }

    private void showRemoveDialog(String title, String type){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
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

    private void showEditBackgroundDialog() {
        final RadioGroup radioGroup;
        final RadioButton trueRb;
        final RadioButton falseRb;
        final Boolean active = false;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_a_background_of_an_on_demand_page, null);
        builder.setTitle(R.string.dialog_edit_a_background_of_an_on_demand_page_title);
        builder.setView(dialogView);
        radioGroup = (RadioGroup)dialogView.findViewById(R.id.edit_background_of_an_on_demand_page_active_settings_rg);
        trueRb = (RadioButton)dialogView.findViewById(R.id.edit_background_of_an_on_demand_page_active_settings_true_rb);
        falseRb = (RadioButton)dialogView.findViewById(R.id.edit_background_of_an_on_demand_page_active_settings_false_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.edit_poster_of_an_on_demand_page_active_settings_true_rb:
                        active = true;
                        break;
                    case R.id.edit_poster_of_an_on_demand_page_active_settings_false_rb:
                        active = false;
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
                HashMap<String, String> map = new HashMap<>();
                map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                type = Constants.REQUEST_EDIT_A_BACKGROUND_OF_AN_ON_DEMAND_PAGE;
                RecyclerPresenter presenter = new RecyclerPresenter(UserOnDemandPagesActivity.this, type, Constants
                        .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, getApplicationContext());
                presenter.loadJson();
                SnackbarUtils.showSnackShort(getApplicationContext(), coordinatorLayout, getString(R.string
                        .snack_create_a_album_text));
            }

        });
        dialog = builder.create();
        dialog.show();
    }

    private void showCreatePosterDialog(){
        createPosterDialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_a_poster, null);
        Window window = createPosterDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.createPosterDialogStyle);
        window.getDecorView().setPadding(0, 0 , 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        createPosterDialog.setContentView(dialogView);
        Button bt_gallery = (Button)dialogView.findViewById(R.id.button_gallery) ;
        Button bt_camera = (Button)dialogView.findViewById(R.id.button_camera) ;
        createPosterDialog.show();
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
                    createPosterDialog.dismiss();
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
                    createPosterDialog.dismiss();
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

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {
        switch (requestType){
            case Constants.REQUEST_REMOVE_A_VIDEO_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_GENRE_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_REGION_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_BACKGROUND_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_PROMOTION_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_LIST_OF_VIDEOS_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_LIST_OF_GENRES_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_LIST_OF_REGIONS_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_LIST_OF_BACKGROUNDS_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_REMOVE_A_LIST_OF_PROMOTIONS_FROM_AN_ON_DEMAND_PAGE:
                break;
            case Constants.REQUEST_EDIT_AN_ON_DEMAND_PAGE:
                if(requestType.equals(Constants.CODE_200_OK)){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string.
                            edit_an_on_demand_pape_http_status_code_200));
                }else if(requestType.equals(Constants.CODE_403_FORBIDDEN)){
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout, getString(R.id.
                            edit_an_on_demand_pape_http_status_code_403));
                }else if(requestType.equals(Constants.CODE_404_NOT_FOUND)){
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout, getString(R.id.
                            edit_an_on_demand_pape_http_status_code_404));
                }
                break;
            case Constants.REQUEST_CREATE_AN_ON_DEMAND_PAGE:
                break;

            case Constants.REQUEST_DELETE_A_DRAFT_OF_AN_ON_DEMAND_PAGE:
                if(requestType.equals(Constants.CODE_204_NO_CONTENT)){
                    SnackbarUtils.showSnackShort(context, coordinatorLayout, getString(R.string.
                            remove_a_draft_of_an_on_demand_pape_http_status_code_204));
                }else if(requestType.equals(Constants.CODE_403_FORBIDDEN)){
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout, getString(R.id.
                            remove_a_draft_of_an_on_demand_pape_http_status_code_403));

                }else if(requestType.equals(Constants.CODE_404_NOT_FOUND)){
                    SnackbarUtils.showErrorSnackShort(context, coordinatorLayout, getString(R.id.
                            remove_a_draft_of_an_on_demand_pape_http_status_code_404));
                }
                break;
            default:
                break;
        }
    }
}
