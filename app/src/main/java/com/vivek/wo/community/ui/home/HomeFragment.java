package com.vivek.wo.community.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;
import com.vivek.wo.community.R;
import com.vivek.wo.community.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding dataBinding;
    private HomeViewModel homeViewModel;

    private int[] defaultTabRes = new int[]{
            R.string.tab_home_1, R.string.tab_home_2,
            R.string.tab_home_3, R.string.tab_home_4,
            R.string.tab_home_5, R.string.tab_home_6};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < defaultTabRes.length; i++) {
            TabLayout.Tab tab = dataBinding.tabLayout.newTab();
            dataBinding.tabLayout.addTab(tab);
            tab.setText(defaultTabRes[i]);
        }
        dataBinding.tabLayout.setupWithViewPager(dataBinding.viewPager, false);

    }
}
