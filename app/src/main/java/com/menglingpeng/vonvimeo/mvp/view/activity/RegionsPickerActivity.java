package com.menglingpeng.vonvimeo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.menglingpeng.vonvimeo.base.BaseActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegionsPickerActivity extends BaseActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private Context context;
    private SearchView searchView;
    private RecyclerView searchRv;
    private SearchView.SearchAutoComplete searchAutoComplete;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_regions_picker;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.regions_picker_tb);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.regions_picker_cdl);
        toolbar.setTitle(R.string.regions);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(Color.WHITE);
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
        getMenuInflater().inflate(R.regions_picker_toolbar_overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() = R.id.genres_picker_search) {

        }
        return super.onOptionsItemSelected(item);
    }
}

