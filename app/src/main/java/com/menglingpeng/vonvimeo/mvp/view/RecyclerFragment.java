package com.menglingpeng.vonvimeo.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.base.BaseFragment;
import com.menglingpeng.vonvimeo.mvp.adapter.RecyclerAdapter;
import com.menglingpeng.vonvimeo.mvp.adapter.TabPagerFragmentAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.OnRecyclerListItemListener;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.HashMap;

public class RecyclerFragment extends BaseFragment implements com.menglingpeng.vonvimeo.mvp.interf.RecyclerView<Video>,
        OnRecyclerListItemListener, SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerPresenter presenter;
    private RecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private BaseActivity mActivity;
    private String type;
    private HashMap<String, String> map;
    private String mRequestType = Constants.REQUEST_NORMAL;
    private int page = 1;
    private Fragment fragment = null;
    private Context context;
    private User user;

    public static RecyclerFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static RecyclerFragment newInstance(String id, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        bundle.putString(Constants.ID, id);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static RecyclerFragment newInstance(User user, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        bundle.putSerializable(Constants.USER, user);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
        type = getArguments().get(Constants.TYPE).toString();
        context = getActivity().getApplicationContext();
    }

    @Override
    protected void initView() {
        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //确保item大小固定，可以提升性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new LandingAnimator());
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(this);
        if(type.equals(Constants.REQUEST_LIST_DETAIL_FOR_AUTH_USER) || type.equals(
                Constants.REQUEST_LIST_DETAIL_FOR_A_USER)){
            setUserAdapter(user);
        }
    }

    @Override
    protected void initData() {

    }

    private void setUserAdapter(User user){
        fragment = TabPagerFragmentAdapter.getCurrentPagerViewFragment();
        adapter = new RecyclerAdapter(recyclerView, context, fragment, type, this);
        recyclerView.setAdapter(adapter);
        adapter.addData(user);
        progressBar.setVisibility(ProgressBar.GONE);
    }

    public void showRefreshProgress(final boolean refreshState) {
        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(refreshState);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public <T> void onRecyclerFragmentListListener(RecyclerView.ViewHolder viewHolder, T t) {

    }

    @Override
    public void onRefresh() {
        if (type.indexOf(Constants.DETAIL) != -1) {
            showRefreshProgress(false);
        } else {
            page = 1;
            map.put("page", String.valueOf(page));
            mRequestType = Constants.REQUEST_REFRESH;
            initData();
        }
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {
        showRefreshProgress(false);
    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
