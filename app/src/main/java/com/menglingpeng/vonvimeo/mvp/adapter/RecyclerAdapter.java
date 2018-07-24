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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.R;
import com.menglingpeng.vonvimeo.mvp.interf.OnRecyclerListItemListener;
import com.menglingpeng.vonvimeo.mvp.model.Album;
import com.menglingpeng.vonvimeo.mvp.model.Categorite;
import com.menglingpeng.vonvimeo.mvp.model.Channel;
import com.menglingpeng.vonvimeo.mvp.model.ChannelVideo;
import com.menglingpeng.vonvimeo.mvp.model.Follower;
import com.menglingpeng.vonvimeo.mvp.model.Following;
import com.menglingpeng.vonvimeo.mvp.model.Project;
import com.menglingpeng.vonvimeo.mvp.model.User;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.HttpUtils;
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
                        viewHolder = new ChannelsViewHolder(view);
                        break;
                    case Constants.REQUEST_LIST_ALL_VIDEO_FOR_A_CHANNEL:
                        view = inflater.inflate(R.layout.recycler_item_channel_detail, parent, false);
                        viewHolder = new ChannelViewHolder(view);
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
                    default:
                        break;
                }
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FeedVideoViewHolder){
            final FeedVideoViewHolder videoViewHolder = (FeedVideoViewHolder)holder;
        } else if ((holder instanceof AlbumViewHolder)){
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
            final Categorite categorite = (Categorite) list.get(position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, categorite);
                    }
                }
            });
            viewHolder.categoriteNameTv.setText(categorite.getName());
            viewHolder.categoriteVideosCountTv.setText(TextUtil.setBeforeBold(String.valueOf(categorite.getShots_count()),
                    context.getString(R.string.videos)));
        }else if (holder instanceof ChannelsViewHolder){
            final ChannelsViewHolder viewHolder = (ChannelsViewHolder)holder;
            final Channel channel = (Channel)list.get(position);
            String url;
            url = channel.getData().get(position).getLink();
            ImageLoader.load(fragment, url, viewHolder.channelsIconIv, false);
            viewHolder.channelsNameTv.setText(channel.getData().get(position).getName());
            viewHolder.channelsDescTv.setText(channel.getData().get(position).getDescription());
            viewHolder.channelsFollowersCountTv.setText(channel.getData().get(position).
                    getMetadata().getConnections().getUsers().getTotal());
            viewHolder.channelsVideosCountTv.setText(channel.getData().get(position).getMetadata()
                    .getConnections().getVideos().getTotal());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRecyclerFragmentListListener(viewHolder, channel);
                    }
                }
            });
        }else if(holder instanceof ChannelViewHolder){
            final ChannelViewHolder viewHolder = (ChannelViewHolder)holder;
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
        } else if (holder instanceof EmptyViewHolder) {
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
                 default:
                     break;
            }
            viewHolder.emptyIv.setImageResource(ivId);
            viewHolder.emptyTv.setText(context.getString(tvId));
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

    public class ChannelsViewHolder extends RecyclerView.ViewHolder {

        public final RelativeLayout channelsRl;
        public final ImageView channelsIconIv;
        public final TextView channelsNameTv;
        public final TextView channelsDescTv;
        public final TextView channelsVideosCountTv;
        public final TextView channelsFollowersCountTv;

        public ChannelsViewHolder(View view) {
            super(view);
            channelsRl = (RelativeLayout)view.findViewById(R.id.channels_rl);
            channelsIconIv = (ImageView)view.findViewById(R.id.channels_icon_iv);
            channelsNameTv = (TextView)view.findViewById(R.id.channels_name_tv);
            channelsDescTv = (TextView)view.findViewById(R.id.channels_desc_tv);
            channelsVideosCountTv = (TextView)view.findViewById(R.id.channels_videos_count_tv);
            channelsFollowersCountTv = (TextView)view.findViewById(R.id.
                    channels_followers_count_tv);
        }
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {

        public final ImageView videoThumbIv;
        public final TextView videoNameTv;
        public final ImageView avatarIv;
        public final TextView userNameTv;
        public final TextView videoPlayCountsTv;


        public ChannelViewHolder(View view) {
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
        public WatchedVideoViewHolder(View view) {
            super(view);
        }
    }

    private class LikedVideoViewHolder extends RecyclerView.ViewHolder {
        public LikedVideoViewHolder(View view) {
            super(view);
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
