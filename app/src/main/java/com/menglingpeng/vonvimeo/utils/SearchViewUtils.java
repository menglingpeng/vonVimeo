package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchViewUtils {

    public static void initSearchView(final MenuItem item){

        //通过 item 获取 actionview
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("搜索");

        //改变默认的搜索图标
        ((ImageView)searchView.findViewById(R.id.search_button)).setImageResource(R.drawable.ic_search);

        //搜索监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //在输入法按下搜索或者回车时，会调用次方法，在这里可以作保存历史记录的操作，我这里用了 sharepreference保存

                SPUtils spUtils = new SPUtils("knowledgeHistory");
                spUtils.put(query, query);
                presenter.searchKnowledge(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //输入字符则回调此方法
                //当输入字符为空时，重新设置 item

                if(newText==null||newText.length()==0){

                    //由于实现了历史数据的功能，在此重新设置此 item才能实时生效
                    initSearchView(item); }

                return false;
            }
        });
        //根据id-search_src_text获取TextView
        searchViewOfKnowledge = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        //改变输入文字的颜色
        searchViewOfKnowledge.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        try {

            //取出历史数据
            final List<String> arr = new ArrayList<>();

            Map<String, String> map = new HashMap<>();
            map = SharedPrefUtils.getSearchHistory((HashMap<String, String>) map);

            for (String key : map.keySet()) {
                arr.add(map.get(key).toString());
            }

            //显示历史数据列表
            searchViewOfKnowledge.setThreshold(0);

            //历史数据列表的 adapter,必须继承 ArrayAdater 或实现 filterable接口
            HistoryAdapter adapter = new HistoryAdapter(this, R.layout.item_history, arr,searchView);

            //设置 adapter
            searchViewOfKnowledge.setAdapter(adapter);

            //如果重写了 Adapter 的 getView 方法，可以不用实现 item 监听（实现了也没用），否则必须实现监听，不然会报错
            searchViewOfKnowledge.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    searchView.setQuery(arr.get(position), true);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //searchview 的关闭监听
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                return false;
            }
        });
    }

    public class HistoryAdapter extends ArrayAdapter {

        private Context context;
        private List<String> titles;
        private int resource;
        private android.widget.SearchView searchView;
        private List<String> originalValues;


        public HistoryAdapter(@NonNull Context context, int resource, List<String> titles, android.widget.SearchView searchView) {
            super(context, resource, objects);
            this.context=context;
            this.titles=titles;
            this.resource=resource;
            this.searchView=searchView;
        }

        @NonNull
        @Override
        public Filter getFilter() {

            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    FilterResults results = new FilterResults();
                    List<String> filteredArrList = new ArrayList<String>();
                    if (originalValues == null) {

                        //保存一份未筛选前的完整数据
                        originalValues = new ArrayList<String>(titles);
                    }
                    if (constraint == null || constraint.length() == 0) { //如果接收到的文字为空，则不作比较，直接返回未筛选前的完整数据
                        results.count = originalValues.size();
                        results.values = originalValues;
                    } else {
                        //遍历原始数据，与接收到的文字作比较，得到筛选结果
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < originalValues.size(); i++) {
                            String data = originalValues.get(i);
                            if(data.toLowerCase().startsWith(constraint.toString())) {
                                filteredArrList.add(data);
                            }
                        }
                        //返回得到的筛选列表
                        results.count = filteredArrList.size();
                        results.values = filteredArrList;
                    }
                    return results;
                };

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    titles = (List<String>) filterResults.values; // 得到筛选后的列表结果
                    notifyDataSetChanged();  // 刷新数据        }

                }
            };
            return filter;



        }


    }
}
