package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.SearchView;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter{

    private Context context;
    private List<String> titles;
    private int resource;
    private SearchView searchView;

    public HistoryAdapter(@NonNull Context context, int resource, List<String> titles, SearchView searchView) {
        super(context, resource, objects);
        this.context=context;
        this.titles=titles;
        this.resource=resource;
        this.searchView=searchView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
}
