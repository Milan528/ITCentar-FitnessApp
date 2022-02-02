package com.example.itcentar_fitnessapp.clients;

import com.example.itcentar_fitnessapp.interfaces.IFitnessAppRetrofitAPI;
import com.example.itcentar_fitnessapp.interfaces.IRetrofitClient;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.User;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;
import com.example.itcentar_fitnessapp.models.Workout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FitnessAppRetrofitClient implements IRetrofitClient {
    private final Retrofit mRetrofit;
    private final IFitnessAppRetrofitAPI mRetrofitAPI;
    private static final String BASE_URL="https://json-test.itcentar.rs/jsons/";

    public FitnessAppRetrofitClient(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitAPI=mRetrofit.create(IFitnessAppRetrofitAPI.class);
    }


    @Override
    public void getUserToDisplay(IUserToDisplayCallback callback) {
        Call<User> call=mRetrofitAPI.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    return;
                }
               User user= response.body();
                callback.getUserSuccess(user);
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.getUserFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getEventToDisplay(IEventToDisplayCallback callback,Integer eventId) {
        String myUrl=BASE_URL+"event/"+eventId.toString()+".json";
        Call<Event> call=mRetrofitAPI.getEvent(myUrl);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {

                if(!response.isSuccessful()){
                    callback.getEventFailed("Failed to load event");
                    return;
                }
                Event event= response.body();
                event.setEventId(eventId);
                callback.getEventSuccess(event);
            }
            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                callback.getEventFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getWeeklyProgress(IWeeklyProgressCallback callback) {
        Call<WeeklyProgress> call=mRetrofitAPI.getWeeklyProgress();
        call.enqueue(new Callback<WeeklyProgress>() {
            @Override
            public void onResponse(Call<WeeklyProgress> call, Response<WeeklyProgress> response) {

                if(!response.isSuccessful()){
                    return;
                }
                WeeklyProgress progress= response.body();
                callback.getWeeklyProgressSuccess(progress);

            }
            @Override
            public void onFailure(Call<WeeklyProgress> call, Throwable t) {
                callback.getWeeklyProgressFailed(t.getMessage());
            }
        });
    }


}
