package com.example.itcentar_fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.holders.ReorderElementHolder;

import java.util.List;

public class ReorderElementsViewAdapter extends RecyclerView.Adapter<ReorderElementHolder> {
    List<HomeScreenParts> displayOrder;

    public ReorderElementsViewAdapter(List<HomeScreenParts> displayOrder) {
        this.displayOrder = displayOrder;
    }

    @NonNull
    @Override
    public ReorderElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_element_reorder_holder,parent,false);
        return new ReorderElementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReorderElementHolder holder, int position) {
            String text=displayOrder.get(position).getName();
            holder.setElementText(text);
    }

    @Override
    public int getItemCount() {
        return displayOrder.size();
    }
}
