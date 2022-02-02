package com.example.itcentar_fitnessapp.interfaces;


import com.example.itcentar_fitnessapp.models.User;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface IFitnessAppRetrofitAPI {
    @GET("user.json")
    Call<User> getUser();

    @GET("events.json")
    Call<WeeklyProgress> getWeeklyProgress();

    @GET
    Call<Event> getEvent(@Url String url);

}
