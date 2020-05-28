package com.vivek.wo.community.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.vivek.wo.community.R;
import com.vivek.wo.community.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding dataBinding;
    private HomeViewModel homeViewModel;

    private int[] defaultTabTitleRes = new int[]{
            R.string.tab_home_1, R.string.tab_home_2,
            R.string.tab_home_3, R.string.tab_home_4,
            R.string.tab_home_5, R.string.tab_home_6};


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTab();
    }

    private void initTab() {
        dataBinding.viewPager.setAdapter(new HomeFragmentPagerAdapter(getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        dataBinding.tabLayout.setupWithViewPager(dataBinding.viewPager, false);
        for (int i = 0; i < defaultTabTitleRes.length; i++) {
            TabLayout.Tab tab = dataBinding.tabLayout.getTabAt(i);
            tab.setCustomView(R.layout.tab_home);
            //去除点击效果
            tab.view.setBackgroundColor(Color.TRANSPARENT);
            TextView textView = tab.getCustomView().findViewById(R.id.tab_title);
            textView.setText(defaultTabTitleRes[i]);
        }
        dataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setScaleX(1.2f);
                tab.getCustomView().setScaleY(1.2f);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setScaleX(1.0f);
                tab.getCustomView().setScaleY(1.0f);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        dataBinding.viewPager.setCurrentItem(2);
    }
}
