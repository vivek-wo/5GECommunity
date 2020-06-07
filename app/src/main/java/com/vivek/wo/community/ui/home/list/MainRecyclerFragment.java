package com.vivek.wo.community.ui.home.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.wo.community.databinding.FragmentRecyclerviewBinding;

public class MainRecyclerFragment extends Fragment {

    private FragmentRecyclerviewBinding databinding;
    private MainRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = FragmentRecyclerviewBinding.inflate(inflater, container, false);
        return databinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        databinding.recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        databinding.recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new MainRecyclerAdapter();
        }
        databinding.recyclerView.setAdapter(adapter);
    }

}
