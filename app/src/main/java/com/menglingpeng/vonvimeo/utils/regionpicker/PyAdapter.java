package com.menglingpeng.vonvimeo.utils.regionpicker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.WeakHashMap;

public abstract class PyAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {

    private static final String TAG = PyAdapter.class.getSimpleName();
    public static final int TYPE_LETTER = 0;
    public static final int TYPE_OTHER = 1;
    private WeakHashMap<View, VH> holders = new WeakHashMap<>();
    public final ArrayList<PinYinEntity> entityList = new ArrayList<>();
    private AdapterView.OnItemClickListener listener = (entity, position) -> {

    };

    public PyAdapter(List<? extends PinYinEntity> entities) {
        if (entities == null) throw new NullPointerException("entities == null!");
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
