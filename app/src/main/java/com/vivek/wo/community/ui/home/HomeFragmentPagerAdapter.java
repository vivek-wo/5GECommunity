package com.vivek.wo.community.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vivek.wo.community.ui.home.tab.FollowFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private int[] defaultTabTitles;

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior,
                                    int[] defaultTabTitles) {
        super(fm, behavior);
        this.defaultTabTitles = defaultTabTitles;
    }

    @Override
    public int getCount() {
        return this.defaultTabTitles.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new FollowFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
