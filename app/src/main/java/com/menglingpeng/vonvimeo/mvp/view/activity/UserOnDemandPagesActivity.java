package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.IdStringUtil;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;

import java.util.HashMap;

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
            default:
                break;
        }
    }
}
