package com.menglingpeng.vonvimeo.mvp.model;

import android.content.Context;
import android.os.AsyncTask;

import com.menglingpeng.vonvimeo.mvp.interf.OnloadJsonListener;

import java.util.HashMap;

public class RecyclerModel implements com.menglingpeng.vonvimeo.mvp.interf.RecyclerModel {

    private String type;
    private String requestType;
    private String requestMethod;
    private HashMap<String, String> map;
    private Context context;

    public RecyclerModel(Context context){
        this.context = context;
    }

    @Override
    public void getJson(String type, String requestType, String requestMethod, HashMap<String, String> map,
                        OnloadJsonListener listener) {

        this.map = map;
        this.type = type;
        this.requestType = requestType;
        this.requestMethod = requestMethod;
        new GetDataTask().execute(listener);
    }

    class GetDataTask extends AsyncTask<OnloadJsonListener, Void, String> {
        OnloadJsonListener listener;
        String json;

        @Override
        protected String doInBackground(OnloadJsonListener... params) {
            listener = params[0];
            json = HttpUtils.getJson(map, type, requestType, requestMethod);
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            if(json != null){
                listener.onSuccess(json, requestType);
            }else {
                listener.onFailure(context.getString(R.string.on_load_shots_listener_failed_msg));
            }
        }
    }
}
