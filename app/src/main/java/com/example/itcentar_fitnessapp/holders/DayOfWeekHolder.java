package com.example.itcentar_fitnessapp.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.models.DailyEvent;

public class DayOfWeekHolder extends RecyclerView.ViewHolder{
    private TextView startingLetter;
    private DailyEvent dailyEvent;

    public DayOfWeekHolder(@NonNull View itemView) {
        super(itemView);
    }
}
