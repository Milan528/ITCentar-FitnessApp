package com.example.itcentar_fitnessapp.holders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.interfaces.IDayOfWeekClickedListener;
import com.example.itcentar_fitnessapp.models.DailyEvent;
import com.example.itcentar_fitnessapp.models.Event;

public class DayOfWeekHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView startingLetter;
    private Event dailyEvent;
    private IDayOfWeekClickedListener onClickListener;
    private Context context;
    private boolean isCurrentlyDisplayed;
    public DayOfWeekHolder(@NonNull View itemView, IDayOfWeekClickedListener listener,Context ctx) {
        super(itemView);
        startingLetter=itemView.findViewById(R.id.text_view_daily_event);
        onClickListener=listener;
        context=ctx;
        dailyEvent=null;
        isCurrentlyDisplayed=false;
        itemView.setOnClickListener(this);
    }

    public boolean isCurrentlyDisplayed() {
        return isCurrentlyDisplayed;
    }

    public void setCurrentlyDisplayed(boolean currentlyDisplayed) {
        isCurrentlyDisplayed = currentlyDisplayed;
       // changeDisplayDesign();
    }

    private void changeDisplayDesign() {
        if(isCurrentlyDisplayed){
            setNewDesignToDisplay(context.getResources().getColor(R.color.teal),ContextCompat.getDrawable(context, R.drawable.text_view_round_shape_teal_border));
        }else{
            if(dailyEvent==null){
                setNewDesignToDisplay(context.getResources().getColor(R.color.white),ContextCompat.getDrawable(context, R.drawable.text_view_round_shape_gray_background));
            }else{
                if(dailyEvent.isCompleted()){
                    setNewDesignToDisplay(context.getResources().getColor(R.color.white),ContextCompat.getDrawable(context, R.drawable.text_view_round_shape_teal_background));
                }else{
                    setNewDesignToDisplay(context.getResources().getColor(R.color.black),ContextCompat.getDrawable(context, R.drawable.text_view_round_shape_gray_background));
                }
            }
        }

    }

    private void setNewDesignToDisplay(int color, Drawable design){
        startingLetter.setBackground(design);
        startingLetter.setTextColor(color);
    }

    public void setDailyEvent(Event event){
        dailyEvent=event;
        changeDisplayDesign();
    };

    public void setStartingLetter(String letter){
        startingLetter.setText(letter);
    }
    @Override
    public void onClick(View view) {
        onClickListener.dayOfWeekClicked(dailyEvent);
    }

}
