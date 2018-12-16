package com.menglingpeng.vonvimeo.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.menglingpeng.vonvimeo.mvp.model.CardPay;

import java.util.List;

public class OtherPayAdapter extends ListViewAdapter {

    private LayoutInflater inflater;
    private List<CardPay> cardPays;

    public OtherPayAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ListViewAdapter.ViewHolder holder, Object o) {

    }

    @Override
    public int getCount() {
        return cardPays.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = inflater.inflate(R.layout.listview_choose_other_pay, parent, false);
            viewHolder = new ViewHolder();
        }
        return null;
    }

    private class ViewHolder{
        private String pictureUrl;
        private String cardName;
        private String dailyLimit;
        private Boolean checked;
    }
}
