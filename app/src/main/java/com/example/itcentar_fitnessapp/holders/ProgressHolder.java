package com.example.itcentar_fitnessapp.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;

public class ProgressHolder extends RecyclerView.ViewHolder {
    TextView progressTittle,completedProgress;
    public ProgressHolder(@NonNull View itemView,String tittle,String text) {
        super(itemView);
        progressTittle=itemView.findViewById(R.id.text_view_progress_title);
        completedProgress=itemView.findViewById(R.id.text_view_completed_progress);
        progressTittle.setText(tittle);
        completedProgress.setText(text);
    }
}
