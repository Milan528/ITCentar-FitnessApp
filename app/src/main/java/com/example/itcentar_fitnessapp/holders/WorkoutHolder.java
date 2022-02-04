package com.example.itcentar_fitnessapp.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.adapters.WorkoutEquipmentViewAdapter;
import com.example.itcentar_fitnessapp.models.Recipe;
import com.example.itcentar_fitnessapp.models.Workout;
import com.makeramen.roundedimageview.RoundedImageView;

public class WorkoutHolder extends RecyclerView.ViewHolder {
    RoundedImageView imageToDisplay;
    RecyclerView workoutEquipmentRecyclerView;
    WorkoutEquipmentViewAdapter workoutEquipmentViewAdapter;
    TextView holderTittle,tittle;
    Workout workout;
    Context context;
    View view;
    public WorkoutHolder(@NonNull View itemView, Workout workoutToBind, Context context) {
        super(itemView);
        view=itemView;
        this.tittle=itemView.findViewById(R.id.text_view_recipe_mindset_tittle);
        this.imageToDisplay=itemView.findViewById(R.id.image_view_recipe_mindset);
        this.holderTittle=itemView.findViewById(R.id.text_view_recipe_mindset);
        this.workoutEquipmentRecyclerView=itemView.findViewById(R.id.recycler_view_workout_equipment);
        this.context=context;
        this.workout=workoutToBind;
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holderTittle.getLayoutParams();
        params.dimensionRatio="0:0";
        holderTittle.setLayoutParams(params);
        tittle.setText(workout.getTitle());
        Glide.with(context)
                .load(workout.getBackground())
                .into(imageToDisplay);
        if(workout.getEquipment()!=null)
             createEquipmentAdapter();

    }

    private void createEquipmentAdapter() {

        workoutEquipmentViewAdapter=new WorkoutEquipmentViewAdapter(workout);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        workoutEquipmentRecyclerView.setLayoutManager(layoutManager);
        workoutEquipmentRecyclerView.setAdapter(workoutEquipmentViewAdapter);
    }
}
