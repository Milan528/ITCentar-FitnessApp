package com.example.itcentar_fitnessapp.interfaces;

public interface IRetrofitClient {
    void getUserToDisplay(IUserToDisplayCallback callback);
    void getEventToDisplay(IEventToDisplayCallback callback,Integer eventId);
    void getWeeklyProgress(IWeeklyProgressCallback callback);

}
