package com.example.tabselectersample;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OriginalFragmentPagerAdapter extends FragmentPagerAdapter {

    private CharSequence[] tabTitles = {"タブ１", "タブ２", "タブ３", "タブ４", "タブ５", "タブ６", "タブ７", "タブ８", "タブ９", "タブ１０123456"};

    public OriginalFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Main1Fragment();
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return new Main2Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
