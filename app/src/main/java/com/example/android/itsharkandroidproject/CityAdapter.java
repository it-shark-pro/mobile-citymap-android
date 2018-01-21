package com.example.android.itsharkandroidproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<CityModel> cities = new ArrayList<>();
    private ItemClickListener itemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_city_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CityModel cityModel = cities.get(position);
        holder.titleTextView.setText(cityModel.getTitle());
        holder.descriptionTextView.setText(cityModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void update(final List<CityModel> items) {
        this.cities.clear();
        this.cities.addAll(items);
        notifyDataSetChanged();
    }

    public CityModel getItemByPosition(final int position) {
        return cities.get(position);
    }

    public void setItemClickListener(final ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView titleTextView;
        final TextView descriptionTextView;

        ViewHolder(final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.text_view_city_title);
            descriptionTextView = itemView.findViewById(R.id.text_view_city_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            itemClickListener.onItemClick(view, getAdapterPosition(), CityAdapter.this);
        }
    }
}
