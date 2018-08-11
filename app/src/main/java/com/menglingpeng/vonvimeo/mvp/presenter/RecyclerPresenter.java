package com.menglingpeng.vonvimeo.mvp.presenter;

import android.content.Context;

import com.menglingpeng.vonvimeo.mvp.interf.OnloadJsonListener;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerModel;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerPresenterIf;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;

import java.util.HashMap;

public class RecyclerPresenter implements RecyclerPresenterIf, OnloadJsonListener {

    private RecyclerView recyclerViewIf;
    private RecyclerModel recyclerModel;
    private String type;
    private String requestType;
    private String requestMethod;
    private HashMap<String, String> map;

    public RecyclerPresenter(RecyclerView recyclerViewIf, String type, String requestType, String requestMethod,
                             HashMap<String, String> map, Context context) {
        this.recyclerViewIf = recyclerViewIf;
        this.type = type;
        this.requestType = requestType;
        this.requestMethod = requestMethod;
        this.map = map;
        recyclerModel = new RecyclerModel(context);
    }


    @Override
    public void onSuccess(String json, String requestType) {
        recyclerViewIf.loadSuccess(json, requestType);

    }

    @Override
    public void onFailure(String msg) {
        recyclerViewIf.hideProgress();
        recyclerViewIf.loadFailed(msg);
    }

    @Override
    public void loadJson() {
        recyclerModel.getJson(type, requestType, requestMethod, map, this);
    }
}
