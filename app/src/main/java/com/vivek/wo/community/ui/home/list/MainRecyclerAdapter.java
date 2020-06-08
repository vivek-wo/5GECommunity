package com.vivek.wo.community.ui.home.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.wo.community.databinding.ItemLayoutMainBinding;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.DataViewHolder> {

    static class DataViewHolder extends RecyclerView.ViewHolder {
        private ItemLayoutMainBinding databinding;

        public ItemLayoutMainBinding getBinding() {
            return this.databinding;
        }

        public DataViewHolder(ItemLayoutMainBinding databinding) {
            super(databinding.getRoot());
            this.databinding = databinding;
        }

    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutMainBinding binding = ItemLayoutMainBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
