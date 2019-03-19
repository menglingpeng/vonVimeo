package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.R;
import com.menglingpeng.vonvimeo.mvp.interf.OnRecyclerListItemListener;
import com.menglingpeng.vonvimeo.mvp.model.Album;
import com.menglingpeng.vonvimeo.mvp.model.Category;
import com.menglingpeng.vonvimeo.mvp.model.Channel;
import com.menglingpeng.vonvimeo.mvp.model.FeedVideo;
import com.menglingpeng.vonvimeo.mvp.model.Follow;
import com.menglingpeng.vonvimeo.mvp.model.Group;
import com.menglingpeng.vonvimeo.mvp.model.Portfolio;
import com.menglingpeng.vonvimeo.mvp.model.Project;
import com.menglingpeng.vonvimeo.mvp.model.Stuff;
import com.menglingpeng.vonvimeo.mvp.model.Tag;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.mvp.presenter.RecyclerPresenter;
import com.menglingpeng.vonvimeo.mvp.view.UserAlbumDetailActivity;
import com.menglingpeng.vonvimeo.mvp.view.UserAlbumsActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserChannelsActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserFollowingActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserGroupsActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserProfileActivity;
import com.menglingpeng.vonvimeo.mvp.view.activity.UserUploadedVideosActivity;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;
import com.menglingpeng.vonvimeo.utils.SnackbarUtils;
import com.menglingpeng.vonvimeo.utils.TextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ITEM = 0;
    private static final int TYPE_ITEM_FOOTER = 1;
    private static final int TYPE_ITEM_EMPTY = 2;
    private OnRecyclerListItemListener mListener;
    private Fragment fragment;
    private Context context;
    private String type;
    private ArrayList list = new ArrayList<>();
    private int viewType;
    private boolean isLoading;
    onLoadingMore loadingMore;
    //加载更多的提前量
    private int visibleThreshold = 2;
    private List<Boolean> booleanlist;

    public RecyclerAdapter(RecyclerView recyclerView, Context context, Fragment fragment, final String type,
                           OnRecyclerListItemListener listener) {
        this.type = type;
        this.context = context;
        this.fragment = fragment;
        mListener = listener;
        booleanlist = new ArrayList<>();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemcount = layoutManager.getItemCount();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                if (itemcount >= Constants.PER_PAGE_VALUE) {
                    if (!isLoading && lastPosition >= (itemcount - visibleThreshold)) {
                        if (loadingMore != null) {
                            isLoading = true;
                            loadingMore.onLoadMore();
                        }
                    }
                }
            }
        });

        for (int i = 0; i < list.size(); i++) {
            //设置默认的显示
            booleanlist.add(false);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list.isEmpty()) {
            viewType = TYPE_ITEM_EMPTY;
        } else {
            if (position == list.size()) {
                viewType = TYPE_ITEM_FOOTER;
            } else {
                viewType = TYPE_ITEM;
            }
        }
        return viewType;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        int cout = 0;
        if (list.isEmpty()) {
            Log.i("empty", "true");
            cout = 1;
            return cout;
        } else {
            if (list.size() < 12) {
                cout = list.size();
            } else {
                cout = list.size() + 1;
            }
        }
        return cout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_ITEM_EMPTY:
                view = inflater.inflate(R.layout.recycler_item_empty, parent, false);
                viewHolder = new EmptyViewHolder(view);
                break;
            case TYPE_ITEM_FOOTER:
                view = inflater.inflate(R.layout.recycler_item_footer_loading_more, parent, false);
                viewHolder = new FooterViewHolder(view);
                break;
            default:
                switch (type){
                    case Constants.REQUEST_LIST_USER_FEED_VIDEOS:
                        view = inflater.inflate(R.layout.recycler_item, parent, false);
                        viewHolder = new FeedVideoViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_USER_ALBUMS:
                        view = inflater.inflate(R.layout.user_albums_recycler_item, parent, false);
                        viewHolder = new AlbumViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_USER_PROJECTS:
                        view = inflater.inflate(R.layout.user_projects_recycler_item, parent, false);
                        viewHolder = new ProjectViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_CATEGORITES:
                        view = inflater.inflate(R.layout.categorites_recycler_item, parent, false);
                        viewHolder = new CateGoritesViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_CHANNELS:
                        view = inflater.inflate(R.layout.recycler_item_channels, parent, false);
                        viewHolder = new UserChannelViewThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_VIDEO_FOR_A_CHANNEL:
                        view = inflater.inflate(R.layout.recycler_item_channel_detail, parent, false);
                        viewHolder = new ChannelDetailViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_GROUPS:
                        view = inflater.inflate(R.layout.layout.recycler_item_groups, parent, false);
                        viewHolder = new GroupViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_DETAIL_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.user_detail_recycler_item, parent, false);
                        viewHolder = new DetailOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.user_follow_recycler_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.user_follow_recycler_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_PROJECTS_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.user_projects_recycler_item, parent, false);
                        viewHolder = new ProjectViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_DETAIL_FOR_A_USER:
                        view = inflater.inflate(R.layout.user_detail_recycler_item, parent, false);
                        viewHolder = new DetailOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWERS_FOR_A_USER:
                        view = inflater.inflate(R.layout.user_follow_recycler_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FOLLOWING_FOR_A_USER:
                        view = inflater.inflate(R.layout.user_follow_recycler_item, parent, false);
                        viewHolder = new FollowOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_PROJECTS_FOR_A_USER:
                        view = inflater.inflate(R.layout.user_projects_recycler_item, parent, false);
                        viewHolder = new ProjectViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_VIDOES_THAT_A_USER_HAS_WATCHED:
                        view = inflater.inflate(R.layout.recycler_user_watched_video, parent, false);
                        viewHolder = new WatchedVideoViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_VIDOES_THAT_A_USER_HAS_LIKED:
                        view = inflater.inflate(R.layout.recycler_user_liked_video, parent, false);
                        viewHolder = new LikedVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.TAB_GROUP_DETAIL_MEMBERS:
                        view = inflater.inflate(R.layout.group_members_recycler_item, parent, false);
                        viewHolder = new GroupMemberViewHolder(view);
                        break;
                    case Constants.TAB_GROUP_DETAIL_VIDEOS:
                        break;
                    case Constants.REQUEST_LIST_ALL_PICTURES_OF_USER:
                        view = inflater.inflate(R.layout.recycler_user_picture, parent, false);
                        viewHolder = new UserPictureViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UserPictureViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT:
                        view = inflater.inflate(R.layout.recycler_item_video_in_a_project, parent, false);
                        viewHolder = new ProjectDetailViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_SUGGESTED_FOR_AUTH_USER:
                        view = inflater.inflate(R.layout.user_following_suggested_recycler_item, parent, false);
                        viewHolder = new SuggestedOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_SUGGESTED_FOR_A_USER:
                        view = inflater.inflate(R.layout.user_following_suggested_recycler_item, parent, false);
                        viewHolder = new SuggestedOfUserViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FEED_PEOPLE_OF_AUHT_USER:
                        view = inflater.inflate(R.layout.feed_people_recycler_item, parent, false);
                        viewHolder = new FeedPeopleViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FEED_CHANNEL_OF_AUHT_USER:
                        view = inflater.inflate(R.layout.feed_channle_recycler_item, parent, false);
                        viewHolder = new FeedChannleViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FEED_TAGS_OF_AUHT_USER:
                        view = inflater.inflate(R.layout.feed_tags_recycler_item, parent, false);
                        viewHolder = new FeedTagsViewHolder(view);

                    case Constants.REQUEST_LIST_FEED_GROUP_OF_AUHT_USER:
                        view = inflater.inflate(R.layout.feed_group_recycler_item, parent, false);
                        viewHolder = new FeedGroupViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_FEED_CATEGORY_OF_AUHT_USER:
                        view = inflater.inflate(R.layout.feed_category_recycler_item, parent, false);
                        viewHolder = new FeedCategoryViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_EXPLORE_STUFF:
                        view = inflater.inflate(R.layout.explore_stuff_recycler_item, parent, false);
                        viewHolder = new ExploreStuffViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_TOTAL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_TOTAL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_PLAYS:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_TOTAL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_PLAYS:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_LIKES:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_TOTAL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_LIKES:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_COMMENTS:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_TOTAL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_COMMENTS:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DURATION:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_TOTAL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DURATION:
                        view = inflater.inflate(R.layout.recycler_user_uploaded_video, parent, false);
                        viewHolder = new UploadedVideoThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FEATURED_GROUPS_SORT_BY_DATE_IN_THUMB_VIEW:
                        view = inflater.inflate(R.layout.recycler_item_group, parent, false);
                        viewHolder = new GroupViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_GROUPS_SORT_BY_DATE_IN_THUMB_VIEW:
                        view = inflater.inflate(R.layout.recycler_item_group, parent, false);
                        viewHolder = new GroupViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FEATURED_GROUPS_SORT_BY_DATE_IN_DETAIL_VIEW:
                        view = inflater.inflate(R.layout.detail_view_recycler_item_group, parent, false);
                        viewHolder = new GroupDetaiTypeViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_GROUPS_SORT_BY_DATE_IN_DETAIL_VIEW:
                        view = inflater.inflate(R.layout.detail_view_recycler_item_group, parent, false);
                        viewHolder = new GroupDetaiTypeViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FEATURED_CHANNElS_SORT_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FEATURED_CHANNElS_SORT_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FEATURED_CHANNElS_SORT_BY_VIDEOS:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FEATURED_CHANNElS_SORT_BY_FOLLOWERS:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_CHANNElS_SORT_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_CHANNElS_SORT_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_CHANNElS_SORT_BY_VIDEOS:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_CHANNElS_SORT_BY_FOLLOWERS:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FOLLOWING_CHANNElS_FOR_A_USER_SORT_BY_DATE_IN_VIEW_THUMB:
                        view = inflater.inflate(R.layout.recycler_item_user_channel_view_type_thumb, parent, false);
                        viewHolder = new UserChannelViewThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_MODERATED_CHANNElS_FOR_A_USER_SORT_BY_DATE_IN_VIEW_THUMB:
                        view = inflater.inflate(R.layout.recycler_item_user_channel_view_type_thumb, parent, false);
                        viewHolder = new UserChannelViewThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_LIKED_BY_AUTH_USER_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new LikedVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_LIKED_BY_SINGLE_USER_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new LikedVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new WatchLaterVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new WatchLaterVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_PLAYS:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new WatchLaterVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_LIKES:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new WatchLaterVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_COMMENTS:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new WatchLaterVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_AUTH_USER_WATCH_LATER_SORY_BY_DURATION:
                        view = inflater.inflate(R.layout.recycler_user_liked_videos_thumb_view_item, parent, false);
                        viewHolder = new WatchLaterVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_ALBUMS_OF_AUTH_USER:
                        view = inflater.inflate(R.layout.auth_user_albums_recycler_item, parent, false);
                        viewHolder = new AuthUserAlbumViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_VIDEOS_IN_AN_ALBUM:
                        view = inflater.inflate(R.layout.recycler_user_album_detail_item, parent, false);
                        viewHolder = new AlbumDetailViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_VIDEOS_IN_A_CHANNEL:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_item, parent, false);
                        viewHolder = new ChannelDetailViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_FOLLOWERS_OF_A_CHANNEL:
                        view = inflater.inflate(R.layout.recycler_followers_of_a_channel_item, parent, false);
                        viewHolder = new FollowerOfChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_GROUP_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new GroupVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_GROUP_SORY_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new GroupVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_GROUP_SORY_BY_PLAYS:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new GroupVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_GROUP_SORY_BY_LIKES:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new GroupVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_GROUP_SORY_BY_COMMENTS:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new GroupVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_GROUP_SORY_BY_DURATION:
                        view = inflater.inflate(R.layout.recycler_videos_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new GroupVideoTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_MEMBERS_IN_A_GROUP_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_members_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new MemberOfGroupThumbTypeViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_MODERATORS_IN_A_GROUP_SORY_BY_ALPHABETICAL:
                        view = inflater.inflate(R.layout.recycler_moderators_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new ModeratorOfGroupDetailTypeViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_MODERATORS_IN_A_GROUP_SORY_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_moderators_of_a_channel_thumb_view_item, parent, false);
                        viewHolder = new ModeratorOfGroupDetailTypeViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_A_PROJECT_SORY_BY_DATE_ADDED:
                        view = inflater.inflate(R.layout.recycler_videos_in_a_project_thumb_view_item, parent, false);
                        viewHolder = new UserProjectVideoThumbTypeViewHolder(view);
                    case Constants.REQUEST_GET_ALL_VIDEOS_OF_A_USER_ON_DEMAND_PAGES:
                        view = inflater.inflate(R.layout.
                                recycler_videos_of_a_user_on_demand_pages_thumb_view_item, parent, false);
                        viewHolder = new VideoOfUserOnDemandPagesTypeThumbViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_PORTFOLIOS_OF_A_USR:
                        view = inflater.inflate(R.layout.
                                recycler_user_portfolios_thumb_view_item, parent, false);
                        viewHolder = new PortfolioDetailTypeViewHolder(view);
                        break;
                    case Constants.REQUEST_GET_ALL_VIDEOS_IN_PORTFOLIO_OF_A_USR:
                        view = inflater.inflate(R.layout.
                                recycler_videos_in_portfolio_of_a_user_thumb_view_item, parent, false);
                        viewHolder = new VideoInPortfolioTypeThumbViewHolder(view);
                        break;
                    case Constants.MENU_VIMEO_STOCK:
                        view = inflater.inflate(R.layout.
                                recycler_user_portfolios_thumb_view_item, parent, false);
                        viewHolder = new PortfolioDetailTypeViewHolder(view);
                        break;
                    case Constants.MENU_ON_DEMAND:
                        view = inflater.inflate(R.layout.
                                recycler_videos_of_a_user_on_demand_pages_thumb_view_item, parent, false);
                        viewHolder = new VideoOfUserOnDemandPagesTypeThumbViewHolder(view);
                        break;
                    default:
                        break;
                }
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof FeedVideoTypeThumbViewHolder){
            final FeedVideoTypeThumbViewHolder viewHolder = (FeedVideoTypeThumbViewHolder)holder;
            final FeedVideo video = (FeedVideo) list.get(position);
            ImageLoader.load(fragment, video.getClip().getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getClip().getName());
            viewHolder.videoAddedTimeTv.setText(video.getClip().getCreated_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if (holder instanceof FeedVideoTypeDetailViewHolder){
            final FeedVideoTypeDetailViewHolder viewHolder = (FeedVideoTypeDetailViewHolder)holder;
            final FeedVideo video = (FeedVideo)list.get(position);
            String pictureUrl = video.getClip().getPictures().getUri();
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getClip().getName());
            viewHolder.userNameTv.setText(video.getClip().getUser().getName());
            viewHolder.addedTimeTv.setText(video.getClip().getModified_time());
            viewHolder.videoDescTv.setText(video.getClip().getDescription());
            viewHolder.videoDurationTv.setText(video.getClip().getDuration());
            viewHolder.likesCountTv.setText(video.getClip().getMetadataBean().getConnections().getLikes().getTotal());
            viewHolder.commentsCountTv.setText(video.getClip().getMetadataBean().getConnections().getComments().getTotal());
            viewHolder.playsCountTv.setText(video.getClip().getStats().getPlays());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if (holder instanceof VideoOfUserOnDemandPagesTypeThumbViewHolder){
            final VideoOfUserOnDemandPagesTypeThumbViewHolder viewHolder =
                    (VideoOfUserOnDemandPagesTypeThumbViewHolder)holder;
            final FeedVideo video = (FeedVideo) list.get(position);
            ImageLoader.load(fragment, video.getClip().getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getClip().getName());
            viewHolder.videoAddedTimeTv.setText(video.getClip().getCreated_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof VideoOfUserOnDemandPagesTypeDetailViewHolder){
            final VideoOfUserOnDemandPagesTypeDetailViewHolder viewHolder =
                    (VideoOfUserOnDemandPagesTypeDetailViewHolder)holder;
            final FeedVideo video = (FeedVideo)list.get(position);
            String pictureUrl = video.getClip().getPictures().getUri();
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getClip().getName());
            viewHolder.userNameTv.setText(video.getClip().getUser().getName());
            viewHolder.addedTimeTv.setText(video.getClip().getModified_time());
            viewHolder.videoDescTv.setText(video.getClip().getDescription());
            viewHolder.videoDurationTv.setText(video.getClip().getDuration());
            viewHolder.likesCountTv.setText(video.getClip().getMetadata().getConnections().getLikes().getTotal());
            viewHolder.commentsCountTv.setText(video.getClip().getMetadata().getConnections().getComments().getTotal());
            viewHolder.playsCountTv.setText(video.getClip().getStats().getPlays());
            viewHolder.userNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fragment.getActivity(), UserProfileActivity.class);
                    intent.putExtra(Constants.USER, video.getClip().getUser());
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof PortfolioThumbTypeViewHolder){
            final PortfolioThumbTypeViewHolder viewHolder = (PortfolioThumbTypeViewHolder)holder;
            final Portfolio portfolio = (Portfolio)list.get(position);
            viewHolder.portfolioNameTv.setText(portfolio.getName());
            viewHolder.sortTv.setText(portfolio.getSort());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, portfolio);
                    }
                }
            });
        }else if(holder instanceof PortfolioDetailTypeViewHolder){
            final PortfolioDetailTypeViewHolder viewHolder = (PortfolioDetailTypeViewHolder)holder;
            final Portfolio portfolio = (Portfolio)list.get(position);
            viewHolder.portfolioNameTv.setText(portfolio.getName());
            viewHolder.addedTimeTv.setText(portfolio.getCreated_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, portfolio);
                    }
                }
            });
        }
        else if (holder instanceof VideoInPortfolioTypeThumbViewHolder){
            final VideoInPortfolioTypeThumbViewHolder viewHolder = (VideoInPortfolioTypeThumbViewHolder)holder;
            final Video video = (Video)list.get(position);
            ImageLoader.load(fragment, video.getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            viewHolder.videoAddedTimeTv.setText(video.getCreated_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof VideoInPortfolioTypeDetailViewHolder){
            final VideoInPortfolioTypeDetailViewHolder viewHolder = (VideoInPortfolioTypeDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            String pictureUrl = video.getPictures().getUri();
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getName());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.addedTimeTv.setText(video.getModified_time());
            viewHolder.videoDescTv.setText(video.getDescription());
            viewHolder.videoDurationTv.setText(video.getDuration());
            viewHolder.likesCountTv.setText(video.getMetadataBean().getConnections().getLikes().getTotal());
            viewHolder.commentsCountTv.setText(video.getMetadataBean().getConnections().getComments().getTotal());
            viewHolder.playsCountTv.setText(video.getStats().getPlays());
            viewHolder.userNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fragment.getActivity(), UserProfileActivity.class);
                    intent.putExtra(Constants.USER, video.getUser());
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }
        else if(holder instanceof UploadedVideoThumbViewHolder){
            final UploadedVideoThumbViewHolder viewHolder = (UploadedVideoThumbViewHolder)holder;
            final Video video = (Video)list.get(position);
            String url = video.getPictures().getUri();
            ImageLoader.load(fragment, url, viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            String sortText;
            if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DATE)){
                sortText = video.getCreated_time();
            }else if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_ALPHABETICAL)){
                sortText = video.getCreated_time();
            }else if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_PLAYS)){
                sortText = video.getStatus();
            }else if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_LIKES)){

            }else if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_COMMENTS)){

            }else if(type.equals(Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER_SORY_BY_DURATION){
                sortText = String.valueOf(video.getDuration());
            }
            viewHolder.videoSortTv.setText(sortText);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof UploadedVideoDetailViewHolder){
            final UploadedVideoDetailViewHolder viewHolder = (UploadedVideoDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            String url = video.getPictures().getUri();
            ImageLoader.load(fragment, url, viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.addedTimeTv.setText(video.getModified_time());
            viewHolder.videoDescTv.setText(video.getDescription());
            viewHolder.videoDurationTv.setText(video.getDuration());
            viewHolder.uploadedVideoCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    //用集合保存当前的状态
                    booleanlist.set(position,isChecked);
                }
            });
            viewHolder.uploadedVideoCb.setChecked(booleanlist.get(position));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });

        }else if (holder instanceof LikedVideoTypeThumbViewHolder){
            final LikedVideoTypeThumbViewHolder viewHolder = (LikedVideoTypeThumbViewHolder)holder;
            final Video video = (Video)list.get(position);
            String pictureUrl = video.getPictures().getUri();
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getName());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.likedTimeTv.setText(video.getModified_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if (holder instanceof LikedVideoTypeDetailViewHolder){
            final LikedVideoTypeDetailViewHolder viewHolder = (LikedVideoTypeDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            String pictureUrl = video.getPictures().getUri();v
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getName());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.likedTimeTv.setText(video.getModified_time());
            viewHolder.videoDescTv.setText(video.getDescription());
            viewHolder.videoDurationTv.setText(video.getDuration());
            viewHolder.likesCountTv.setText(video.getMetadataBean().getConnections().getLikes().getTotal());
            viewHolder.commentsCountTv.setText(video.getMetadataBean().getConnections().getComments().getTotal());
            viewHolder.playsCountTv.setText(video.getStats().getPlays());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if (holder instanceof GroupThumbTypeViewHolder){
            final GroupThumbTypeViewHolder viewHolder = (GroupThumbTypeViewHolder)holder;
            final Group group = (Group)list.get(position);
            ImageLoader.load(fragment, group.getPictures().getUri(), viewHolder.groupThumbIv, false);
            viewHolder.groupNameTv.setText(group.getName());
            viewHolder.groupVideosCountTv.setText(group.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.groupFollowersCountTv.setText(group.getMetadata().getConnections().getUsers().getTotal());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, group);
                    }
                }
            });
        }
        else if(holder instanceof GroupDetaiTypeViewHolder){
            final GroupDetaiTypeViewHolder viewHolder = (GroupDetaiTypeViewHolder)holder;
            final Group group = (Group)list.get(position);
            String thumbUrl = group.getPictures().getUri();
            ImageLoader.load(fragment, thumbUrl, viewHolder.groupThumbIv, false);
            viewHolder.groupNameTv.setText(group.getName());
            viewHolder.groupCreatedTimeTv.setText(group.getCreated_time());
            viewHolder.groupVideosCountTv.setText(group.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.groupMembersCountTv.setText(group.getMetadata().getConnections().getUsers().getTotal());
            viewHolder.groupDescTv.setText(group.getDescription());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, group);
                    }
                }
            });
        }else if(holder instanceof GroupVideoTypeThumbViewHolder){
            final GroupVideoTypeThumbViewHolder viewHolder = (GroupVideoTypeThumbViewHolder)holder;
            final Video video = (Video)list.get(position);
            String pictureUrl = video.getPictures().getUri();
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getName());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.likedTimeTv.setText(video.getModified_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof GroupVideoTypeDetailViewHolder){
            final GroupVideoTypeDetailViewHolder viewHolder = (GroupVideoTypeDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            String pictureUrl = video.getPictures().getUri();
            ImageLoader.load(fragment, pictureUrl, viewHolder.VideoThumbIv, false);
            viewHolder.VideoNameTv.setText(video.getName());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.addedTimeTv.setText(video.getModified_time());
            viewHolder.videoDescTv.setText(video.getDescription());
            viewHolder.videoDurationTv.setText(video.getDuration());
            viewHolder.likesCountTv.setText(video.getMetadataBean().getConnections().getLikes().getTotal());
            viewHolder.commentsCountTv.setText(video.getMetadataBean().getConnections().getComments().getTotal());
            viewHolder.playsCountTv.setText(video.getStats().getPlays());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof MemberOfGroupThumbTypeViewHolder){
            final MemberOfGroupThumbTypeViewHolder viewHolder = (MemberOfGroupThumbTypeViewHolder)holder;
            final User user = (User)list.get(position);
            ImageLoader.loadCricleImage(context, user.getPictures().getUri(), viewHolder.userAvatarIv);
            viewHolder.userNameTv.setText(user.getName());
            viewHolder.addedTimeTv.setText(user.getMetadata().getInteractions().getFollow().getAdded_time().toString());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, user);
                    }
                }
            });
        }else if(holder instanceof MemberOfGroupDetailTypeViewHolder){
            final MemberOfGroupDetailTypeViewHolder viewHolder = (MemberOfGroupDetailTypeViewHolder)holder;
            final User user = (User) list.get(position);
            ImageLoader.loadCricleImage(context, user.getPictures().getUri(), viewHolder.userAvatarIv);
            viewHolder.userNameTv.setText(user.getName());
            viewHolder.userPrivilegeBt.setText(user.getAccount().toString());
            viewHolder.userLocationTv.setText(user.getLocation().toString());
            viewHolder.joinedTimeTv.setText(user.getMetadata().getInteractions().getFollow().getAdded_time().toString());
            viewHolder.userBioTv.setText(user.getBio().toString());
            viewHolder.userStatusTv.setText();
            viewHolder.videosTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserUploadedVideosActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.albumsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserAlbumsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.channelsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserChannelsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.groupsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserGroupsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.followingTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserFollowingActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, user);
                    }
                }
            });
        }
        else if(holder instanceof ModeratorOfGroupDetailTypeViewHolder){

        }
        else if (holder instanceof AuthUserAlbumViewHolder){
            final AuthUserAlbumViewHolder viewHolder = (AuthUserAlbumViewHolder)holder;
            final Album album = (Album) list.get(position);
            viewHolder.albumNameTv.setText(album.getName());
            viewHolder.albumVideosCountTv.setText(TextUtil.setBeforeBold(String.valueOf(album.getMetadata().
                            getConnections().getVideos().getTotal()),
                    context.getString(R.string.videos)));
            viewHolder.albumCreatedTimeTv.setText(album.getCreated_time());
            viewHolder.albumInfoIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final TextView titleTv;
                    final ImageView closeIv;
                    final TextView contentTv;
                    final AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View dialogView = fragment.getActivity().getLayoutInflater().inflate(R.
                            layout.dialog_auth_user_album_info, null);
                    builder.setView(dialogView);
                    titleTv = (TextView)dialogView.findViewById(R.id.info_title_tv);
                    closeIv = (ImageView)dialogView.findViewById(R.id.info_close_iv);
                    contentTv = (TextView)dialogView.findViewById(R.id.info_content_tv);
                    titleTv.setText(context.getString(R.string.description));
                    contentTv.setText(album.getDescription());
                    dialog = builder.create();
                    closeIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
            });
            viewHolder.albumShareIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.albumViewIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fragment.getActivity(), UserAlbumDetailActivity.class);
                    intent.putExtra(Constants.ALBUM, album);
                    context.startActivity(intent);
                }
            });
            viewHolder.albumSettingsIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final TextInputEditText albumNameEt;
                    final TextInputEditText albumDescEt;
                    final RadioGroup radioGroup;
                    final RadioButton radioButton1;
                    final RadioButton radioButton2;
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View dialogView = fragment.getActivity().getLayoutInflater().inflate(R.
                            layout.create_an_album_dialog_message, null);
                    builder.setTitle(R.string.create_a_bucket);
                    builder.setView(dialogView);
                    albumNameEt = (TextInputEditText) dialogView.findViewById(R.id.album_name_tiet);
                    albumDescEt = (TextInputEditText) dialogView.findViewById(R.id.album_desc_tiet);
                    radioGroup = (RadioGroup)dialogView.findViewById(R.id.album_privacy_settings_rg);
                    radioButton1 = (RadioButton)dialogView.findViewById(R.id.album_privacy_settings_anyone_rb);
                    radioButton2  = (RadioButton)dialogView.findViewById(R.id.album_privacy_settings_people_with_password_rb);
                    albumNameEt.setText(album.getName());
                    albumDescEt.setText(album.getDescription());
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            switch (radioGroup.getCheckedRadioButtonId()){
                                case R.id.album_privacy_settings_anyone_rb:
                                    radioButton1.setText(Constants.PRIVACY_ANYONE);
                                    break;
                                case R.id.album_privacy_settings_people_with_password_rb:
                                    radioButton2.setText(Constants.PRIVACY_WITH_A_PASSWORD);
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
                            String name = albumNameEt.getText().toString();
                            if (name.equals("")) {
                                SnackbarUtils.showSnackShort(context, fragment.getActivity().
                                                findViewById(R.id.user_albums_cdl),
                                        context.getString(R.string
                                        .the_name_of_album_is_not_null));
                            } else {
                                HashMap<String, String> map = new HashMap<>();
                                map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                                map.put(Constants.NAME, albumNameEt.getText().toString());
                                map.put(Constants.DESCRIPTION, albumDescEt.getText().toString());
                                if(radioButton1.isChecked()) {
                                    map.put(Constants.PRIVACY, radioButton1.getText().toString());
                                }else {
                                    map.put(Constants.PRIVACY, radioButton2.getText().toString());
                                }
                                type = Constants.REQUEST_CREATE_A_ALBUM;
                                RecyclerPresenter presenter = new RecyclerPresenter(fragment.
                                        getActivity(), type, Constants
                                        .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, context);
                                presenter.loadJson();
                                SnackbarUtils.showSnackShort(context, fragment.getActivity().
                                        findViewById(R.id.user_albums_cdl), context.getString(R.string
                                        .snack_create_a_bucket_text));
                            }

                        }
                    });
                    albumNameEt.setFocusable(true);
                    dialog = builder.create();
                    dialog.show();
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, album);
                    }
                }
            });
        }
        else if ((holder instanceof AlbumThumbTypeViewHolder)){
            final AlbumThumbTypeViewHolder viewHolder = (AlbumThumbTypeViewHolder)holder;
            final Album album = (Album) list.get(position);
            viewHolder.albumNameTv.setText(album.getName());
            viewHolder.albumVideosCountTv.setText(TextUtil.setBeforeBold(String.valueOf(album.getMetadata().
                            getConnections().getVideos().getTotal()),
                    context.getString(R.string.videos)));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, album);
                    }
                }
            });
        }else if(holder instanceof AlbumDetailViewHolder){
            final AlbumDetailViewHolder viewHolder = (AlbumDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            ImageLoader.load(fragment, video.getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            ImageLoader.loadCricleImage(fragment, video.getUser().getPictures().getUri(), viewHolder.avatarIv);
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.userNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fragment.getActivity(), UserProfileActivity.class);
                    intent.putExtra(Constants.USER, video.getUser());
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }
        else if (holder instanceof ProjectViewHolder){
            final ProjectViewHolder viewHolder = (ProjectViewHolder)holder;
            final Project project = (Project)list.get(position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, project);
                    }
                }
            });
            viewHolder.projectNameTv.setText(project.getName());
            viewHolder.projectVideosCountTv.setText(TextUtil.setBeforeBold(String.valueOf(project.getShots_count()),
                    context.getString(R.string.videos)));
        }
        else if (holder instanceof CateGoritesViewHolder){
            final CateGoritesViewHolder viewHolder = (CateGoritesViewHolder) holder;
            final Category category = (Category) list.get(position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, category);
                    }
                }
            });
            viewHolder.categoriteNameTv.setText(category.getName());
            viewHolder.categoriteVideosCountTv.setText(TextUtil.setBeforeBold(String.valueOf(category.getShots_count()),
                    context.getString(R.string.videos)));
        }else if(holder instanceof ChannelViewHolder){
            final ChannelViewHolder viewHolder = (ChannelViewHolder)holder;
            final Channel channel = (Channel)list.get(position);
            String url;
            url = channel.getUri();
            ImageLoader.load(fragment, url, viewHolder.channelThumbIv, false);
            viewHolder.channelNameTv.setText(channel.getName());
            viewHolder.channelDescTv.setText(channel.getDescription());
            viewHolder.channelFollowersCountTv.setText(channel.getMetadata().getConnections().getUsers().getTotal());
            viewHolder.channelVideosCountTv.setText(channel.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channel);
                    }
                }
            });
        }
        else if (holder instanceof UserChannelViewDetailViewHolder){
            final UserChannelViewDetailViewHolder viewHolder = (UserChannelViewDetailViewHolder)holder;
            final Channel channel = (Channel)list.get(position);
            String url;
            url = channel.getUri();
            ImageLoader.load(fragment, url, viewHolder.channelThumbIv, false);
            viewHolder.channelNameTv.setText(channel.getName());
            viewHolder.channleAddedTimeTv.setText(channel.getModified_time());
            viewHolder.channelDescTv.setText(channel.getDescription());
            viewHolder.channelFollowersCountTv.setText(channel.getMetadata().getConnections().getUsers().getTotal());
            viewHolder.channelVideosCountTv.setText(channel.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channel);
                    }
                }
            });
        }else if (holder instanceof UserChannelViewThumbViewHolder) {
            final UserChannelViewThumbViewHolder viewHolder = (UserChannelViewThumbViewHolder) holder;
            final Channel channel = (Channel) list.get(position);
            String url;
            url = channel.getUri();
            ImageLoader.load(fragment, url, viewHolder.channelThumbIv, false);
            viewHolder.channelNameTv.setText(channel.getName());
            viewHolder.channelFollowersCountTv.setText(channel.getMetadata().getConnections().getUsers().getTotal());
            viewHolder.channelVideosCountTv.setText(channel.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channel);
                    }
                }
            });
        } else if(holder instanceof ChannelDetailViewHolder){
            final ChannelDetailViewHolder viewHolder = (ChannelDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            ImageLoader.load(fragment, video.getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            ImageLoader.loadCricleImage(fragment, video.getUser().getPictures().getUri(), viewHolder.avatarIv);
            viewHolder.videoNameTv.setText(video.getUser().getName());
            viewHolder.videoPlayCountsTv.setText(video.getStats().getPlays());
            viewHolder.avatarIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(fragment.getActivity(), UserProfileActivity.class);
                    intent.putExtra(Constants.USER, video.getUser());
                    context.startActivity(intent);
                }
            });
            viewHolder.videoNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(fragment.getActivity(), UserProfileActivity.class);
                    intent.putExtra(Constants.USER, video.getUser());
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if (holder instanceof FollowerOfChannelViewHolder){
            final FollowerOfChannelViewHolder viewHolder = (FollowerOfChannelViewHolder)holder;
            final User user = (User) list.get(position);
            ImageLoader.loadCricleImage(context, user.getPictures().getUri(), viewHolder.userAvatarIv);
            viewHolder.userNameTv.setText(user.getName());
            viewHolder.userPrivilegeBt.setText(user.getAccount().toString());
            viewHolder.userLocationTv.setText(user.getLocation().toString());
            viewHolder.addedTimeTv.setText(user.getMetadata().getInteractions().getFollow().getAdded_time().toString());
            viewHolder.userBioTv.setText(user.getBio().toString());
            viewHolder.userStatusTv.setText();
            viewHolder.videosTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserUploadedVideosActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.albumsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserAlbumsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.channelsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserChannelsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.groupsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserGroupsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.followingTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserFollowingActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, user);
                    }
                }
            });
        }
        else if(holder instanceof FollowOfUserThumbTypeViewHolder){
            final FollowOfUserThumbTypeViewHolder viewHolder = (FollowOfUserThumbTypeViewHolder) holder;
            final User user = (User)list.get(position);
            ImageLoader.loadCricleImage(context, user.getPictures().getUri(), viewHolder.userAvatarIv);
            viewHolder.userNameTv.setText(user.getName());
            viewHolder.addedTimeTv.setText(user.getMetadata().getInteractions().getFollow().getAdded_time().toString());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, user);
                    }
                }
            });
        }else if(holder instanceof FollowOfUserDetailTypeViewHolder) {
            final FollowOfUserDetailTypeViewHolder viewHolder = (FollowOfUserDetailTypeViewHolder) holder;
            final User user = (User) list.get(position);
            ImageLoader.loadCricleImage(context, user.getPictures().getUri(), viewHolder.userAvatarIv);
            viewHolder.userNameTv.setText(user.getName());
            viewHolder.userPrivilegeBt.setText(user.getAccount().toString());
            viewHolder.userLocationTv.setText(user.getLocation().toString());
            viewHolder.addedTimeTv.setText(user.getMetadata().getInteractions().getFollow().getAdded_time().toString());
            viewHolder.userBioTv.setText(user.getBio().toString());
            viewHolder.userStatusTv.setText();
            viewHolder.videosTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserUploadedVideosActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.albumsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserAlbumsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.channelsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserChannelsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.groupsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserGroupsActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.followingTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserFollowingActivity.class);
                    intent.putExtra(Constants.USER, user);
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, user);
                    }
                }
            });
        }
        else if (holder instanceof ProjectViewHolder){
            final ProjectViewHolder viewHolder = (ProjectViewHolder)holder;
            final Project project = (Project)list.get(position);
            viewHolder.projectNameTv.setText(project.getName());
            viewHolder.projectVideosCountTv.setText(project.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.settingsIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final TextInputEditText projectNameEt;
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View dialogView = fragment.getActivity().getLayoutInflater().inflate(
                            R.layout.dialog_creat_a_project, null);
                    builder.setTitle(R.string.edit_project);
                    builder.setView(dialogView);
                    projectNameEt = (TextInputEditText) dialogView.findViewById(R.id.project_name_tiet);
                    projectNameEt.setText(project.getName());
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = projectNameEt.getText().toString();
                            if (name.equals("")) {
                                SnackbarUtils.showSnackShort(context, fragment.getActivity().findViewById(
                                        R.id.user_projects_cdl), context.getString(R.string
                                        .snack_the_name_of_project_is_not_null));
                            } else {
                                HashMap<String, String> map = new HashMap<>();
                                map.put(Constants.ACCESS_TOKEN, SharedPrefUtils.getAuthToken());
                                map.put(Constants.NAME, projectNameEt.getText().toString());
                                type = Constants.REQUEST_REQUEST_EDIT_A_PROJECT;
                                RecyclerPresenter presenter = new RecyclerPresenter(
                                        fragment.getActivity(), type, Constants
                                        .REQUEST_NORMAL, Constants.REQUEST_POST_MEIHOD, map, context);
                                presenter.loadJson();
                                SnackbarUtils.showSnackShort(context, fragment.getActivity().findViewById(
                                        R.id.user_projects_cdl), context.getString(R.string
                                        .snack_edit_a_project_text));
                            }

                        }
                    });
                    builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    projectNameEt.setFocusable(true);
                    dialog = builder.create();
                    dialog.show();
                }
            });
            viewHolder.deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, project);
                    }
                }
            });

        } else if(holder instanceof UserProjectVideoThumbTypeViewHolder){
            final UserProjectVideoThumbTypeViewHolder viewHolder = (UserProjectVideoThumbTypeViewHolder)holder;
            final Video video = (Video)list.get(position);
            ImageLoader.load(fragment, video.getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            viewHolder.addedTimeTv.setText(video.getCreated_time());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof UserProjectVideoTypeDetailViewHolder){
            final UserProjectVideoTypeDetailViewHolder viewHolder = (UserProjectVideoTypeDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            ImageLoader.load(fragment, video.getPictures().getUri(), viewHolder.videoThumbIv, false);
            viewHolder.videoNameTv.setText(video.getName());
            viewHolder.addedTimeTv.setText(video.getCreated_time());
            viewHolder.userNameTv.setText(video.getUser().getName());
            viewHolder.videoDescTv.setText(video.getDescription());
            viewHolder.videoDurationTv.setText(video.getDuration());
            viewHolder.playsCountTv.setText(video.getStats().getPlays());
            viewHolder.likesCountTv.setText(video.getMetadataBean().getConnections().getLikes().getTotal());
            viewHolder.commentsCountTv.setText(video.getMetadataBean().getConnections().getComments().getTotal());
            viewHolder.userNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fragment.getActivity(), UserProfileActivity.class);
                    intent.putExtra(Constants.USER, video.getUser());
                    context.startActivity(intent);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }
        else if(holder instanceof FeedPeopleViewHolder){
            final FeedPeopleViewHolder viewHolder = (FeedPeopleViewHolder)holder;
            final Follow follow = (Follow) list.get(position);
            String avatarUrl = follow.getPictures().getUri();
            ImageLoader.loadCricleImage(fragment, avatarUrl, viewHolder.avatarIv);
            viewHolder.userNameTv.setText(follow.getName());
            viewHolder.followingTimeTv.setText(follow.getCreated_time());
            viewHolder.videosCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.appearancesCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.likesCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, follow);
                    }
                }
            });
        }else if (holder instanceof FeedChannleViewHolder){
            final FeedChannleViewHolder viewHolder = (FeedChannleViewHolder)holder;
            final Channel channel = (Channel)list.get(position);
            viewHolder.channleNameTv.setText(channel.getName());
            viewHolder.channleFollowingTimeTv.setText(channel.getModified_time());
            viewHolder.channleVideosCountTv.setText(channel.getMetadata().getConnections().
                    getVideos().getTotal());
            viewHolder.channleFollowersCountTv.setText(channel.getMetadata().getConnections().
                    getUsers().getTotal());
            viewHolder.channleCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channel);
                    }
                }
            });
        }else if(holder instanceof FeedGroupViewHolder){
            final FeedGroupViewHolder viewHolder = (FeedGroupViewHolder)holder;
            final Group group = (Group)list.get(position);
            viewHolder.groupNameTv.setText(group.getName());
            viewHolder.groupFollowingTimeTv.setText(group.getModified_time());
            viewHolder.groupVideosCountTv.setText(group.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.groupFollowersCountTv.setText(group.getMetadata().getConnections().getUsers().getTotal());
            viewHolder.groupCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, group);
                    }
                }
            });
        }else if(holder instanceof FeedCategoryViewHolder){
            final FeedCategoryViewHolder viewHolder = (FeedCategoryViewHolder)holder;
            final Category category = (Category)list.get(position);
            viewHolder.categoryNameTv.setText(category.getName());
            viewHolder.categoryFollowingTimeTv.setText(category.getMetadata().getInteractions().getFollow().
                    getAdded_time().toString());
            viewHolder.categoryVideosCountTv.setText(category.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.categoryCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, category);
                    }
                }
            });
        }else if (holder instanceof FeedTagsViewHolder){
            final FeedTagsViewHolder viewHolder = (FeedTagsViewHolder)holder;
            final Tag tag = (Tag)list.get(position);
            viewHolder.tagsNameTv.setText(tag.getName());
            viewHolder.tagsFollowingTimeTv.setText(tag.getMetadata().getConnections().getVideos().);
            viewHolder.tagsVideosCountTv.setText(tag.getMetadata().getConnections().getVideos().getTotal());
            viewHolder.tagsCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, tag);
                    }
                }
            });
        }else if(holder instanceof ExploreStuffViewHolder){
            final ExploreStuffViewHolder viewHolder = (ExploreStuffViewHolder)holder;
            final Stuff stuff = (Stuff)list.get(position);
            viewHolder.stuffNameTv.setText(stuff.getName());
            viewHolder.stuffDescTv.setText(stuff.getDesc());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, position);
                    }
                }
            });
        }else if(holder instanceof OndemandPageOfPageGenreViewHolder){
            final OndemandPageOfPageGenreViewHolder viewHolder = (OndemandPageOfPageGenreViewHolder) holder;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, position);
                    }
                }
            });

        }
        else if (holder instanceof EmptyViewHolder) {
            int ivId = 0;
            int tvId = 0;
            EmptyViewHolder viewHolder = (EmptyViewHolder) holder;
            switch (type) {
                case Constants.REQUEST_LIST_VIDEOS_FOR_AUTH_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_video_here;
                    break;
                case Constants.REQUEST_LIST_VIDEOS_FOR_A_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_video_here;
                    break;
                case Constants.REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_follower_here;
                    break;
                case Constants.REQUEST_LIST_FOLLOWING_FOR_AUTH_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_following_here;
                    break;
                case Constants.REQUEST_LIST_FOLLOWERS_FOR_A_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_follower_here;
                    break;
                case Constants.REQUEST_LIST_FOLLOWING_FOR_A_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_following_here;
                    break;
                case Constants.REQUEST_LIST_PROJECTS_FOR_A_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_project_here;
                    break;
                case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_AUTH_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_uploaded_vidoes_here;
                    break;
                case Constants.REQUEST_GET_ALL_VIDEOS_UPLOADED_BY_SINGLE_USER:
                    ivId = R.drawable.ic_image_grey_400_48dp;
                    tvId = R.string.no_uploaded_vidoes_here;
                    break;

                 default:
                     break;
            }
            viewHolder.emptyIv.setImageResource(ivId);
            viewHolder.emptyTv.setText(context.getString(tvId));
            }
    }

    public class FeedVideoTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public FeedVideoTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_feed_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_feed_video_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_feed_video_added_time);
        }
    }

    private class FeedVideoTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public FeedVideoTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_feed_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_feed_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_feed_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_feed_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_feed_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_feed_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_feed_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_feed_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_feed_duration_tv);
        }
    }

    public class VideoOfUserOnDemandPagesTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfUserOnDemandPagesTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_on_demand_pages_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_on_demand_pages_video_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_on_demand_pages_video_added_time);
        }
    }

    private class VideoOfUserOnDemandPagesTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfUserOnDemandPagesTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_on_demand_pages_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_on_demand_pages_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_on_demand_pages_duration_tv);
        }
    }

    public class PortfolioThumbTypeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView portfolioThumbIv;
        public final TextView portfolioNameTv;
        public final TextView sortTv;


        public PortfolioThumbTypeViewHolder(View view) {
            super(view);
            portfolioThumbIv = (ImageView)view.findViewById(R.id.portfolio_thumb_iv_iv);
            portfolioNameTv = (TextView)view.findViewById(R.id.portfolio_name_tv);
            sortTv = (TextView)view.findViewById(R.id.portfolio_sort_tv);

        }
    }

    public class PortfolioDetailTypeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView portfolioThumbIv;
        public final TextView portfolioNameTv;
        public final TextView addedTimeTv;


        public PortfolioDetailTypeViewHolder(View view) {
            super(view);
            portfolioThumbIv = (ImageView)view.findViewById(R.id.detail_view_portfolio_thumb_iv_iv);
            portfolioNameTv = (TextView)view.findViewById(R.id.detail_view_portfolio_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.id.detail_view_portfolio_added_time_tv);

        }
    }

    public class VideoInPortfolioTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoInPortfolioTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_portfolio_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_portfolio_video_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_portfolio_video_added_time);
        }
    }

    private class VideoInPortfolioTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoInPortfolioTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_portfolio_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_portfolio_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_portfolio_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_portfolio_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_portfolio_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_portfolio_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_portfolio_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_portfolio_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_portfolio_duration_tv);
        }
    }

    public class RegionDetailTypeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView regionThumbIv;
        public final TextView regionNameTv;
        public final TextView addedTimeTv;


        public RegionDetailTypeViewHolder(View view) {
            super(view);
            regionThumbIv = (ImageView)view.findViewById(R.id.detail_view_added_thumb_iv_iv);
            regionNameTv = (TextView)view.findViewById(R.id.detail_view_added_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.id.detail_view_added_added_time_tv);

        }
    }

    public class UploadedVideoThumbViewHolder extends RecyclerView.ViewHolder {
        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoSortTv;

        public UploadedVideoThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.uploaded_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.uploaded_video_name_tv);
            videoSortTv = (TextView)view.findViewById(R.id.uploaded_video_sort_tv);
        }
    }

    public class UploadedVideoDetailViewHolder extends RecyclerView.ViewHolder {
        public final CheckBox uploadedVideoCb;
        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView videoDurationTv;



        public UploadedVideoDetailViewHolder(View view) {
            super(view);
            uploadedVideoCb = (CheckBox) view.findViewById(R.id.detail_view_uploaded_video_cb)
            videoThumbIv = (ImageView)view.findViewById(R.id.detail_view_uploaded_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.detail_view_uploaded_view_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_uploaded_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.id.detail_view_uploaded_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_uploaded_video_desc_tv);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_uploaded_video_duration);

        }
    }

    public class GroupDetaiTypeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView groupThumbIv;
        public final TextView groupNameTv;
        public final TextView groupCreatedTimeTv;
        public final TextView groupVideosCountTv;
        public final TextView groupMembersCountTv;
        public final TextView groupDescTv;


        public GroupDetaiTypeViewHolder(View view) {
            super(view);

            groupThumbIv = (ImageView)view.findViewById(R.id.detail_view_group_thumb_iv);
            groupNameTv = (TextView)view.findViewById(R.id.detail_view_group_name_tv);
            groupCreatedTimeTv = (TextView)view.findViewById(R.id.detail_view_group_added_time_tv);
            groupVideosCountTv = (TextView)view.findViewById(R.id.detail_view_group_videos_count_tv);
            groupMembersCountTv = (TextView)view.findViewById(R.id.detail_view_group_members_count_tv);
            groupDescTv = (TextView)view.findViewById(R.id.detail_view_group_desc_tv);

        }
    }


    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView emptyIv;
        public final TextView emptyTv;

        public EmptyViewHolder(View view) {
            super(view);
            emptyIv = (ImageView) view.findViewById(R.id.recycler_item_empty_iv);
            emptyTv = (TextView) view.findViewById(R.id.recycler_item_empty_tv);
        }
    }

    public class AuthUserAlbumViewHolder extends RecyclerView.ViewHolder {

        public final ImageView albumThumbIv;
        public final TextView albumNameTv;
        public final TextView albumVideosCountTv;
        public final TextView albumCreatedTimeTv;
        public final ImageView albumInfoIv;
        public final ImageView albumShareIv;
        public final ImageView albumViewIv;
        public final ImageView albumSettingsIv;

        public AuthUserAlbumViewHolder(View view) {
            super(view);

            albumThumbIv = (ImageView)view.findViewById(R.id.auth_user_album_thumb_iv);
            albumNameTv = (TextView) view.findViewById(R.id.auth_user_album_name_tv);
            albumVideosCountTv = (TextView) view.findViewById(R.id.auth_user_album_videos_count_tv);
            albumCreatedTimeTv = (TextView)view.findViewById(R.id.auth_user_album_created_time_tv);
            albumInfoIv = (ImageView)view.findViewById(R.id.auth_user_album_info_iv);
            albumShareIv = (ImageView)view.findViewById(R.id.auth_user_album_share_iv);
            albumViewIv = (ImageView)view.findViewById(R.id.auth_user_album_view_iv);
            albumSettingsIv = (ImageView)view.findViewById(R.id.auth_user_album_settings_iv);
        }
    }

    public class AlbumThumbTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout albumRl;
        public final ImageView albumThumbIv;
        public final TextView albumNameTv;
        public final TextView albumVideosCountTv;

        public AlbumThumbTypeViewHolder(View view) {
            super(view);
            albumRl = (RelativeLayout) view.findViewById(R.id.album_rl);
            albumThumbIv = (ImageView)view.findViewById(R.id.album_thumb_thumb_iv);
            albumNameTv = (TextView) view.findViewById(R.id.album_name_tv);
            albumVideosCountTv = (TextView) view.findViewById(R.id.album_videos_count_tv);
        }
    }

    public class AlbumDetailTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout albumRl;
        public final ImageView albumThumbIv;
        public final TextView albumNameTv;
        public final TextView albumVideosCountTv;
        public final TextView albumUserNameTv;
        public final TextView albumCreatedTimeTv;
        public final TextView albumDurationTv;

        public AlbumDetailTypeViewHolder(View view) {
            super(view);
            albumRl = (RelativeLayout) view.findViewById(R.id.detai_view_album_rl);
            albumThumbIv = (ImageView)view.findViewById(R.id.detai_view_album_thumb_thumb_iv);
            albumNameTv = (TextView) view.findViewById(R.id.detai_view_album_name_tv);
            albumVideosCountTv = (TextView) view.findViewById(R.id.detai_view_album_videos_count_tv);
            albumUserNameTv = (TextView)view.findViewById(R.id.detail_view_album_user_name_tv);
            albumCreatedTimeTv = (TextView)view.findViewById(R.id.detail_view_album_created_time_tv);
            albumDurationTv = (TextView)view.findViewById(R.id.detail_view_album_vodeo_duration_tv);
        }
    }

    public static class AlbumDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;

        public AlbumDetailViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.album_detail_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.album_detail_video_name_tv);
            avatarIv = (ImageView)view.findViewById(R.id.album_detail_video_user_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.album_detail_video_user_name_tv);
        }
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout projectRl;
        public final TextView projectNameTv;
        public final TextView projectVideosCountTv;
        public final ImageView settingsIv;
        public final ImageView deleteIv;

        public ProjectViewHolder(View view) {
            super(view);
            projectRl = (RelativeLayout) view.findViewById(R.id.project_rl);
            projectNameTv = (TextView) view.findViewById(R.id.project_name_tv);
            projectVideosCountTv = (TextView) view.findViewById(R.id.project_videos_count_tv);
            settingsIv = (ImageView)view.findViewById(R.id.project_settings_iv);
            deleteIv = (ImageView)view.findViewById(R.id.project_delete_iv);
        }
    }

    public static class UserProjectVideoThumbTypeViewHolder extends RecyclerView.ViewHolder {
        final ImageView videoThumbIv;
        final TextView videoNameTv;
        final TextView addedTimeTv;

        public UserProjectVideoThumbTypeViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.project_detail_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.project_detail_video_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.id.project_detail_video_added_time_tv);
        }
    }

    private class UserProjectVideoTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public UserProjectVideoTypeDetailViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.detail_view_project_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.detail_view_project_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_project_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_project_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_project_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_project_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_project_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_project_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_project_duration_tv);
        }
    }

    public class CateGoritesViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout categoriteRl;
        public final TextView categoriteNameTv;
        public final TextView categoriteVideosCountTv;

        public CateGoritesViewHolder(View view) {
            super(view);
            categoriteRl = (RelativeLayout) view.findViewById(R.id.categorite_rl);
            categoriteNameTv = (TextView) view.findViewById(R.id.categorite_name_tv);
            categoriteVideosCountTv = (TextView) view.findViewById(R.id.categorite_videos_count_tv);
        }
    }

    public class UserChannelViewDetailViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout channelRl;
        public final ImageView channelThumbIv;
        public final TextView channelNameTv;
        public final TextView channleAddedTimeTv;
        public final TextView channelDescTv;
        public final TextView channelVideosCountTv;
        public final TextView channelFollowersCountTv;

        public UserChannelViewDetailViewHolder(View view) {
            super(view);
            channelRl = (RelativeLayout)view.findViewById(R.id.channel_rl);
            channelThumbIv = (ImageView)view.findViewById(R.id.user_channle_thumb_iv_iv);
            channelNameTv = (TextView)view.findViewById(R.id.user_channle_name_tv);
            channleAddedTimeTv = (TextView)view.findViewById(R.id.user_channle_added_time_tv);
            channelDescTv = (TextView)view.findViewById(R.id.user_channel_desc_tv);
            channelVideosCountTv = (TextView)view.findViewById(R.id.user_channel_videos_count_tv);
            channelFollowersCountTv = (TextView)view.findViewById(R.id.
                    channels_followers_count_tv);
        }
    }

    public class UserChannelViewThumbViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout channelRl;
        public final ImageView channelThumbIv;
        public final TextView channelNameTv;
        public final TextView channelVideosCountTv;
        public final TextView channelFollowersCountTv;

        public UserChannelViewThumbViewHolder(View view) {
            super(view);
            channelRl = (RelativeLayout)view.findViewById(R.id.channel_rl);
            channelThumbIv = (ImageView)view.findViewById(R.id.user_channle_thumb_iv);
            channelNameTv = (TextView)view.findViewById(R.id.user_channle_name_tv);
            channelVideosCountTv = (TextView)view.findViewById(R.id.user_channel_videos_count_tv);
            channelFollowersCountTv = (TextView)view.findViewById(R.id.
                    channels_followers_count_tv);
        }
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout channelRl;
        public final ImageView channelThumbIv;
        public final TextView channelNameTv;
        public final TextView channelDescTv;
        public final TextView channelVideosCountTv;
        public final TextView channelFollowersCountTv;

        public ChannelViewHolder(View view) {
            super(view);
            channelRl = (RelativeLayout)view.findViewById(R.id.channel_rl);
            channelThumbIv = (ImageView)view.findViewById(R.id.channel_thumb_iv_iv);
            channelNameTv = (TextView)view.findViewById(R.id.channel_name_tv);
            channelDescTv = (TextView)view.findViewById(R.id.channel_desc_tv);
            channelVideosCountTv = (TextView)view.findViewById(R.id.channel_videos_count_tv);
            channelFollowersCountTv = (TextView)view.findViewById(R.id.
                    channel_followers_count_tv);
        }
    }

    public class ChannelDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView videoPlayCountsTv;


        public ChannelDetailViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.channel_detail_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.channel_detail_video_name_tv);
            avatarIv = (ImageView)view.findViewById(R.id.channel_detail_video_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.channel_detail_video_user_tv);
            videoPlayCountsTv = (TextView)view.findViewById(R.id.channel_detail_video_play_count_tv);

        }
    }

    public class FollowerOfChannelViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView userAvatarIv;
        public final TextView userNameTv;
        public final TextView userPrivilegeBt;
        public final TextView userLocationTv;
        public final TextView addedTimeTv;
        public final TextView userStatusTv;
        public final TextView userBioTv;
        public final TextView videosTv;
        public final TextView albumsTv;
        public final TextView channelsTv;
        public final TextView groupsTv;
        public final TextView followingTv;


        public FollowerOfChannelViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.channel_followers_rl);
            userAvatarIv = (ImageView) view.findViewById(R.id.channel_followers_avatar_iv);
            userPrivilegeBt = (Button)view.findViewById(R.id.channel_followers_user_privilege_bt);
            userLocationTv = (TextView)view.findViewById(R.id.channel_followers_location_tv);
            addedTimeTv = (TextView) view.findViewById(R.id.channel_followers_time_tv);
            userNameTv = (TextView) view.findViewById(R.id.channel_followers_name_tv);
            userStatusTv = (TextView)view.findViewById(R.id.channel_followers_user_status_tv);
            userBioTv = (TextView)view.findViewById(R.id.channel_followers_user_bio_tv);
            videosTv = (TextView)view.findViewById(R.id.channel_followers_videos_tv);
            albumsTv = (TextView)view.findViewById(R.id.channel_followers_albums_tv);
            channelsTv = (TextView)view.findViewById(R.id.channel_followers_channels_tv);
            groupsTv = (TextView)view.findViewById(R.id.channel_followers_groups_tv);
            followingTv = (TextView)view.findViewById(R.id.channel_followers_following_tv);

        }
    }

    public class GroupThumbTypeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView groupThumbIv;
        public final TextView groupNameTv;
        public final TextView groupDescTv;
        public final TextView groupVideosCountTv;
        public final TextView groupFollowersCountTv;

        public GroupThumbTypeViewHolder(View view) {
            super(view);
            groupThumbIv = (ImageView)view.findViewById(R.id.group_thumb_iv_iv);
            groupNameTv = (TextView)view.findViewById(R.id.group_name_tv);
            groupDescTv = (TextView)view.findViewById(R.id.group_desc_tv);
            groupVideosCountTv = (TextView)view.findViewById(R.id.group_videos_count_tv);
            groupFollowersCountTv = (TextView)view.findViewById(R.id.
                    group_followers_count_tv);
        }
    }

    public class GroupDetailTypeViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout groupRl;
        public final ImageView groupThumbIv;
        public final TextView groupNameTv;
        public final TextView groupDescTv;
        public final TextView groupVideosCountTv;
        public final TextView groupFollowersCountTv;

        public GroupDetailTypeViewHolder(View view) {
            super(view);
            groupRl = (RelativeLayout)view.findViewById(R.id.detail_view_group_rl);
            groupThumbIv = (ImageView)view.findViewById(R.id.detail_view_group_thumb_iv_iv);
            groupNameTv = (TextView)view.findViewById(R.id.detail_view_group_name_tv);
            groupDescTv = (TextView)view.findViewById(R.id.detail_view_group_desc_tv);
            groupVideosCountTv = (TextView)view.findViewById(R.id.detail_view_group_videos_count_tv);
            groupFollowersCountTv = (TextView)view.findViewById(R.id.
                    detail_view_group_followers_count_tv);
        }
    }

    private class GroupVideoTypeThumbViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView likedTimeTv;


        public GroupVideoTypeThumbViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.group_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.group_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.group_video_user_name_tv);
            likedTimeTv = (TextView)view.findViewById(R.id.group_video_time_tv);
        }
    }

    private class GroupVideoTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public GroupVideoTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_group_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_group_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_group_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_group_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_group_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_group_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_group_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_group_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_group_duration_tv);
        }
    }

    public class MemberOfGroupThumbTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView userAvatarIv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;

        public MemberOfGroupThumbTypeViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.thumb_view_group_member_rl);
            userAvatarIv = (ImageView) view.findViewById(R.id.thumb_view_group_member_avatar_iv);
            addedTimeTv = (TextView) view.findViewById(R.id.thumb_view_group_member_time_tv);
            userNameTv = (TextView) view.findViewById(R.id.thumb_view_group_member_name_tv);

        }
    }

    public class MemberOfGroupDetailTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView userAvatarIv;
        public final TextView userNameTv;
        public final TextView userPrivilegeBt;
        public final TextView userLocationTv;
        public final TextView joinedTimeTv;
        public final TextView userStatusTv;
        public final TextView userBioTv;
        public final TextView videosTv;
        public final TextView albumsTv;
        public final TextView channelsTv;
        public final TextView groupsTv;
        public final TextView followingTv;


        public MemberOfGroupDetailTypeViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.detail_view_group_member_rl);
            userAvatarIv = (ImageView) view.findViewById(R.id.detail_view_group_member_avatar_iv);
            userPrivilegeBt = (Button)view.findViewById(R.id.detail_view_group_member_privilege_bt);
            userLocationTv = (TextView)view.findViewById(R.id.detail_view_group_member_location_tv);
            joinedTimeTv = (TextView) view.findViewById(R.id.detail_view_group_member_added_time_tv);
            userNameTv = (TextView) view.findViewById(R.id.detail_view_group_member_name_tv);
            userStatusTv = (TextView)view.findViewById(R.id.detail_view_group_member_status_tv);
            userBioTv = (TextView)view.findViewById(R.id.detail_view_group_member_bio_tv);
            videosTv = (TextView)view.findViewById(R.id.detail_view_group_member_videos_tv);
            albumsTv = (TextView)view.findViewById(R.id.detail_view_group_member_albums_tv);
            channelsTv = (TextView)view.findViewById(R.id.detail_view_group_member_channels_tv);
            groupsTv = (TextView)view.findViewById(R.id.detail_view_group_member_groups_tv);
            followingTv = (TextView)view.findViewById(R.id.detail_view_group_member_following_tv);

        }
    }

    public class ModeratorOfGroupDetailTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView userAvatarIv;
        public final TextView userNameTv;
        public final TextView userPrivilegeBt;
        public final TextView userLocationTv;
        public final TextView joinedTimeTv;
        public final TextView userStatusTv;
        public final TextView userBioTv;
        public final TextView videosTv;
        public final TextView albumsTv;
        public final TextView channelsTv;
        public final TextView groupsTv;
        public final TextView followingTv;


        public ModeratorOfGroupDetailTypeViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.detail_view_group_member_rl);
            userAvatarIv = (ImageView) view.findViewById(R.id.detail_view_group_member_avatar_iv);
            userPrivilegeBt = (Button)view.findViewById(R.id.detail_view_group_member_privilege_bt);
            userLocationTv = (TextView)view.findViewById(R.id.detail_view_group_member_location_tv);
            joinedTimeTv = (TextView) view.findViewById(R.id.detail_view_group_member_added_time_tv);
            userNameTv = (TextView) view.findViewById(R.id.detail_view_group_member_name_tv);
            userStatusTv = (TextView)view.findViewById(R.id.detail_view_group_member_status_tv);
            userBioTv = (TextView)view.findViewById(R.id.detail_view_group_member_bio_tv);
            videosTv = (TextView)view.findViewById(R.id.detail_view_group_member_videos_tv);
            albumsTv = (TextView)view.findViewById(R.id.detail_view_group_member_albums_tv);
            channelsTv = (TextView)view.findViewById(R.id.detail_view_group_member_channels_tv);
            groupsTv = (TextView)view.findViewById(R.id.detail_view_group_member_groups_tv);
            followingTv = (TextView)view.findViewById(R.id.detail_view_group_member_following_tv);

        }
    }

    public class DetailOfUserViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout profileTablayoutDetailShotsRl;
        public final RelativeLayout profileTablayoutDetailLikesRl;
        public final RelativeLayout profileTablayoutDetailBucketsRl;
        public final RelativeLayout profileTablayoutDetailProjectsRl;
        public final RelativeLayout profileTablayoutDetailFollowersRl;
        public final RelativeLayout profileTablayoutDetailFollowingsRl;
        public final TextView profileTablayoutDetailShotsCountTv;
        public final TextView profileTablayoutDetailLikesCountTv;
        public final TextView profileTablayoutDetailBucketsCountTv;
        public final TextView profileTablayoutDetailProjectsCountTv;
        public final TextView profileTablayoutDetailFollowersCountTv;
        public final TextView  profileTablayoutDetailFollowingsCountTv;

        public DetailOfUserViewHolder(View view) {
            super(view);
            profileTablayoutDetailShotsRl = (RelativeLayout) view.findViewById(R.id.profile_tablayout_detail_shots_rl);
            profileTablayoutDetailLikesRl = (RelativeLayout) view.findViewById(R.id.profile_tablayout_detail_likes_rl);
            profileTablayoutDetailBucketsRl = (RelativeLayout) view.findViewById(R.id
                    .profile_tablayout_detail_buckets_rl);
            profileTablayoutDetailProjectsRl = (RelativeLayout) view.findViewById(R.id
                    .profile_tablayout_detail_projects_rl);
            profileTablayoutDetailFollowersRl = (RelativeLayout) view.findViewById(R.id
                    .profile_tablayout_detail_followers_rl);
            profileTablayoutDetailFollowingsRl = (RelativeLayout) view.findViewById(R.id
                    .profile_tablayout_detail_followings_rl);
            profileTablayoutDetailShotsCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_detail_shots_count_tv);
            profileTablayoutDetailLikesCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_detail_likes_count_tv);
            profileTablayoutDetailBucketsCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_detail_buckets_count_tv);
            profileTablayoutDetailProjectsCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_detail_projects_count_tv);
            profileTablayoutDetailFollowersCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_detail_followers_count_tv);
            profileTablayoutDetailFollowingsCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_detail_followings_count_tv);

        }
    }

    public class FollowOfUserThumbTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView userAvatarIv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;

        public FollowOfUserThumbTypeViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.thumb_view__following_rl);
            userAvatarIv = (ImageView) view.findViewById(R.id.thumb_view_following_avatar_iv);
            addedTimeTv = (TextView) view.findViewById(R.id.thumb_view_following_time_tv);
            userNameTv = (TextView) view.findViewById(R.id.thumb_view_following_name_tv);

        }
    }

    public class FollowOfUserDetailTypeViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView userAvatarIv;
        public final TextView userNameTv;
        public final TextView userPrivilegeBt;
        public final TextView userLocationTv;
        public final TextView addedTimeTv;
        public final TextView userStatusTv;
        public final TextView userBioTv;
        public final TextView videosTv;
        public final TextView albumsTv;
        public final TextView channelsTv;
        public final TextView groupsTv;
        public final TextView followingTv;


        public FollowOfUserDetailTypeViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.detail_view__following_rl);
            userAvatarIv = (ImageView) view.findViewById(R.id.detail_view_following_avatar_iv);
            userPrivilegeBt = (Button)view.findViewById(R.id.detail_view_following_user_privilege_bt);
            userLocationTv = (TextView)view.findViewById(R.id.detail_view_following_location_tv);
            addedTimeTv = (TextView) view.findViewById(R.id.detail_view_following_time_tv);
            userNameTv = (TextView) view.findViewById(R.id.detail_view_following_name_tv);
            userStatusTv = (TextView)view.findViewById(R.id.detail_view_following_user_status_tv);
            userBioTv = (TextView)view.findViewById(R.id.detail_view_following_user_bio_tv);
            videosTv = (TextView)view.findViewById(R.id.detail_view_following_videos_tv);
            albumsTv = (TextView)view.findViewById(R.id.detail_view_following_albums_tv);
            channelsTv = (TextView)view.findViewById(R.id.detail_view_following_channels_tv);
            groupsTv = (TextView)view.findViewById(R.id.detail_view_following_groups_tv);
            followingTv = (TextView)view.findViewById(R.id.detail_view_following_following_tv);

        }
    }

    private class WatchedVideoViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox watchedCb;
        public final ImageView watchedVideoThumbIv;
        public final TextView watchedVideoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView playCountTv;

        public WatchedVideoViewHolder(View view) {
            super(view);

            watchedCb = (CheckBox)view.findViewById(R.id.watched_video_cb);
            watchedVideoThumbIv = (ImageView)view.findViewById(R.id.watched_video_thumb_iv);
            watchedVideoNameTv = (TextView)view.findViewById(R.id.watched_video_name_tv)
            avatarIv = (ImageView)view.findViewById(R.id.watched_video_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.watched_video_user_name_tv);
            playCountTv = (TextView)view.findViewById(R.id.watched_video_play_count_tv);
        }
    }

    private class LikedVideoTypeThumbViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView likedTimeTv;


        public LikedVideoTypeThumbViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.like_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.like_video_name_tv)
            userNameTv = (TextView)view.findViewById(R.id.like_video_user_name_tv);
            likedTimeTv = (TextView)view.findViewById(R.id.like_video_time_tv);
        }
    }

    private class WatchLaterVideoTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public WatchLaterVideoTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_liked_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_liked_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_liked_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_liked_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_liked_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_liked_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_liked_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_liked_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_duration_tv);
        }
    }

    private class WatchLaterVideoTypeThumbViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;


        public WatchLaterVideoTypeThumbViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.like_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.like_video_name_tv)
            userNameTv = (TextView)view.findViewById(R.id.like_video_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.id.like_video_time_tv);
        }
    }

    private class LikedVideoTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView likedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public LikedVideoTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_liked_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_liked_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_liked_video_user_name_tv);
            likedTimeTv = (TextView)view.findViewById(R.detail_view_liked_video_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_liked_video_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_liked_video_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_liked_video_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_liked_video_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_duration_tv);
        }
    }

    public class UploadedVideosViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox uploadedCb;
        public final ImageView uploadedVideoThumbIv;
        public final TextView uploadedVideoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView playCountTv;

        public UploadedVideosViewHolder(View view) {
            super(view);

            uploadedCb = (CheckBox)view.findViewById(R.id.uploaded_video_cb);
            uploadedVideoThumbIv = (ImageView)view.findViewById(R.id.uploaded_video_thumb_iv);
            uploadedVideoNameTv = (TextView)view.findViewById(R.id.uploaded_video_name_tv)
            avatarIv = (ImageView)view.findViewById(R.id.uploaded_video_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.uploaded_video_user_name_tv);
            playCountTv = (TextView)view.findViewById(R.id.uploaded_video_play_count_tv);
        }
    }

    public class GroupMemberViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout groupMemberRl;
        public final ImageView groupMemberAvatarIv;
        public final TextView groupMemberNameTv;
        public final TextView groupMemberLocationTv;
        public final TextView groupMemberVideosCountTv;
        public final TextView followersOfMemberCountTv;

        public GroupMemberViewHolder(View view) {
            super(view);
            groupMemberRl = (RelativeLayout) view.findViewById(R.id.group_member_rl);
            groupMemberAvatarIv = (ImageView) view.findViewById(R.id.group_member_avatar_iv);
            groupMemberLocationTv = (TextView) view.findViewById(R.id.group_member_location_tv);
            groupMemberNameTv = (TextView) view.findViewById(R.id.group_member_name_tv);
            groupMemberVideosCountTv = (TextView) view.findViewById(R.id.group_member_videos_count_tv);
            followersOfMemberCountTv = (TextView) view.findViewById(R.id
                    .group_member_follow_count_tv);
        }
    }

    public class FollowingOfUserViewHolder extends RecyclerView.ViewHolder {

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView onlineTimeTv;

        public FollowingOfUserViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.following_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.following_name_tv);
            onlineTimeTv = (TextView)view.findViewById(R.id.following_time_tv);
        }
    }

    public class SuggestedOfUserViewHolder extends RecyclerView.ViewHolder {

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView onlineTimeTv;

        public SuggestedOfUserViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.suggested_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.suggested_name_tv);
            onlineTimeTv = (TextView)view.findViewById(R.id.suggested_common_cout_tv);
        }
    }


    public class UserPictureViewHolder extends RecyclerView.ViewHolder {
        public final ImageView userPictureIv;
        public final TextView userPictureNameTv;

        public UserPictureViewHolder(View view) {
            super(view);
            userPictureIv = (ImageView)view.findViewById(R.id.user_picture_iv);
            userPictureNameTv = (TextView)view.findViewById(R.id.user_picture_name_tv);
        }
    }

    public class FeedPeopleViewHolder extends RecyclerView.ViewHolder {

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView followingTimeTv;
        public final CheckBox videosCb;
        public final CheckBox appearancesCb;
        public final CheckBox likesCb;

        public FeedPeopleViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.following_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.following_name_tv);
            followingTimeTv = (TextView)view.findViewById(R.id.following_time_tv);
            videosCb = (CheckBox)view.findViewById(R.id.feed_videos_cb);
            appearancesCb = (CheckBox)view.findViewById(R.id.feed_appearances_cb);
            likesCb = (CheckBox)view.findViewById(R.id.feed_likes_cb);
        }
    }

    public class FeedChannleViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox channleCb;
        public final TextView channleNameTv;
        public final TextView channleFollowingTimeTv;
        public final TextView channleVideosCountTv;
        public final TextView channleFollowersCountTv;

        public FeedChannleViewHolder(View view) {
            super(view);

            channleCb = (CheckBox)view.findViewById(R.id.feed_channel_cb);
            channleNameTv = (TextView)view.findViewById(R.id.feed_channel_name_tv);
            channleFollowingTimeTv = (TextView)view.findViewById(R.id.feed_channel_following_time_tv);
            channleVideosCountTv = (TextView)view.findViewById(R.id.feed_channel_videos_count_tv);
            channleFollowersCountTv  = (TextView)view.findViewById(R.id.feed_channel_followers_count_tv);
        }
    }

    public class FeedTagsViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox tagsCb;
        public final TextView tagsNameTv;
        public final TextView tagsFollowingTimeTv;
        public final TextView tagsVideosCountTv;
        public final TextView tagsFollowersCountTv;

        public FeedTagsViewHolder(View view) {
            super(view);

            tagsCb = (CheckBox)view.findViewById(R.id.feed_tags_cb);
            tagsNameTv = (TextView)view.findViewById(R.id.feed_tags_name_tv);
            tagsFollowingTimeTv = (TextView)view.findViewById(R.id.feed_tags_following_time_tv);
            tagsVideosCountTv = (TextView)view.findViewById(R.id.feed_tags_videos_count_tv);
            tagsFollowersCountTv  = (TextView)view.findViewById(R.id.feed_tags_followers_count_tv);
        }
    }

    public class FeedGroupViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox groupCb;
        public final TextView groupNameTv;
        public final TextView groupFollowingTimeTv;
        public final TextView groupVideosCountTv;
        public final TextView groupFollowersCountTv;

        public FeedGroupViewHolder(View view) {
            super(view);

            groupCb = (CheckBox)view.findViewById(R.id.feed_group_cb);
            groupNameTv = (TextView)view.findViewById(R.id.feed_group_name_tv);
            groupFollowingTimeTv = (TextView)view.findViewById(R.id.feed_group_following_time_tv);
            groupVideosCountTv = (TextView)view.findViewById(R.id.feed_group_videos_count_tv);
            groupFollowersCountTv  = (TextView)view.findViewById(R.id.feed_group_followers_count_tv);
        }
    }

    public class FeedCategoryViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox categoryCb;
        public final TextView categoryNameTv;
        public final TextView categoryFollowingTimeTv;
        public final TextView categoryVideosCountTv;


        public FeedCategoryViewHolder(View view) {
            super(view);

            categoryCb = (CheckBox)view.findViewById(R.id.feed_category_cb);
            categoryNameTv = (TextView)view.findViewById(R.id.feed_category_name_tv);
            categoryFollowingTimeTv = (TextView)view.findViewById(R.id.feed_category_following_time_tv);
            categoryVideosCountTv = (TextView)view.findViewById(R.id.feed_category_videos_count_tv);
        }
    }

    public class ExploreStuffViewHolder extends RecyclerView.ViewHolder {

        public final ImageView stuffIconIv;
        public final TextView stuffNameTv;
        public final TextView stuffDescTv;
        public ExploreStuffViewHolder(View view) {
            super(view);

            stuffIconIv = (ImageView)view.findViewById(R.id.explore_stuff_icon_iv);
            stuffNameTv = (ImageView)view.findViewById(R.id.explore_stuff_name_tv);
            stuffDescTv = (ImageView)view.findViewById(R.id.explore_stuff_desc_tv);
        }
    }

    public class MyPrivateMessageViewHolder extends RecyclerView.ViewHolder{

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView messageTitleTv;
        public final TextView sendTimeTv;

        public MyPrivateMessageViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.message_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.message_user_name_tv);
            messageTitleTv = (TextView)view.findViewById(R.id.message_title_tv);
            sendTimeTv = (TextView)view.findViewById(R.id.message_send_time_tv);
        }
    }

    public class UnReadMessageViewHolder extends RecyclerView.ViewHolder{

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView messageTitleTv;
        public final TextView sendTimeTv;

        public UnReadMessageViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.unread_message_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.unread_message_user_name_tv);
            messageTitleTv = (TextView)view.findViewById(R.unid.read_message_title_tv);
            sendTimeTv = (TextView)view.findViewById(R.id.unread_message_send_time_tv);
        }
    }

    public class ReadMessageViewHolder extends RecyclerView.ViewHolder{

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView messageTitleTv;
        public final TextView sendTimeTv;

        public ReadMessageViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.read_message_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.read_message_user_name_tv);
            messageTitleTv = (TextView)view.findViewById(R.id.read_message_title_tv);
            sendTimeTv = (TextView)view.findViewById(R.id.read_message_send_time_tv);
        }
    }

    public class SentMessageViewHolder extends RecyclerView.ViewHolder{

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView messageTitleTv;
        public final TextView sendTimeTv;

        public SentMessageViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.sent_message_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.sent_message_user_name_tv);
            messageTitleTv = (TextView)view.findViewById(R.id.sent_message_title_tv);
            sendTimeTv = (TextView)view.findViewById(R.id.sent_message_send_time_tv);
        }
    }

    public class CommentsActivityViewHolder extends RecyclerView.ViewHolder{

        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView messageTitleTv;
        public final TextView createTimeTv;

        public CommentsActivityViewHolder(View view) {
            super(view);

            avatarIv = (ImageView)view.findViewById(R.id.comments_activity_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.comments_activity_user_name_tv);
            messageTitleTv = (TextView)view.findViewById(R.id.comments_activity_title_tv);
            createTimeTv = (TextView)view.findViewById(R.id.comments_activity_send_time_tv);
        }
    }

    public class MyPeopelOfUserMessagesViewHolder extends RecyclerView.ViewHolder {
        public final ImageView myPeopleAvatarIv;
        public final TextView myPeopleNameTv;
        public final TextView myPeopleLocationTv;
        public final TextView myPeopleVideosCountTv;
        public final TextView followersOfmyPeopleCountTv;

        public MyPeopelOfUserMessagesViewHolder(View view) {
            super(view);
            myPeopleAvatarIv = (ImageView) view.findViewById(R.id.my_people_avatar_iv);
            myPeopleLocationTv = (TextView) view.findViewById(R.id.my_people_location_tv);
            myPeopleNameTv = (TextView) view.findViewById(R.id.my_people_name_tv);
            myPeopleVideosCountTv = (TextView) view.findViewById(R.id.my_people_videos_count_tv);
            followersOfmyPeopleCountTv = (TextView) view.findViewById(R.id
                    .my_people_follow_count_tv);
        }
    }

    public class VideoOfVimeoOnDemandPagesTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfVimeoOnDemandPagesTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_iew_video_of_vimeo_on_demand_pages_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_video_of_vimeo_on_demand_pages_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_video_of_vimeo_on_demand_pages_added_time);
        }
    }

    private class VideoOfVimeoOnDemandPagesTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfVimeoOnDemandPagesTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_video_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_iew_video_of_vimeo_on_demand_pages_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_plays_count_i);
            likesCountTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_likes_count_i);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_comments_count_i);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_iew_video_of_vimeo_on_demand_pages_duration_tv);
        }
    }

    public class OnDemandPageEmptyView extends RecyclerView.ViewHolder{

        public final Button upgradeBt;

        public OnDemandPageEmptyView(View view) {
            super(view);
            upgradeBt = (Button)view.findViewById(R.id.empty_view_upgrade_bt);
        }
    }

    private class VideoOfRegionTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfRegionTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_video_of_a_region_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_video_of_a_region_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_plays_count_tv);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_likes_count_tv);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_comments_count_tv);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_region_duration_tv);
        }
    }

    public class VideoOfRegionTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfRegionTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_iew_video_of_a_region_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_region_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_region_added_time);
        }
    }

    public class BankCardViewHolder extends RecyclerView.ViewHolder{

        public final ImageView iconIv;
        public final TextView bankNameTv;
        public final TextView cardLimitTv;

        public BankCardViewHolder(View view) {
            super(view);

            iconIv = (ImageView)view.findViewById(R.id.bank_icon_iv);
            bankNameTv = (TextView)view.findViewById(R.id.bank_name_tv);
            cardLimitTv = (TextView)view.findViewById(R.id.bank_card_limit_tv);
        }
    }

    public class SeasonDetailTypeViewHolder extends RecyclerView.ViewHolder {

        public final ImageView seasonThumbIv;
        public final TextView seasonNameTv;
        public final TextView addedTimeTv;


        public SeasonDetailTypeViewHolder(View view) {
            super(view);
            seasonThumbIv = (ImageView)view.findViewById(R.id.detail_view_season_thumb_iv_iv);
            seasonNameTv = (TextView)view.findViewById(R.id.detail_view_season_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.id.detail_view_season_added_time_tv);

        }
    }

    private class VideoOfSeasonTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfSeasonTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_video_of_a_season_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_video_of_a_season_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_plays_count_tv);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_likes_count_tv);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_comments_count_tv);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_season_duration_tv);
        }
    }

    public class VideoOfSeasonTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfSeasonTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_video_of_a_season_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_season_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_season_added_time);
        }
    }

    public class  GenreOfOndemandPagesViewHolder extends RecyclerView.ViewHolder{

        public final ImageView genreThumbIv;
        public final TextView genreNameTv;
        public final TextView genreConnectionsCountsTv;
        public GenreOfOndemandPagesViewHolder(View view) {
            super(view);

            genreThumbIv = (ImageView)view.findViewById(R.id.genre_thumb_iv);
            genreNameTv = (TextView)view.findViewById(R.id.genre_name_tv);
            genreConnectionsCountsTv = (TextView)view.findViewById(R.id.genre_connections_count_tv);
        }
    }

    private class VideoOfGenreTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfGenreTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_video_of_a_genre_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_video_of_a_genre_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_plays_count_tv);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_likes_count_tv);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_comments_count_tv);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_genre_duration_tv);
        }
    }

    public class VideoOfGenreTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfGenreTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_video_of_a_genre_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_genre_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_genre_added_time);
        }
    }

    public class  PosterOfOndemandPagesViewHolder extends RecyclerView.ViewHolder{

        public final ImageView posterThumbIv;
        public final TextView posterNameTv;
        public final TextView posterConnectionsCountsTv;
        public PosterOfOndemandPagesViewHolder(View view) {
            super(view);

            posterThumbIv = (ImageView)view.findViewById(R.id.poster_thumb_iv);
            posterNameTv = (TextView)view.findViewById(R.id.poster_name_tv);
            posterConnectionsCountsTv = (TextView)view.findViewById(R.id.poster_connections_count_tv);
        }
    }

    private class VideoOfPosterTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfPosterTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_video_of_a_poster_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_video_of_a_poster_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_plays_count_tv);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_likes_count_tv);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_comments_count_tv);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_of_a_poster_duration_tv);
        }
    }

    public class VideoOfPosterTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfPosterTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_video_of_a_poster_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_poster_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_video_of_a_poster_added_time);
        }
    }

    public class  BackgroudOfOndemandPagesViewHolder extends RecyclerView.ViewHolder{

        public final ImageView backgroudThumbIv;
        public final TextView backgroudNameTv;
        public final TextView backgroudTypeTv;
        public BackgroudOfOndemandPagesViewHolder(View view) {
            super(view);

            backgroudThumbIv = (ImageView)view.findViewById(R.id.on_demand_page_backgroud_iv);
            backgroudNameTv = (TextView)view.findViewById(R.id.on_demand_page_backgroud_name_tv);
            backgroudTypeTv = (TextView)view.findViewById(R.id.on_demand_page_backgroud_type_tv);
        }
    }

    public class  BackgroudPictureOfOndemandPagesViewHolder extends RecyclerView.ViewHolder{

        public final ImageView backgroudPictureThumbIv;
        public final TextView backgroudPictureNameTv;
        public final TextView backgroudPictureTypeTv;
        public BackgroudPictureOfOndemandPagesViewHolder(View view) {
            super(view);

            backgroudPictureThumbIv = (ImageView)view.findViewById(R.id.backgroud_picture_iv);
            backgroudPictureNameTv = (TextView)view.findViewById(R.id.backgroud_picture_name_tv);
            backgroudPictureTypeTv = (TextView)view.findViewById(R.id.backgroud_picture_type_tv);
        }
    }

    private class VideoOfOnDemandPagesInAGenreTypeDetailViewHolder extends RecyclerView.ViewHolder {

        public final ImageView VideoThumbIv;
        public final TextView VideoNameTv;
        public final TextView userNameTv;
        public final TextView addedTimeTv;
        public final TextView videoDescTv;
        public final TextView playsCountTv;
        public final TextView likesCountTv;
        public final TextView commentsCountTv;
        public final TextView videoDurationTv;


        public VideoOfOnDemandPagesInAGenreTypeDetailViewHolder(View view) {
            super(view);

            VideoThumbIv = (ImageView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_video_thumb_iv);
            VideoNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_name_tv);
            userNameTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_user_name_tv);
            addedTimeTv = (TextView)view.findViewById(R.detail_view_video_of_on_demand_pages_in_a_genre_added_time_tv);
            videoDescTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_desc_tv);
            playsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_plays_count_tv);
            likesCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_likes_count_tv);
            commentsCountTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_comments_count_tv);
            videoDurationTv = (TextView)view.findViewById(R.id.detail_view_video_of_on_demand_pages_in_a_genre_duration_tv);
        }
    }

    public class VideoOfOnDemandPagesInAGenreTypeThumbViewHolder extends RecyclerView.ViewHolder{

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoAddedTimeTv;

        public  VideoOfOnDemandPagesInAGenreTypeThumbViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.thumb_view_video_of_on_demand_pages_in_a_genre_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.thumb_view_video_of_on_demand_pages_in_a_genre_name_tv);
            videoAddedTimeTv = (TextView)view.findViewById(R.id.thumb_view_video_of_on_demand_pages_in_a_genre_added_time);
        }
    }

    public class  OndemandPageOfPageGenreViewHolder extends RecyclerView.ViewHolder{

        public final ImageView onDemandPageThumbIv;
        public final TextView onDemandPageNameTv;
        public final TextView onDemandPageVideosCountsTv;
        public OndemandPageOfPageGenreViewHolder(View view) {
            super(view);

            onDemandPageThumbIv = (ImageView)view.findViewById(R.id.genre_thumb_iv);
            onDemandPageNameTv = (TextView)view.findViewById(R.id.genre_name_tv);
            onDemandPageVideosCountsTv = (TextView)view.findViewById(R.id.genre_connections_count_tv);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public <T> void addData(T d) {
        list.add(d);
        for (int i = 0; i < list.size(); i++) {
            booleanlist.add(false);
        }
        notifyDataSetChanged();
    }

    //更改集合内部存储的状态
    public void initCheck(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            //更改指定位置的数据
            booleanlist.set(i,flag);
        }
    }

    //清空所有数据
    public void deleteAllData() {
        list.clear();
        booleanlist.clear();
        notifyDataSetChanged();
    }

    //删除选中的数据
    public void deletingData() {
        int y=0;
        for (int i = 0; i < list.size(); i++) {
            if(booleanlist.get(i)!=null && booleanlist.get(i) ) {
                list.remove(i);
                booleanlist.remove(i);
                y++;
                i--;
            }
        }
        notifyDataSetChanged();
    }
    public void selectAll(){
        initCheck(true);
        notifyDataSetChanged();
    }
    public void unSelectAll(){
        initCheck(false);
        notifyDataSetChanged();
    }


    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setLoadingMore(onLoadingMore loadingMore) {
        this.loadingMore = loadingMore;
    }

    public interface onLoadingMore {
        void onLoadMore();
    }
}
