package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.R;
import com.menglingpeng.vonvimeo.mvp.interf.OnRecyclerListItemListener;
import com.menglingpeng.vonvimeo.mvp.model.Album;
import com.menglingpeng.vonvimeo.mvp.model.Category;
import com.menglingpeng.vonvimeo.mvp.model.Channel;
import com.menglingpeng.vonvimeo.mvp.model.ChannelVideo;
import com.menglingpeng.vonvimeo.mvp.model.Group;
import com.menglingpeng.vonvimeo.mvp.model.Project;
import com.menglingpeng.vonvimeo.mvp.model.Stuff;
import com.menglingpeng.vonvimeo.mvp.model.Tag;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.mvp.model.Video;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.ImageLoader;
import com.menglingpeng.vonvimeo.utils.TextUtil;

import java.util.ArrayList;

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

    public RecyclerAdapter(RecyclerView recyclerView, Context context, Fragment fragment, final String type,
                           OnRecyclerListItemListener listener) {
        this.type = type;
        this.context = context;
        this.fragment = fragment;
        mListener = listener;
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
                        viewHolder = new UserChannelViewHolder(view);
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
                        viewHolder = new LikedVideoViewHolder(view);
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
                    case Constants.REQUEST_LIST_ALL_FEATURED_CHANNLES_SORT_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_DIRECTORY_CHANNLES_SORT_BY_DATE:
                        view = inflater.inflate(R.layout.recycler_item_user_channel, parent, false);
                        viewHolder = new ChannelViewHolder(view);
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
        if(holder instanceof FeedVideoViewHolder){
            final FeedVideoViewHolder videoViewHolder = (FeedVideoViewHolder)holder;
        } else if(holder instanceof UploadedVideoThumbViewHolder){
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
        }else if(holder instanceof UploadedVideoDetailViewHolder){

        }else if(holder instanceof GroupDetaiTypeViewHolder){
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
        }
        else if ((holder instanceof AlbumViewHolder)){
            final AlbumViewHolder viewHolder = (AlbumViewHolder)holder;
            final Album album = (Album) list.get(position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, album);
                    }
                }
            });
            viewHolder.albumNameTv.setText(album.getName());
            viewHolder.albumVideosCountTv.setText(TextUtil.setBeforeBold(String.valueOf(album.getShots_count()),
                    context.getString(R.string.videos)));
        }else if (holder instanceof ProjectViewHolder){
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
        }else if (holder instanceof CateGoritesViewHolder){
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
        }else if (holder instanceof UserChannelViewHolder){
            final UserChannelViewHolder viewHolder = (UserChannelViewHolder)holder;
            final Channel channel = (Channel)list.get(position);
            String url;
            url = channel.getData().get(position).getLink();
            ImageLoader.load(fragment, url, viewHolder.channelThumbIv, false);
            viewHolder.channelNameTv.setText(channel.getData().get(position).getName());
            viewHolder.channelDescTv.setText(channel.getData().get(position).getDescription());
            viewHolder.channelFollowersCountTv.setText(channel.getData().get(position).
                    getMetadata().getConnections().getUsers().getTotal());
            viewHolder.channelVideosCountTv.setText(channel.getData().get(position).getMetadata()
                    .getConnections().getVideos().getTotal());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channel);
                    }
                }
            });
        }else if(holder instanceof ChannelDetailViewHolder){
            final ChannelDetailViewHolder viewHolder = (ChannelDetailViewHolder)holder;
            final ChannelVideo channelVideo = (ChannelVideo)list.get(position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channelVideo);
                    }
                }
            });
        }else if(holder instanceof FollowOfUserViewHolder){
            final FollowOfUserViewHolder viewHolder = (FollowOfUserViewHolder) holder;
            User user;
            if (type.indexOf(Constants.FOLLOWING) != -1) {
                final Following following = (Following) list.get(position);
                user = following.getFollowee();
            } else {
                final Follower follower = (Follower) list.get(position);
                user = follower.getFollower();
            }
            final User userFollow = user;
            ImageLoader.loadCricleImage(context, userFollow.getAvatar_url(), viewHolder.followerAvatarIv);
            viewHolder.followerNameTv.setText(userFollow.getName());
            viewHolder.followerLocationTv.setText(userFollow.getLocation());
            viewHolder.followerShotsCountTv.setText(TextUtil.setBeforeBold(String.valueOf(userFollow.getShots_count()
            ), context.getString(R.string.explore_spinner_list_shots)));
            viewHolder.followersOfFollowerCountTv.setText(TextUtil.setBeforeBold(String.valueOf(userFollow
                    .getFollowers_count()), context.getText(R.string.followers).toString()));
            viewHolder.followerRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, String.valueOf(userFollow.getId()));
                    }
                }
            });
        }else if (holder instanceof ProjectViewHolder){
            final ProjectViewHolder viewHolder = (ProjectViewHolder)holder;
            final Project project = (Project)list.get(position);
            viewHolder.projectNameTv.setText(project.getName());
            viewHolder.projectVideosCountTv.setText(project.getMetadata().getConnections().getVideos().getTotal());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, project.getName());
                    }
                }
            });

        } else if(holder instanceof ProjectDetailViewHolder){
            final ProjectDetailViewHolder viewHolder = (ProjectDetailViewHolder)holder;
            final Video video = (Video)list.get(position);
            String thumbUrl = video.getData().get(position).getPictures().getUri();
            String avatarUrl = video.getData().get(position).getUser().getPictures().getUri();
            ImageLoader.load(context,thumbUrl ,viewHolder.groupDetailVideoThumbIv, true );
            viewHolder.groupDetailVideoNameTv.setText(video.getData().get(position).getName());
            viewHolder.userNameTv.setText(video.getData().get(position).getUser().getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, video);
                    }
                }
            });
        }else if(holder instanceof FeedPeopleViewHolder){
            final FeedPeopleViewHolder viewHolder = (FeedPeopleViewHolder)holder;
            final Following following = (Following) list.get(position);
            String avatarUrl = following.getData().get(position).getPictures().getUri();
            ImageLoader.loadCricleImage(fragment, avatarUrl, viewHolder.avatarIv);
            viewHolder.userNameTv.setText(following.getData().get(position).getName());
            viewHolder.followingTimeTv.setText(following.getData().get(position).getCreated_time());
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
                        mListener.onRecyclerFragmentListListener(viewHolder, following);
                    }
                }
            });
        }else if (holder instanceof FeedChannleViewHolder){
            final FeedChannleViewHolder viewHolder = (FeedChannleViewHolder)holder;
            final Channel channel = (Channel)list.get(position);
            viewHolder.channleNameTv.setText(channel.getData().get(position).getName());
            viewHolder.channleFollowingTimeTv.setText(channel.getData().get(position).getModified_time());
            viewHolder.channleVideosCountTv.setText(channel.getData().get(position).getMetadata().getConnections().
                    getVideos().getTotal());
            viewHolder.channleFollowersCountTv.setText(channel.getData().get(position).getMetadata().getConnections().
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
        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final TextView videoSortTv;

        public UploadedVideoDetailViewHolder(View view) {
            super(view);

            videoThumbIv = (ImageView)view.findViewById(R.id.detail_view_uploaded_video_thumb_iv);
            videoNameTv = (TextView)view.findViewById(R.id.detail_view_uploaded_video_name_tv);
            videoSortTv = (TextView)view.findViewById(R.id.detail_view_uploaded_video_sort_tv);
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


    public class FeedVideoViewHolder extends RecyclerView.ViewHolder{

        public FeedVideoViewHolder(View itemView) {
            super(itemView);
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

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout albumRl;
        public final TextView albumNameTv;
        public final TextView albumVideosCountTv;

        public AlbumViewHolder(View view) {
            super(view);
            albumRl = (RelativeLayout) view.findViewById(R.id.album_rl);
            albumNameTv = (TextView) view.findViewById(R.id.album_name_tv);
            albumVideosCountTv = (TextView) view.findViewById(R.id.album_videos_count_tv);
        }
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout projectRl;
        public final TextView projectNameTv;
        public final TextView projectVideosCountTv;

        public ProjectViewHolder(View view) {
            super(view);
            projectRl = (RelativeLayout) view.findViewById(R.id.project_rl);
            projectNameTv = (TextView) view.findViewById(R.id.project_name_tv);
            projectVideosCountTv = (TextView) view.findViewById(R.id.project_videos_count_tv);
        }
    }

    public static class ProjectDetailViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox groupDetailCb;
        public final ImageView groupDetailVideoThumbIv;
        public final TextView groupDetailVideoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView playCountTv;

        public ProjectDetailViewHolder(View view) {
            super(view);
            groupDetailCb = (CheckBox)view.findViewById(R.id.group_detail_video_cb);
            groupDetailVideoThumbIv = (ImageView)view.findViewById(R.id.group_detail_video_thumb_iv);
            groupDetailVideoNameTv = (TextView)view.findViewById(R.id.group_detail_video_name_tv)
            avatarIv = (ImageView)view.findViewById(R.id.group_detail_video_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.group_detail_video_user_name_tv);
            playCountTv = (TextView)view.findViewById(R.id.group_detail_video_play_count_tv);
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

    public class UserChannelViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout channelRl;
        public final ImageView channelThumbIv;
        public final TextView channelNameTv;
        public final TextView channleAddedTimeTv;
        public final TextView channelDescTv;
        public final TextView channelVideosCountTv;
        public final TextView channelFollowersCountTv;

        public UserChannelViewHolder(View view) {
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

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        public final ImageView groupIv;

        public GroupViewHolder(View view) {
            super(view);
            groupIv = (ImageView)view.findViewById(R.id.group_iv);
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

    public class FollowOfUserViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout followerRl;
        public final ImageView followerAvatarIv;
        public final TextView followerNameTv;
        public final TextView followerLocationTv;
        public final TextView followerShotsCountTv;
        public final TextView followersOfFollowerCountTv;

        public FollowOfUserViewHolder(View view) {
            super(view);
            followerRl = (RelativeLayout) view.findViewById(R.id.profile_tablayout_follow_rl);
            followerAvatarIv = (ImageView) view.findViewById(R.id.profile_tablayout_follow_avatar_iv);
            followerLocationTv = (TextView) view.findViewById(R.id.profile_tablayout_follow_location_tv);
            followerNameTv = (TextView) view.findViewById(R.id.profile_tablayout_follow_name_tv);
            followerShotsCountTv = (TextView) view.findViewById(R.id.profile_tablayout_follow_shots_count_tv);
            followersOfFollowerCountTv = (TextView) view.findViewById(R.id
                    .profile_tablayout_followers_of_follow_count_tv);
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

    private class LikedVideoViewHolder extends RecyclerView.ViewHolder {

        public final CheckBox likedCb;
        public final ImageView likedVideoThumbIv;
        public final TextView likedVideoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView playCountTv;

        public LikedVideoViewHolder(View view) {
            super(view);

            likedCb = (CheckBox)view.findViewById(R.id.like_video_cb);
            likedVideoThumbIv = (ImageView)view.findViewById(R.id.like_video_thumb_iv);
            likedVideoNameTv = (TextView)view.findViewById(R.id.like_video_name_tv)
            avatarIv = (ImageView)view.findViewById(R.id.like_video_avatar_iv);
            userNameTv = (TextView)view.findViewById(R.id.like_video_user_name_tv);
            playCountTv = (TextView)view.findViewById(R.id.like_video_play_count_tv);
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

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }
    }

    public <T> void addData(T d) {
        list.add(d);
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
