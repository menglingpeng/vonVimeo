package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder> {
    private Context mContext;
    private List<String> lists;
    private HashMap<Integer, Boolean> Maps = new HashMap<Integer, Boolean>();
    private HashMap<Integer, Boolean> AllMaps = new HashMap<Integer, Boolean>();
    public RecyclerViewOnItemClickListener onItemClickListener;

    //成员方法，初始化checkBox的状态，默认全部不选中
    public CheckAdapter(Context context, List<String> lists) {
        this.mContext = context;
        this.lists = lists;
        initMap();
    }

    //初始化map内的数据状态，全部重置为false，即为选取状态
    private void initMap() {
        for (int i = 0; i < lists.size(); i++) {
            Maps.put(i, false);
        }
    }


    //获取最终的map存储数据
    public Map<Integer, Boolean> getMap() {
        return Maps;
    }

    //后续扩展 - 获取最终的map存储数据
    public Map<Integer, Boolean> getAllMap() {
        return AllMaps;
    }

    //点击item选中CheckBox
    public void setSelectItem(int position) {
        //对当前状态取反
        if (Maps.get(position)) {
            Maps.put(position, false);
        } else {
            Maps.put(position, true);
        }
        notifyItemChanged(position);
    }

    @Override
    public CheckAdapter.CheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckViewHolder checkViewHolder = new CheckViewHolder(LayoutInflater.from(MainActivity.this).
                inflate(R.layout.item_layout, parent, false), onItemClickListener);
        return checkViewHolder;
    }

    @Override
    public void onBindViewHolder(CheckAdapter.CheckViewHolder holder, final int position) {


        holder.mName.setText(lists.get(position));

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Maps.put(position, isChecked);
            }
        });

        if (Maps.get(position) == null) {
            Maps.put(position, false);
        }
        //没有设置tag之前会有item重复选框出现，设置tag之后，此问题解决
        holder.mCheckBox.setChecked(Maps.get(position));


        //之后扩展使用
        AllMaps.put(position, true);
    }


    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    public void setItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class CheckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewOnItemClickListener mListener;
        private TextView mName;
        private CheckBox mCheckBox;

        public CheckViewHolder(View itemView, RecyclerViewOnItemClickListener onItemClickListener) {
            super(itemView);
            this.mListener = onItemClickListener;
            itemView.setOnClickListener(this);
            mName = (TextView) itemView.findViewById(R.id.item_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.item_cb);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClickListener(v, getPosition());
            }
        }
    }

    //接口回调设置点击事件
    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(View view, int position);
    }

}

