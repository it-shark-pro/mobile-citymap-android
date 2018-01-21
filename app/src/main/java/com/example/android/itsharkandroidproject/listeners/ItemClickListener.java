package com.example.android.itsharkandroidproject.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface ItemClickListener {
    void onItemClick(View view, int position, RecyclerView.Adapter adapter);
}
