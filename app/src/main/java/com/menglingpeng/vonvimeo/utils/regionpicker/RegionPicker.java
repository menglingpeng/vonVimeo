package com.menglingpeng.vonvimeo.utils.regionpicker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class RegionPicker extends DialogFragment{

    private ArrayList<Region> allCountries = new ArrayList<>();
    private ArrayList<Region> selectedCountries = new ArrayList<>();
    private OnPick onPick;

    public RegionPicker() { }

    public static RegionPicker newInstance(Bundle args, OnPick onPick) {
        RegionPicker picker = new RegionPicker();
        picker.setArguments(args);
        picker.onPick = onPick;
        return picker;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_region_picker, container, false);
        EditText etSearch = (EditText) root.findViewById(R.id.et_search);
        final RecyclerView rvRegion = (RecyclerView) root.findViewById(R.id.rv_region);
        allCountries.clear();
        allCountries.addAll(Region.getAll(getContext(), null));
        selectedCountries.clear();
        selectedCountries.addAll(allCountries);
        final Adapter adapter = new Adapter(getContext());
        adapter.setOnPick(Region -> {
            dismiss();
            if(onPick != null) onPick.onPick(Region);
        });
        adapter.setSelectedCountries(selectedCountries);
        rvRegion.setAdapter(adapter);
        rvRegion.setLayoutManager(new LinearLayoutManager(getContext()));
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override public void afterTextChanged(Editable s) {
                String string = s.toString();
                selectedCountries.clear();
                for (Region Region : allCountries) {
                    if(Region.name.toLowerCase().contains(string.toLowerCase()))
                        selectedCountries.add(Region);
                }
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}
