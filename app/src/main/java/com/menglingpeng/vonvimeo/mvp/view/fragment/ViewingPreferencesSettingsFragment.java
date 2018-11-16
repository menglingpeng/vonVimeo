package com.menglingpeng.vonvimeo.mvp.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;

public class ViewingPreferencesSettingsFragment extends BaseFragment {

    private User user;
    private Context context;
    private Spinner languageSpinner;
    private Button saveBt;
    private String language;
    private RadioGroup radioGroup;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_viewing_preferences_settings;
    }

    @Override
    protected void initView() {

        user = (User)getActivity().getIntent().getSerializableExtra(Constants.USER);
        context = getContext();
        saveBt = (Button)rootView.findViewById(R.id.view_preferences_settings_save_bt);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.view_preferences_settings_mature_content_filter_rg);

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void initLanguageSpinner(){
        languageSpinner = (Spinner) rootView.findViewById(R.id.view_preferences_settings_language_spinner);
        ArrayAdapter<String> arrayAdapter;
        String[] languageArray = getResources().getStringArray(R.array.language);
        arrayAdapter = new ArrayAdapter<String>(context, R.layout.custom_spinner_text, languageArray);
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        languageSpinner.setAdapter(arrayAdapter);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        language = context.getString(R.string.language_english);
                        break;
                    case 1:
                        language = context.getString(R.string.language_spanish);
                        break;
                    case 2:
                        language = context.getString(R.string.language_portuguese);
                        break;
                    case 3:
                        language = context.getString(R.string.language_french);
                        break;
                    case 4:
                        language = context.getString(R.string.language_german);
                        break;
                    case 5:
                        language = context.getString(R.string.language_japanese);
                        break;
                    case 6:
                        language = context.getString(R.string.language_korean);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
