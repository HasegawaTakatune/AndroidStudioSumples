package com.example.tabselectersample;

import android.widget.HorizontalScrollView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class PageChangeListener implements ViewPager.OnPageChangeListener {

    private HorizontalScrollView trackScroller;
    private TabLayout tabLayout;

    PageChangeListener(TabLayout tabLayout, HorizontalScrollView trackScroller) {
        this.tabLayout = tabLayout;
        this.trackScroller = trackScroller;
    }

    @Override
    public void onPageSelected(int position) {
        trackScroller.scrollTo((tabLayout.getWidth() / tabLayout.getTabCount()) * position, 0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
}