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

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.vivek.wo.community.R;
import com.vivek.wo.community.databinding.FragmentHomeBinding;
import com.vivek.wo.community.ui.view.TouchSlopViewPager;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding dataBinding;
    private HomeViewModel homeViewModel;
    private SharedViewModel sharedViewModel;

    private final int[] DEFAULT_TITLE_TABS = new int[]{
            R.string.tab_home_1, R.string.tab_home_2,
            R.string.tab_home_3, R.string.tab_home_4,
            R.string.tab_home_5, R.string.tab_home_6};


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
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
        for (int i = 0; i < DEFAULT_TITLE_TABS.length; i++) {
            TabLayout.Tab tab = dataBinding.tabLayout.getTabAt(i);
            tab.setCustomView(R.layout.tab_home);
            //去除点击效果
            tab.view.setBackgroundColor(Color.TRANSPARENT);
            TextView textView = tab.getCustomView().findViewById(R.id.tab_title);
            textView.setText(DEFAULT_TITLE_TABS[i]);
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
        dataBinding.viewPager.setOnTouchSlopListener(
                new TouchSlopViewPager.OnTouchSlopListener() {
                    @Override
                    public void onTouchSlop() {
                        dataBinding.appBar.setExpanded(false, true);
                    }
                });
        dataBinding.viewPager.setCurrentItem(3);
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.appBar.addOnOffsetChangedListener(mOnOffsetChangedListener);
    }

    private void onOffsetLinked(int verticalOffset, int maxOffset) {
        float alpha = (verticalOffset + maxOffset) / (float) maxOffset;
        dataBinding.searchEdit.setAlpha(alpha);
        dataBinding.searchEditIcon.setAlpha(alpha);
        dataBinding.searchButton.setAlpha(1 - alpha);

        float percent = 0.8f + 0.2f * alpha;
        dataBinding.tabLayout.setScaleX(percent);
        dataBinding.tabLayout.setScaleY(percent);
    }

    private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener =
            new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    onOffsetLinked(verticalOffset, appBarLayout.getTotalScrollRange());
                }
            };

    @Override
    public void onPause() {
        super.onPause();
        dataBinding.appBar.removeOnOffsetChangedListener(mOnOffsetChangedListener);
    }
}
