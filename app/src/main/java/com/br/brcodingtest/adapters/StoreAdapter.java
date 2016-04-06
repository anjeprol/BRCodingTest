package com.br.brcodingtest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.brcodingtest.R;
import com.br.brcodingtest.models.Store;
import com.br.brcodingtest.viewholders.StoreViewHolder;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<Store> mItems;

    public StoreAdapter(Context context, List<Store> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.row_linear, viewGroup, false);
        return new StoreViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((StoreViewHolder) viewHolder).invalidate(mItems.get(i));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addAll(List<Store> items) {
        mItems = items;
    }

    public List<Store> getItems() {
        return mItems;
    }
}