package com.example.itcentar_fitnessapp.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.enums.MyDaysOfTheWeek;
import com.example.itcentar_fitnessapp.holders.DayOfWeekHolder;
import com.example.itcentar_fitnessapp.interfaces.IDayOfWeekClickedListener;
import com.example.itcentar_fitnessapp.models.DailyEvent;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DaysOfWeekViewAdapter extends RecyclerView.Adapter<DayOfWeekHolder> {
    private int size;
    private WeeklyProgress weeklyProgress;
    private List<Event> events;
    private Context context;
    private IDayOfWeekClickedListener dayOfWeekClickedListener;
    private int clickedPosition;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DaysOfWeekViewAdapter(int size, WeeklyProgress weeklyProgress, List<Event> events, Context context, IDayOfWeekClickedListener dayOfWeekClickedListener) {
        this.size = size;
        this.weeklyProgress = weeklyProgress;
        this.events = events;
        this.context=context;
        this.dayOfWeekClickedListener = dayOfWeekClickedListener;

        LocalDate todaysDate=LocalDate.now();
        int day=todaysDate.getDayOfWeek().getValue();
        this.clickedPosition=day-1;
    }

    @NonNull
    @Override
    public DayOfWeekHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.day_of_week_holder,parent,false);
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        int parentWidth=parent.getMeasuredWidth();
        layoutParams.height=(int)(parentWidth/8);
        layoutParams.width=(int)(parentWidth/8);
        return new DayOfWeekHolder(view, dayOfWeekClickedListener,context);
    }

    @Override
    public void onBindViewHolder(@NonNull DayOfWeekHolder holder, int position) {
        if(position==clickedPosition)
            holder.setCurrentlyDisplayed(true);
        else
            holder.setCurrentlyDisplayed(false);

        String dayString= MyDaysOfTheWeek.values()[position].getDay();
        DailyEvent dailyEvent=getDailyEventByName(dayString);
        Event eventToBind=getEventToBind(dailyEvent.getEventId());
        holder.setEvent(eventToBind);
        holder.setDailyEvent(dailyEvent);
        //String startLetter=weeklyProgress.getEvents().get(position).getDay().charAt(0)+"";
        //holder.setStartingLetter(startLetter);
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

    public DailyEvent getDailyEventByName(String dayString) {
        for(DailyEvent d : weeklyProgress.getEvents()){
            if(d.getDay().equals(dayString))
                return d;
        }
        return null;
    }


    public int getClickedPosition() {
        return clickedPosition;
    }

    public void setClickedPosition(int clickedPosition) {
        this.clickedPosition = clickedPosition;
    }
}
