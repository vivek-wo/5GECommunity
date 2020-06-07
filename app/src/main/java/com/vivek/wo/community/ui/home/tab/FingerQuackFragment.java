package com.vivek.wo.community.ui.home.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.vivek.wo.community.R;
import com.vivek.wo.community.databinding.FragmentHomeFingerfixBinding;
import com.vivek.wo.community.ui.home.SharedViewModel;
import com.vivek.wo.community.ui.home.list.MainRecyclerFragment;

public class FingerQuackFragment extends Fragment {

    private FragmentHomeFingerfixBinding dataBinding;

    private SharedViewModel sharedViewModel;

    private final int[] DEFAULT_TITLE_TABS = new int[]{
            R.string.finger_quack_tab_d, R.string.finger_quack_tab_g};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = FragmentHomeFingerfixBinding.inflate(inflater, container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < DEFAULT_TITLE_TABS.length; i++) {
            TabLayout.Tab tab = dataBinding.tabLayout.newTab();
            tab.setText(DEFAULT_TITLE_TABS[i]);
            dataBinding.tabLayout.addTab(tab);
        }
        dataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tabMainRecyclerFragment();
                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabMainRecyclerFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.appBar.addOnOffsetChangedListener(mOnOffsetChangedListener);
    }

    private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener =
            new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                }
            };

    @Override
    public void onPause() {
        super.onPause();
        dataBinding.appBar.removeOnOffsetChangedListener(mOnOffsetChangedListener);
    }

    private void tabMainRecyclerFragment() {
        MainRecyclerFragment mainRecyclerFragment = null;
        mainRecyclerFragment = (MainRecyclerFragment) getChildFragmentManager().
                findFragmentByTag(MainRecyclerFragment.class.getSimpleName());
        if (mainRecyclerFragment == null) {
            mainRecyclerFragment = new MainRecyclerFragment();
        }
        getChildFragmentManager().beginTransaction().replace(R.id.framelayout_container,
                mainRecyclerFragment, MainRecyclerFragment.class.getSimpleName()).commit();
    }

}
