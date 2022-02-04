package com.example.itcentar_fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.enums.WorkoutEquipment;
import com.example.itcentar_fitnessapp.holders.Placeholder;
import com.example.itcentar_fitnessapp.holders.WorkoutEquipmentHolder;
import com.example.itcentar_fitnessapp.models.Workout;

public class WorkoutEquipmentViewAdapter extends RecyclerView.Adapter<WorkoutEquipmentHolder> {
    Workout workout;

    public WorkoutEquipmentViewAdapter(Workout workout) {
        this.workout = workout;
    }

    @NonNull
    @Override
    public WorkoutEquipmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_workout_equipment_holder,parent,false);
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        WorkoutEquipmentHolder workoutEquipmentHolder=new WorkoutEquipmentHolder(view);
        int parentWidth=parent.getMeasuredWidth();
        layoutParams.height=(int)(parentWidth/3/2);
        return workoutEquipmentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutEquipmentHolder holder, int position) {
        if(position==0){
            holder.setItem(null);
            holder.createWorkoutTime(workout.getTime()+" min");
        }else{
            holder.setItem(workout.getEquipment().get(position-1));
            holder.createWorkoutItem();
        }
    }

    @Override
    public int getItemCount() {
        return workout.getEquipment().size()+1;
    }


}
