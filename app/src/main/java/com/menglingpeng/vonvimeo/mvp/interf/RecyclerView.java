package com.menglingpeng.vonvimeo.mvp.interf;

/**
 * Created by mengdroid on 2017/10/15.
 */

public interface RecyclerView<T extends Data> {
    void hideProgress();
    void loadFailed(String msg);
    void loadSuccess(String json, String requestType);
}
