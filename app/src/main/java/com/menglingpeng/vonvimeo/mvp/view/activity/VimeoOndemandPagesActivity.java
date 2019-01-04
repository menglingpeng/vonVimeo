package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.menglingpeng.vonvimeo.mvp.adapter.HistoryAdapter;
import com.menglingpeng.vonvimeo.mvp.interf.RecyclerView;
import com.menglingpeng.vonvimeo.mvp.view.RecyclerFragment;
import com.menglingpeng.vonvimeo.utils.Constants;
import com.menglingpeng.vonvimeo.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VimeoOndemandPagesActivity extends BaseActivity implements RecyclerView{

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private String title;
    private TextView demandDescTv;
    private ProgressBar progressBar;
    private String sortType;
    private MenuItem menuItem;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_vimeo_ondemand_pages;
    }

    @Override
    protected void initViews() {
        super.initViews();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.vimeo_demand_pages_cdl);
        toolbar = (Toolbar) findViewById(R.id.vimeo_demand_pages_tb);
        demandDescTv = (TextView)findViewById(R.id.vimeo_demand_pages_desc_tv);
        progressBar = (ProgressBar)findViewById(R.id.vimeo_demand_pages_pb);
        menuItem = toolbar.findViewById(R.id.vimeo_on_demand_pages_search);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initSearchView(menuItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.vimeo_on_demand_pages_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.vimeo_on_demand_pages_sort_date:
                sortType = Constants.TYPE_DATE;
                break;
            case R.id.vimeo_on_demand_pages_sort_alphabetical:
                sortType = Constants.TYPE_ALPHABETICAL;
                break;
            case R.id.vimeo_on_demand_pages_sort_videos:
                sortType = Constants.TYPE_VIDEOS;
                break;
            case R.id.vimeo_on_demand_pages_sort_comments:
                sortType = Constants.TYPE_COMMENTS;
                break;
            case R.id.vimeo_on_demand_pages_thumb:
                break;
            case R.id.vimeo_on_demand_pages_detail:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initSearchView(final MenuItem item){

        //通过 item 获取 actionview
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("搜索知识库");

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
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {        @Override        public boolean onClose() {
            return false;
        }
        });}

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void loadSuccess(String json, String requestType) {

    }
}
