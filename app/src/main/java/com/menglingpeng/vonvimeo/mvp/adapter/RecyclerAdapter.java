package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
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
import com.menglingpeng.vonvimeo.mvp.model.Project;
import com.menglingpeng.vonvimeo.utils.Constants;
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
                    case Constants.LIST_USER_FEED_VIDEOS:
                        view = inflater.inflate(R.layout.recycler_item, parent, false);
                        viewHolder = new FeedVideoViewHolder(view);
                        break;
                    case Constants.LIST_USER_ALBUMS:
                        view = inflater.inflate(R.layout.user_albums_recycler_item, parent, false);
                        viewHolder = new AlbumViewHolder(view);
                        break;
                    case Constants.LIST_USER_PROJECTS:
                        view = inflater.inflate(R.layout.user_projects_recycler_item, parent, false);
                        viewHolder = new ProjectViewHolder(view);
                        break;
                    case Constants.LIST_CATEGORITES:
                        view = inflater.inflate(R.layout.categorites_recycler_item, parent, false);
                        viewHolder = new CateGoritesViewHolder(view);
                        break;
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
        }else if (holder instanceof EmptyViewHolder){
            final EmptyViewHolder viewHolder = (EmptyViewHolder)holder;
        }else if ((holder instanceof AlbumViewHolder)){
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
