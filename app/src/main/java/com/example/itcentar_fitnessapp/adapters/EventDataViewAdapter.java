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
import com.example.itcentar_fitnessapp.holders.DayOfWeekHolder;
import com.example.itcentar_fitnessapp.holders.MindsetHolder;
import com.example.itcentar_fitnessapp.holders.Placeholder;
import com.example.itcentar_fitnessapp.holders.RecipeHolder;
import com.example.itcentar_fitnessapp.interfaces.IDayOfWeekClickedListener;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;

public class EventDataViewAdapter extends RecyclerView.Adapter {
    private int size;
    private WeeklyProgress weeklyProgress;
    private Event event;
    private Context context;

    public EventDataViewAdapter(int size, WeeklyProgress weeklyProgress, Event event, Context context) {
        this.size = size;
        this.weeklyProgress = weeklyProgress;
        this.event = event;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int typeValue=AppClient.getInstance().getDisplayOrder().get(position).getValue();
        return typeValue;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder;
        if(viewType== HomeScreenParts.WORKOUT_EQUIPMENT.getValue()){
            view=inflateView(R.layout.view_placeholder,parent);
            viewHolder=new Placeholder(view);
        }else if(viewType== HomeScreenParts.RECIPE.getValue()){
            view=inflateView(R.layout.view_recipe_or_mindset,parent);
            viewHolder=new RecipeHolder(view, event.getRecipe(),context);
        }else if(viewType== HomeScreenParts.MINDSET.getValue()){
            view=inflateView(R.layout.view_recipe_or_mindset,parent);
            viewHolder=new MindsetHolder(view,event.getMindset(),context);
        }else if(viewType== HomeScreenParts.PROGRESS.getValue()){
            view=inflateView(R.layout.view_placeholder,parent);
            viewHolder=new Placeholder(view);
        }else if(viewType== HomeScreenParts.BETTER_YOU.getValue()){
            view=inflateView(R.layout.view_placeholder,parent);
            viewHolder=new Placeholder(view);
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
        return size;
    }
}
