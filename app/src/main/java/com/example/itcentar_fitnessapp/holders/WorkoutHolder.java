package com.example.itcentar_fitnessapp.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.adapters.WorkoutEquipmentViewAdapter;
import com.example.itcentar_fitnessapp.models.MyAppData;
import com.example.itcentar_fitnessapp.models.Workout;
import com.makeramen.roundedimageview.RoundedImageView;

public class WorkoutHolder extends RecyclerView.ViewHolder {
    RoundedImageView imageToDisplay;
    RecyclerView workoutEquipmentRecyclerView;
    WorkoutEquipmentViewAdapter workoutEquipmentViewAdapter;
    TextView holderTittle,tittle;
    Workout workout;
    Context context;
    public WorkoutHolder(@NonNull View itemView, Workout workoutToBind, Context context) {
        super(itemView);
        this.tittle=itemView.findViewById(R.id.text_view_recipe_mindset_workout_tittle);
        this.imageToDisplay=itemView.findViewById(R.id.image_view_recipe_mindset_workout);
        this.holderTittle=itemView.findViewById(R.id.text_view_recipe_mindset_workout);
        this.workoutEquipmentRecyclerView=itemView.findViewById(R.id.recycler_view_workout_equipment);
        this.context=context;
        this.workout=workoutToBind;

        tittle.setText(workout.getTitle());
        Glide.with(context)
                .load(workout.getBackground())
                .into(imageToDisplay);
        if(workout.getEquipment()!=null)
             createEquipmentAdapter();
    }

    private void createEquipmentAdapter() {
        workoutEquipmentViewAdapter=new WorkoutEquipmentViewAdapter(workout);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, MyAppData.WORKOUT_EQUIPMENT_ADAPTER_SIZE);
        workoutEquipmentRecyclerView.setLayoutManager(layoutManager);
        workoutEquipmentRecyclerView.setAdapter(workoutEquipmentViewAdapter);
    }

    public void setHolderTittleVisibility(int visibility){
        holderTittle.setVisibility(visibility);
    }
    public void setWorkoutTittleVisibility(int visibility){
        tittle.setVisibility(visibility);
    }

}
