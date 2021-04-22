package com.vivek.wo.community.ui.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.wo.community.R;
import com.vivek.wo.community.databinding.SampleActivityNestedBinding;
import com.vivek.wo.community.ui.home.list.MainRecyclerAdapter;

public class NestedScrollSampleActivity extends AppCompatActivity {
    private SampleActivityNestedBinding binding;
    private MainRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.sample_activity_nested);
        initRecyclerView();
        initRecyclerView0();
    }

    private void initRecyclerView0() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView0.addItemDecoration(
                new DividerItemDecoration(this, RecyclerView.VERTICAL));
        binding.recyclerView0.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new MainRecyclerAdapter();
        }
        binding.recyclerView0.setAdapter(adapter);
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, RecyclerView.VERTICAL));
        binding.recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new MainRecyclerAdapter();
        }
        binding.recyclerView.setAdapter(adapter);
    }
}
