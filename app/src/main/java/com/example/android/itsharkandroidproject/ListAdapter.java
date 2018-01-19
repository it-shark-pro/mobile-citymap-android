package com.example.android.itsharkandroidproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListItemModel> items = new ArrayList<>();
    private ItemClickListener itemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ListItemModel itemModel = items.get(position);
        holder.nameTextView.setText(itemModel.getName());
        holder.descriptionTextView.setText(itemModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(final List<ListItemModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public ListItemModel getItemByPostion(final int position) {
        return items.get(position);
    }

    public void setItemClickListener(final ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nameTextView;
        final TextView descriptionTextView;

        ViewHolder(final View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.text_view_list_item_title);
            descriptionTextView = itemView.findViewById(R.id.text_view_list_item_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            itemClickListener.onItemClick(view, getAdapterPosition(), ListAdapter.this);
        }
    }
}
