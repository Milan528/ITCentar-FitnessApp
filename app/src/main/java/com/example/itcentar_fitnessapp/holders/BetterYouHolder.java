package com.example.itcentar_fitnessapp.holders;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;

public class BetterYouHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ConstraintLayout layoutBetterYou;
    Context context;


    public BetterYouHolder(@NonNull View itemView,Context context) {
        super(itemView);
        layoutBetterYou=itemView.findViewById(R.id.layout_better_you);
        layoutBetterYou.setOnClickListener(this);
        this.context=context;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context,"Better you clicked",Toast.LENGTH_SHORT).show();
    }
}
