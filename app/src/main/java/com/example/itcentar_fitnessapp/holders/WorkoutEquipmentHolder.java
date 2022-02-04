package com.example.itcentar_fitnessapp.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.models.Item;

public class WorkoutEquipmentHolder extends RecyclerView.ViewHolder {
    ImageView equipmentImage;
    TextView equipmentText;
    Item item;

    public WorkoutEquipmentHolder(@NonNull View itemView) {
        super(itemView);
        equipmentImage=itemView.findViewById(R.id.image_view_equipment);
        equipmentText=itemView.findViewById(R.id.text_view_workout_equipment);



    }

    public void createWorkoutTime(String workoutTime) {
       setImageRes(R.mipmap.clock);
       equipmentText.setText(workoutTime);
    }

    public void createWorkoutItem(){
        if(item!=null) {
            String text=item.getItem();
            equipmentText.setText(text.substring(0,1).toUpperCase()+text.substring(1));
            setImageBasedOnItem();
        }
    }

    private void setImageBasedOnItem() {
        if(item.getItem().equals("mat"))
            setImageRes(R.mipmap.matt);
        else if(item.getItem().equals("scale"))
            setImageRes(R.mipmap.weight);
        else if (item.getItem().equals("dumbbell"))
            setImageRes(R.mipmap.dumbbell);
    }
    private void setImageRes(int res){
        equipmentImage.setImageResource(res);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;

    }
}
