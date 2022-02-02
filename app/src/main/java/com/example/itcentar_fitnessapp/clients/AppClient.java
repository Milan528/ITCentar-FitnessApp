package com.example.itcentar_fitnessapp.clients;

import com.example.itcentar_fitnessapp.interfaces.IRetrofitClient;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;

public class AppClient {
    IRetrofitClient mRetrofitClient;

    private AppClient(){
        mRetrofitClient=new FitnessAppRetrofitClient();
    }

    public static AppClient getInstance(){
        return SingletonHolder.instance;
    }



    private static class SingletonHolder{
        public static final AppClient instance=new AppClient();
    }


    public void getUser(IUserToDisplayCallback callback){
        mRetrofitClient.getUserToDisplay(callback);
    }
    public void getEvent(IEventToDisplayCallback callback,Integer eventId) {
        mRetrofitClient.getEventToDisplay(callback,eventId);
    }
    public void getWeeklyProgress(IWeeklyProgressCallback callback) {
        mRetrofitClient.getWeeklyProgress(callback);
    }


    }
