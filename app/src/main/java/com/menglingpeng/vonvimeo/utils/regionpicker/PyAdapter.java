package com.menglingpeng.vonvimeo.utils.regionpicker;

import android.content.Entity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.WeakHashMap;

public abstract class PyAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>
        implements View.OnClickListener {

    private static final String TAG = PyAdapter.class.getSimpleName();
    public static final int TYPE_LETTER = 0;
    public static final int TYPE_OTHER = 1;
    private WeakHashMap<View, VH> holders = new WeakHashMap<>();
    public final ArrayList<PinYinEntity> entityList = new ArrayList<>();
    public final  HashSet<LetterEntity> letterSet = new HashSet<>();
    private OnItemClickListener listener = (entity, position) -> {

    };

    public PyAdapter(List<? extends PinYinEntity> entities) {
        if (entities == null) throw new NullPointerException("entities == null!");
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        PinYinEntity entity = entityList.get(position);
        holders.put(holder.itemView, holder);
        holder.itemView.setOnClickListener(this);
        if(entity instanceof LetterEntity) {
            onBindLetterHolder(holder, (LetterEntity)entity, position);
        } else {
            onBindHolder(holder, entity, position);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        PinYinEntity entity = entityList.get(position);
        return entity instanceof LetterEntity? TYPE_LETTER : getViewType(entity, position);
    }

    @Override
    public int getItemCount() {
        return entityList.size();;
    }

    public int getViewType(PinYinEntity entity, int position) {
        return TYPE_OTHER;
    }

    public void onBindLetterHolder(VH holder, LetterEntity entity, int position) {

    }

    public void onBindHolder(VH holder, PinYinEntity entity, int position) {

    }

    @Override
    public final void onClick(View v) {
        VH holder = holders.get(v);
        if(holder == null) {
            return;
        }
        int position = holder.getAdapterPosition();
        PinYinEntity pyEntity = entityList.get(position);
        listener.onItemClick(pyEntity, position);
    }


    public void update(List<? extends PinYinEntity> entities){
        if(entities == null) throw new NullPointerException("entities == null!");
        entityList.clear();
        entityList.addAll(entities);
        letterSet.clear();
        for (    public void update(List<? extends PinYinEntity> entities){
            Entity entity : entities) {
            String pinyin = entity.getPinyin();
            if(!TextUtils.isEmpty(pinyin)) {
                char letter = pinyin.charAt(0);
                if(!isLetter(letter))
                    letter = 35;
                letterSet.add(new LetterEntity(letter + ""));
            }
        }
        entityList.addAll(letterSet);
        Collections.sort(entityList, (o1, o2) -> {
            String pinyin = o1.getPinyin().toLowerCase();
            String anotherPinyin = o2.getPinyin().toLowerCase();
            char letter = pinyin.charAt(0);
            char otherLetter = anotherPinyin.charAt(0);
            if(isLetter(letter) && isLetter(otherLetter))
                return pinyin.compareTo(anotherPinyin);
            else if(isLetter(letter) && !isLetter(otherLetter)) {
                return -1;
            } else if(!isLetter(letter) && isLetter(otherLetter)){
                return 1;
            } else {
                if(letter == 35 && o1 instanceof LetterEntity) return -1;
                else if(otherLetter == 35 && o2 instanceof LetterEntity) return 1;
                else return pinyin.compareTo(anotherPinyin);
            }
        });
        notifyDataSetChanged();
    }
    }

    private boolean isLetter(char letter) {
        return 'a' <= letter && 'z' >= letter || 'A' <= letter && 'Z' >= letter;
    }

        public boolean isLetter(int position) {
            if(position < 0 || position >= entityList.size()) return false;
            else return entityList.get(position) instanceof LetterEntity;
        }

        public interface OnItemClickListener {
            void onItemClick(PinYinEntity entity, int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    public class LetterHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public LetterHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
}
