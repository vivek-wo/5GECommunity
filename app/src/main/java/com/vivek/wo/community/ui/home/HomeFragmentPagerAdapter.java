package com.vivek.wo.community.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vivek.wo.community.ui.home.tab.FingerQuackFragment;
import com.vivek.wo.community.ui.home.tab.FingerTipFragment;
import com.vivek.wo.community.ui.home.tab.FollowFragment;
import com.vivek.wo.community.ui.home.tab.RecommendFragment;
import com.vivek.wo.community.ui.home.tab.RingFragment;
import com.vivek.wo.community.ui.home.tab.SwordTFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>(6);

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragmentList.add(new FollowFragment());
        mFragmentList.add(new RecommendFragment());
        mFragmentList.add(new SwordTFragment());
        mFragmentList.add(new FingerQuackFragment());
        mFragmentList.add(new FingerTipFragment());
        mFragmentList.add(new RingFragment());
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

}
