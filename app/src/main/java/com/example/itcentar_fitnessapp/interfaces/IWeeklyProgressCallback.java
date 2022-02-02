package com.example.itcentar_fitnessapp.interfaces;

import com.example.itcentar_fitnessapp.models.WeeklyProgress;

public interface IWeeklyProgressCallback {
    void getWeeklyProgressSuccess(WeeklyProgress fetchedProgress);
    void getWeeklyProgressFailed(String log);
}
