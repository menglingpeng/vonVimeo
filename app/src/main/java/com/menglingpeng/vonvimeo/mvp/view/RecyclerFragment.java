package com.menglingpeng.vonvimeo.mvp.view;

import android.content.Context;
import android.content.Intent;
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
import com.menglingpeng.vonvimeo.mvp.model.Category;
import com.menglingpeng.vonvimeo.mvp.model.Channel;
import com.menglingpeng.vonvimeo.mvp.model.Group;
import com.menglingpeng.vonvimeo.mvp.model.Tag;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.activity.TagActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserChannelsActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserFollowingActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserGroupActivity;
import com.menglingpeng.vonvimeo.utils.Constants;

import java.util.HashMap;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

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
        Intent intent;
        if(viewHolder instanceof RecyclerAdapter.FeedPeopleViewHolder){
            intent = new Intent(getActivity(), UserFollowingActivity.class);
            intent.putExtra(Constants.FOLLOWING, (Following)t);
            intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FEED_PEOPLE_OF_AUHT_USER);
            startActivity(intent);
        }else if (viewHolder instanceof RecyclerAdapter.FeedChannleViewHolder){
            intent = new Intent(getActivity(), UserChannelsActivity.class);
            intent.putExtra(Constants.CHANNLE, (Channel)t);
            intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FEED_CHANNEL_OF_AUHT_USER);
            startActivity(intent);
        }else if(viewHolder instanceof RecyclerAdapter.FeedChannleViewHolder){
            intent = new Intent(getActivity(), UserGroupActivity.class);
            intent.putExtra(Constants.GROUP, (Group)t);
            intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FEED_GROUP_OF_AUHT_USER);
            startActivity(intent);
        }else if(viewHolder instanceof RecyclerAdapter.FeedCategoryViewHolder){
            intent = new Intent(getActivity(), UserFollowingActivity.class);
            intent.putExtra(Constants.CATEGORY, (Category)t);
            intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FEED_CATEGORY_OF_AUHT_USER);
            startActivity(intent);
        }else if (viewHolder instanceof RecyclerAdapter.FeedTagsViewHolder){
            intent = new Intent(getActivity(), TagActivity.class);
            intent.putExtra(Constants.TAG, (Tag)t);
            intent.putExtra(Constants.TYPE, Constants.REQUEST_LIST_FEED_TAGS_OF_AUHT_USER);
            startActivity(intent);
        }else if(viewHolder instanceof RecyclerAdapter.ExploreStuffViewHolder){
            int position = (int)t;
            switch (position){
                case 0:
                    intent = new Intent(getActivity(), UserChannelsActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(getActivity(), UserGroupActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CategoritesActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }
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

    private AlphaInAnimationAdapter setItemAnimation(RecyclerAdapter adapter){
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setDuration(1000);
        return alphaInAnimationAdapter;
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


        switch (requestType) {
            case Constants.REQUEST_REFRESH:
                showRefreshProgress(false);
                adapter = new RecyclerAdapter(recyclerView, context, fragment, type, this);
                recyclerView.setAdapter(setItemAnimation(adapter));
                break;
            case Constants.REQUEST_LOAD_MORE:
                adapter.setLoading(false);
                break;
            case Constants.REQUEST_NORMAL:
                if (type.equals(Constants.REQUEST_ADD_A_V)) {
                    adapter = null;
                } else {
                    adapter = new RecyclerAdapter(recyclerView, context, fragment, type, this);
                }
                recyclerView.setAdapter(setItemAnimation(adapter));
                break;
            default:
                break;
        }
        adapter.setLoadingMore(new RecyclerAdapter.onLoadingMore() {
            @Override
            public void onLoadMore() {
                adapter.setLoading(true);
                page += 1;
                map.put("page", String.valueOf(page));
                mRequestType = Constants.REQUEST_LOAD_MORE;
                initData();
            }
        });

    }


}
