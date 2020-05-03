package com.test.buderdn14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private int DEFAULT_COUNT = 20;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return DEFAULT_COUNT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvHorizontalRecyclerView;
        LinearLayoutManager layoutManager;

        private ViewHolder(View itemView) {
            super(itemView);
            rvHorizontalRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_horizontal);
            layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rvHorizontalRecyclerView.setLayoutManager(layoutManager);
            rvHorizontalRecyclerView.setAdapter(new FakeAdapter(R.layout.item_card_hor));
        }

        private void bind() {
            layoutManager.scrollToPosition(0);
        }
    }
}
