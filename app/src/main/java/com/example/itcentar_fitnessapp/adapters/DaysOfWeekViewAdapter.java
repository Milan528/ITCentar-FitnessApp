package com.example.itcentar_fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.holders.DayOfWeekHolder;
import com.example.itcentar_fitnessapp.interfaces.IDayOfWeekClickedListener;
import com.example.itcentar_fitnessapp.models.DailyEvent;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;

import java.util.List;

public class DaysOfWeekViewAdapter extends RecyclerView.Adapter<DayOfWeekHolder> {
    private int size;
    private WeeklyProgress weeklyProgress;
    private List<Event> events;
    private Context context;
    private IDayOfWeekClickedListener dayOfWeekClickedListener;

    public DaysOfWeekViewAdapter(int size, WeeklyProgress weeklyProgress, List<Event> events, Context context, IDayOfWeekClickedListener dayOfWeekClickedListener) {
        this.size = size;
        this.weeklyProgress = weeklyProgress;
        this.events = events;
        this.context=context;
        this.dayOfWeekClickedListener = dayOfWeekClickedListener;
    }

    @NonNull
    @Override
    public DayOfWeekHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.day_of_week_holder,parent,false);
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        layoutParams.height=(int)(parent.getHeight());//velicina jende celije
        layoutParams.width=(int)(parent.getHeight());
        return new DayOfWeekHolder(view, dayOfWeekClickedListener,context);
    }

    @Override
    public void onBindViewHolder(@NonNull DayOfWeekHolder holder, int position) {
        if(position==0)
            holder.setCurrentlyDisplayed(true);
        else
            holder.setCurrentlyDisplayed(false);

        Event eventToBind=getEventToBind(weeklyProgress.getEvents().get(position).getEventId());
        holder.setDailyEvent(eventToBind);
        String startLetter=weeklyProgress.getEvents().get(position).getDay().charAt(0)+"";
        holder.setStartingLetter(startLetter);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public Event getEventToBind(Integer id){
        for(Event event : events){
            if(event.getEventId()==id)
                return event;
        }
        return null;
    }
}
