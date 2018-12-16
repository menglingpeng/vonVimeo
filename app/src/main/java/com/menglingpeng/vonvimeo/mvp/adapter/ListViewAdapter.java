package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ListViewAdapter<T> extends BaseAdapter {

        //为了让子类访问，于是将属性设置为protected
        protected Context context;
        protected List<T> datas;
        protected LayoutInflater inflater;
        private int layoutId; //不同的ListView的item布局肯能不同，所以要把布局单独提取出来

        public ListViewAdapter(Context context, List<T> datas, int layoutId) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.datas = datas;
            this.layoutId = layoutId;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public T getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //初始化ViewHolder,使用通用的ViewHolder
            ViewHolder holder = ViewHolder.get(context, convertView, parent, layoutId, position);

            convert(holder, getItem(position));
            return holder.getConvertView();
        }

    //将convert方法公布出去
    public abstract void convert (ViewHolder holder, T t);

    public static class ViewHolder {

        private SparseArray<View> mViews;
        private int mPosition;
        private View mConvertView;

        public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
            this.mPosition = position;
            this.mViews = new SparseArray<View>();

            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);

            mConvertView.setTag(this);

        }

        public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            } else {
                ViewHolder holder = (ViewHolder) convertView.getTag();
                holder.mPosition = position;
                return holder;
            }
        }

        /*
        通过viewId获取控件
         */
        //使用的是泛型T,返回的是View的子类
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);

            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }

            return (T) view;
        }

        public View getConvertView() {
            return mConvertView;
        }

    }
}
