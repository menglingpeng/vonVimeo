package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> lists;
    private List<List<String>> childLists;

    public ExpandableListAdapter(Context context , List<String> lists, List<List<String>> childLists ){
        this.context = context;
        this.lists = lists;
        this.childLists = childLists;
    }

    @Override
    public int getGroupCount() {
        return lists.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childLists.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return i;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.expandable_listview_you_need_base_item, null);
        TextView titleTv;
        ImageView addIv;
        titleTv = (TextView)view.findViewById(R.id.you_need_title_tv);
        addIv = (ImageView)view.findViewById(R.id.you_need_title_iv);
        titleTv.setText(lists.get(i));
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.expandable_listview_you_need_base_item, null);
        TextView descTv;
        descTv = (TextView)view.findViewById(R.id.you_need_desc_tv);
        descTv.setText(childLists.get(i).get(i1));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
