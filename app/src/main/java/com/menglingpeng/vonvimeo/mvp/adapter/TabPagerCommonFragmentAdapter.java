package com.menglingpeng.vonvimeo.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.menglingpeng.vonvimeo.mvp.view.CommonFragment;
import com.menglingpeng.vonvimeo.mvp.view.fragment.CommonFragment;

import java.util.List;

public class TabPagerCommonFragmentAdapter extends FragmentStatePagerAdapter{

    private List<CommonFragment> fragments;
    private List<String> titles;
    private static CommonFragment fragment;

    public TabPagerCommonFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<CommonFragment> fragments, List<String> titles) {
        this.fragments = fragments;
        this.titles = titles;
    }

    public void clearFragments() {
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        fragments.clear();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        fragment = (CommonFragment) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    public static CommonFragment getCurrentPagerViewFragment() {
        return fragment;
    }
}
