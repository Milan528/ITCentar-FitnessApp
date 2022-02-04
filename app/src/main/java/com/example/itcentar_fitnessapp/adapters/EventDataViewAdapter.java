package com.example.itcentar_fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.clients.AppClient;
import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.holders.BetterYouHolder;
import com.example.itcentar_fitnessapp.holders.DayOfWeekHolder;
import com.example.itcentar_fitnessapp.holders.MindsetHolder;
import com.example.itcentar_fitnessapp.holders.Placeholder;
import com.example.itcentar_fitnessapp.holders.ProgressHolder;
import com.example.itcentar_fitnessapp.holders.RecipeHolder;
import com.example.itcentar_fitnessapp.holders.WorkoutHolder;
import com.example.itcentar_fitnessapp.interfaces.IDayOfWeekClickedListener;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;

import java.util.ArrayList;
import java.util.List;

public class EventDataViewAdapter extends RecyclerView.Adapter {
    private List<HomeScreenParts> order;
    private WeeklyProgress weeklyProgress;
    private Event event;
    private Context context;

    public EventDataViewAdapter(List<HomeScreenParts> order, WeeklyProgress weeklyProgress, Event event, Context context) {
        this.order = order;
        this.weeklyProgress = weeklyProgress;
        this.event=checkEvent(event);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int typeValue=order.get(position).getValue();
        return typeValue;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder;
        if(viewType == HomeScreenParts.WORKOUT_EQUIPMENT.getValue()){
            view=inflateView(R.layout.view_recipe_or_mindset,parent);
            viewHolder=new WorkoutHolder(view,event.getWorkout(),context);
        }else if(viewType == HomeScreenParts.RECIPE.getValue()){
            view=inflateView(R.layout.view_recipe_or_mindset,parent);
            viewHolder=new RecipeHolder(view, event.getRecipe(),context);
        }else if(viewType == HomeScreenParts.MINDSET.getValue()){
            view=inflateView(R.layout.view_recipe_or_mindset,parent);
            viewHolder=new MindsetHolder(view,event.getMindset(),context);
        }else if(viewType == HomeScreenParts.WEEKLY_PROGRESS.getValue()){
            view=inflateView(R.layout.view_progress_holder,parent);
            viewHolder=new ProgressHolder(view,
                    "This weeks progress",
                    weeklyProgress.getWeekly_progress().toString()+" completed programs");
        }else if(viewType == HomeScreenParts.MONTHLY_PROGRESS.getValue()){
            view=inflateView(R.layout.view_progress_holder,parent);
            viewHolder=new ProgressHolder(view,
                    "This month progress",
                    weeklyProgress.getMonthly_progress().toString()+" completed programs");
        }else if(viewType == HomeScreenParts.WORKOUT_TIP.getValue()){
            view=inflateView(R.layout.view_progress_holder,parent);
            viewHolder=new ProgressHolder(view,
                    "Workout tip!",
                    event.getWorkout_tip());
        }else if(viewType == HomeScreenParts.BETTER_YOU.getValue()){
            view=inflateView(R.layout.view_better_you_holder,parent);
            viewHolder=new BetterYouHolder(view,context);
        }else{
            view=inflateView(R.layout.view_placeholder,parent);
            viewHolder=new Placeholder(view);
        }

        return viewHolder;
    }

    private View inflateView(int resource,ViewGroup parent){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        return inflater.inflate(resource,parent,false);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return order.size();
    }

    public void setEventToDisplay(Event eventToDisplay) {
       event=checkEvent(eventToDisplay);
    }

    private Event checkEvent(Event event){
        if(event!=null)
            return event;
        else
            return AppClient.getInstance().getPlaceholderEvent();
    }
}
