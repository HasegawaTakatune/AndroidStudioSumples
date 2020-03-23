package com.example.tabselectersample;

import android.os.Bundle;
import android.widget.HorizontalScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private HorizontalScrollView trackScroller;
    private TabLayout tabLayout;

    private int scrollingState = ViewPager.SCROLL_STATE_IDLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackScroller = findViewById(R.id.scroller);
        tabLayout = findViewById(R.id.tabLayout);

        OriginalFragmentPagerAdapter adapter = new OriginalFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new PageChangeListener(tabLayout, trackScroller));

        tabLayout.setupWithViewPager(viewPager);
    }
}
