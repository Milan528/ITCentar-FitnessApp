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
import com.example.itcentar_fitnessapp.holders.MindsetHolder;
import com.example.itcentar_fitnessapp.holders.Placeholder;
import com.example.itcentar_fitnessapp.holders.ProgressHolder;
import com.example.itcentar_fitnessapp.holders.RecipeHolder;
import com.example.itcentar_fitnessapp.holders.WorkoutHolder;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDataViewAdapter extends RecyclerView.Adapter {
    private List<HomeScreenParts> order;
    private WeeklyProgress weeklyProgress;
    private Event event;
    private Context context;
    private  Map<Integer,Integer> resMap;

    public EventDataViewAdapter(List<HomeScreenParts> order, WeeklyProgress weeklyProgress, Event event, Context context) {
        this.order = order;
        this.weeklyProgress = weeklyProgress;
        this.event=checkEvent(event);
        this.context = context;
        this.resMap=createViewToResourceMap();
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
        Integer resource=resMap.get(viewType);
        if(resource==null){
            view=inflateView(R.layout.view_placeholder,parent);
            viewHolder=new Placeholder(view);
        }else{
            view=inflateView(resource,parent);
            if(viewType == HomeScreenParts.WORKOUT_EQUIPMENT.getValue()){
                viewHolder=new WorkoutHolder(view,event.getWorkout(),context);
                ((WorkoutHolder)viewHolder).setHolderTittleVisibility(View.GONE);
            }else if(viewType == HomeScreenParts.RECIPE.getValue()){
                viewHolder=new RecipeHolder(view, event.getRecipe(),context);
                if(event.isPlaceholder())
                    ((RecipeHolder)viewHolder).setHolderTittleVisibility(View.GONE);
            }else if(viewType == HomeScreenParts.MINDSET.getValue()){
                viewHolder=new MindsetHolder(view,event.getMindset(),context);
            }else if(viewType == HomeScreenParts.WEEKLY_PROGRESS.getValue()){
                viewHolder=new ProgressHolder(view,
                        "This weeks progress",
                        weeklyProgress.getWeekly_progress().toString()+" completed programs");
            }else if(viewType == HomeScreenParts.MONTHLY_PROGRESS.getValue()){
                viewHolder=new ProgressHolder(view,
                        "This month progress",
                        weeklyProgress.getMonthly_progress().toString()+" completed programs");
            }else if(viewType == HomeScreenParts.WORKOUT_TIP.getValue()){
                viewHolder=new ProgressHolder(view,
                        "Workout tip!",
                        event.getWorkout_tip());
            }else if(viewType == HomeScreenParts.BETTER_YOU.getValue()){
                viewHolder=new BetterYouHolder(view,context);
            }else{
                viewHolder=new Placeholder(view);
            }
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





    private Map<Integer, Integer> createViewToResourceMap() {
        Map<Integer,Integer> myMap=new HashMap<>();
        myMap.put(HomeScreenParts.WORKOUT_EQUIPMENT.getValue(),R.layout.view_recipe_mindset_workout_holder);
        myMap.put(HomeScreenParts.RECIPE.getValue(),R.layout.view_recipe_mindset_workout_holder);
        myMap.put(HomeScreenParts.MINDSET.getValue(),R.layout.view_recipe_mindset_workout_holder);
        myMap.put(HomeScreenParts.WEEKLY_PROGRESS.getValue(),R.layout.view_progress_holder);
        myMap.put(HomeScreenParts.MONTHLY_PROGRESS.getValue(),R.layout.view_progress_holder);
        myMap.put(HomeScreenParts.WORKOUT_TIP.getValue(),R.layout.view_progress_holder);
        myMap.put(HomeScreenParts.BETTER_YOU.getValue(),R.layout.view_better_you_holder);
        return myMap;
    }
}
