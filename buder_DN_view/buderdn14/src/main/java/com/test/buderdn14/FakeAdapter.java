package com.test.buderdn14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class FakeAdapter extends RecyclerView.Adapter<FakeAdapter.ViewHolder> {

    private int DEFAULT_COUNT = 20;
    private int count = DEFAULT_COUNT;

    private int mLayoutRes;

    public FakeAdapter(int layoutRes) {
        mLayoutRes = layoutRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutRes, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.view.setText("Text :" + position);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView view;

        private ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.item_view_holder_text);
        }
    }
}
