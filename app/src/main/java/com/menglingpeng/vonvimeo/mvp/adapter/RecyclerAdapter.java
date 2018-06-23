package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {

    public static final int TYPE_ITEM = 0;
    private static final int TYPE_ITEM_FOOTER = 1;
    private static final int TYPE_ITEM_EMPTY = 2;
    private ArrayList list = new ArrayList<>();
    private Context context;
    private String type;
    private boolean isLoading;

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
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }
    }

    public <T> void addData(T d) {
        list.add(d);
        notifyDataSetChanged();
    }
}
