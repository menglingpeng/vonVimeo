package com.menglingpeng.vonvimeo.mvp.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.menglingpeng.vonvimeo.utils.Cheeses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static android.support.v4.view.MenuItemCompat.*;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;
    private ListView searchLv;
    private SearchView.SearchAutoComplete searchAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar)findViewById(R.id.search_tb);
        searchLv = (ListView) findViewById(R.id.search_lv);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchAutoComplete.isShown()) {
                    searchAutoComplete.setText("");
                    try {
                        Method method = searchView.getClass().getDeclaredMethod("onClosedClicked");
                        method.setAccessible(true);
                        method.invoke(searchView)
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar_overflow_menu, menu);
        MenuItem searchMenu = menu.findItem(R.id.search_menu);
        searchView =  (SearchView) MenuItemCompat.getActionView(searchMenu);
        searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
        searchView.setQueryHint("Search videos, peopel, and more");

        //设置输入框提示文字样式
        searchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_light));
        searchAutoComplete.setTextSize(14);
        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        searchAutoComplete.setThreshold(1);

        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        searchView.onActionViewExpanded();
        searchView.setIconified(true);

        //修改搜索框控件间的间隔（）
        LinearLayout search_edit_frame = (LinearLayout) searchView.findViewById(R.id.search_edit_frame);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) search_edit_frame.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        search_edit_frame.setLayoutParams(params);

        //监听SearchView的内容
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                Cursor cursor = TextUtils.isEmpty(s) ? null : queryData(s);

                setAdapter(cursor);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private Cursor queryData(String key) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "video.db", null);
        Cursor cursor = null;
        try {
            String querySql = "select * from tb_video where name like '%" + key + "%'";
            cursor = db.rawQuery(querySql, null);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        } catch (Exception e) {
            e.printStackTrace();
            String createSql = "create table tb_video (_id integer primary key autoincrement,name varchar(100))";
            db.execSQL(createSql);

            String insertSql = "insert into tb_video values (null,?)";
            for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
                db.execSQL(insertSql, new String[]{Cheeses.sCheeseStrings[i]});
            }

            String querySql = "select * from tb_video where name like '%" + key + "%'";
            cursor = db.rawQuery(querySql, null);

            Log.e("CSDN_LQR", "createSql = " + createSql);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        }
        return cursor;
    }

    private void setAdapter(Cursor cursor) {
        if (searchLv.getAdapter() == null) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(SearchActivity.this,
                    R.layout.item_layout, cursor, new String[]{"name"}, new int[]{R.id.text1});
            searchLv.setAdapter(adapter);
        } else {
            ((SimpleCursorAdapter) searchLv.getAdapter()).changeCursor(cursor);
        }
    }
}
