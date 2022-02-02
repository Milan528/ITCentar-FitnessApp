package com.example.itcentar_fitnessapp.models;

import java.util.List;

public class WeeklyProgress {
    private Integer monthly_progress;
    private Integer weekly_progress;
    private List<DailyEvent> events;

    public Integer getMonthly_progress() {
        return monthly_progress;
    }

    public void setMonthly_progress(Integer monthly_progress) {
        this.monthly_progress = monthly_progress;
    }

    public Integer getWeekly_progress() {
        return weekly_progress;
    }

    public void setWeekly_progress(Integer weekly_progress) {
        this.weekly_progress = weekly_progress;
    }

    public List<DailyEvent> getEvents() {
        return events;
    }

    public void setEvents(List<DailyEvent> events) {
        this.events = events;
    }
}
