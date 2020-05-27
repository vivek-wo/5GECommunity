package com.vivek.wo.community.ui.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class HomeFragmentPagerAdapter extends PagerAdapter {

    public HomeFragmentPagerAdapter() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
