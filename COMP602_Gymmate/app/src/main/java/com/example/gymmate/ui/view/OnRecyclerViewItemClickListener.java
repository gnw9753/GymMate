package com.example.gymmate.ui.view;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnRecyclerViewItemClickListener {
    void onItemClick(RecyclerView parent, View view, int position);
    void onItemLongClick(RecyclerView parent, View view, int position);
}
