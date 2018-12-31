package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.view.activity.UpgradeActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.List;

public class DistributionFragment extends BaseFragment implements View.OnClickListener{

    private Context context;
    private Video video;
    private User user;
    private Button socialUpgradeBt;
    private Button facebookConnectBt;
    private Button youtubeConnectBt;
    private Button twiterConnectBt;
    private ImageView createTagIv;
    private ImageView createCategoriesIv;
    private ImageView createCreditsIv;
    private RadioGroup contentRatingRg;
    private RadioButton allAudiencesRb;
    private RadioButton matureRb;
    private Switch recordedSwitch;
    private Spinner licenseSpinner;
    private EditText customUrlEt;
    private ImageView createSubtitlesIv;
    private ListView subtitlesLv;
    private Button fileLinksUpgradeBt;
    private Spinner fileLinksSpinner;



    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_distribution;
    }

    @Override
    protected void initView() {

        context = getContext();
        video = (Video)getActivity().getIntent().getSerializableExtra(Constants.VIDEO);
        user = video.getUser();
        socialUpgradeBt = (Button)rootView.findViewById(R.id.distribution_social_settings_title_upgrade_bt);
        facebookConnectBt = (Button)rootView.findViewById(R.id.distribution_social_settings_facebook_connect_bt);
        youtubeConnectBt = (Button)rootView.findViewById(R.id.distribution_social_settings_youtube_connect_bt);
        twiterConnectBt = (Button)rootView.findViewById(R.id.distribution_social_settings_twiter_connect_bt);
        createTagIv = (ImageView)rootView.findViewById(R.id.distribution_discovey_settings_tags_create_iv);
        createCategoriesIv = (ImageView)rootView.findViewById(R.id.distribution_discovey_settings_categories_create_iv);
        createCreditsIv = (ImageView)rootView.findViewById(R.id.distribution_discovey_settings_credits_create_iv);
        contentRatingRg = (RadioGroup)rootView.findViewById(R.id.distribution_discovey_settings_content_rating_rg);
        allAudiencesRb = (RadioButton)rootView.findViewById(R.id.distribution_discovey_settings_content_rating_all_audiences_rb);
        matureRb = (RadioButton)rootView.findViewById(R.id.distribution_discovey_settings_content_rating_mature_rb);
        recordedSwitch = (Switch)rootView.findViewById(R.id.distribution_discovey_settings_recorded_switch);
        licenseSpinner = (Spinner)rootView.findViewById(R.id.distribution_discovery_settings_license_spinner);
        createSubtitlesIv = (ImageView)rootView.findViewById(R.
                id.distribution_discovery_settings_subtitles_captions_create_iv);
        subtitlesLv = (ListView)rootView.findViewById(R.id.distribution_subtitles_settings_subtitles_captions_check_lv);
        fileLinksUpgradeBt = (Button)rootView.findViewById(R.id.distribution_file_links_settings_title_upgrade_bt);
        initSpinner();
    }

    @Override
    protected void initData() {

        contentRatingRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.distribution_discovey_settings_content_rating_all_audiences_rb:
                        break;
                    case R.id.distribution_discovey_settings_content_rating_mature_rb:
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
            case R.id.distribution_social_settings_title_upgrade_bt:
                Intent intent = new Intent(getActivity(), UpgradeActivity.class);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                break;
            case R.id.distribution_social_settings_facebook_connect_bt:
                break;
            case R.id.distribution_social_settings_youtube_connect_bt:
                break;
            case R.id.distribution_social_settings_twiter_connect_bt:
                break;
            case R.id.distribution_discovey_settings_tags_create_iv:
                break;
            case R.id.distribution_discovey_settings_categories_create_iv:
                showAddVideoToCategory();
                break;
            case R.id.distribution_discovey_settings_tags_credits_iv:
                break;
            case R.id.id.distribution_discovery_settings_subtitles_captions_create_iv:
                showCreateSubtitlesDialog();
                break;
            case R.id.distribution_file_links_settings_title_upgrade_bt:
                Intent intent1 = new Intent(getActivity(), UpgradeActivity.class);
                intent1.putExtra(Constants.USER, user);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    private void showAddVideoToCategory(){

        ListView listView;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.create_an_album_di, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        listView = (ListView) dialogView.findViewById(R.id.categorites_lv);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void showCreateSubtitlesDialog(){

        Spinner languageSpinner;
        Spinner typeSpinner;
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_subtitle_caption, null);
        builder.setTitle(R.string.create_a_bucket);
        builder.setView(dialogView);
        languageSpinner = (Spinner) dialogView.findViewById(R.id.language_spinner);
        typeSpinner = (Spinner) dialogView.findViewById(R.id.type_spinner);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void initSpinner() {
        licenseSpinner = (Spinner) rootView.findViewById(R.id.distribution_discovery_settings_license_spinner);
        fileLinksSpinner = (Spinner) rootView.findViewById(R.id.distribution_file_links_settings_spinner);
        ArrayAdapter<String> licenseAdapter;
        ArrayAdapter<String> fileLinksAdapter;
        String[] sortArray = getResources().getStringArray(R.array.sort);
        String[] listArray = getResources().getStringArray(R.array.list);
        licenseAdapter = new ArrayAdapter<String>(context, R.layout.custom_spinner_text, sortArray);
        fileLinksAdapter = new ArrayAdapter<String>(context, R.layout.custom_spinner_text, listArray);
        fileLinksAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        licenseAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        licenseSpinner.setAdapter(licenseAdapter);
        fileLinksSpinner.setAdapter(fileLinksAdapter);
        fileLinksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        licenseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
