package com.example.itcentar_fitnessapp.clients;

import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.interfaces.IRetrofitClient;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;

import java.util.ArrayList;
import java.util.List;

public class AppClient {
    private IRetrofitClient mRetrofitClient;
    private List<HomeScreenParts> displayOrder;



    private AppClient(){

        mRetrofitClient=new FitnessAppRetrofitClient();
        displayOrder=new ArrayList<>();
        displayOrder.add(HomeScreenParts.WORKOUT_EQUIPMENT);
        displayOrder.add(HomeScreenParts.RECIPE);
        displayOrder.add(HomeScreenParts.MINDSET);
        displayOrder.add(HomeScreenParts.PROGRESS);
        displayOrder.add(HomeScreenParts.BETTER_YOU);
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

    public List<HomeScreenParts> getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(List<HomeScreenParts> mDisplayOrder) {
        this.displayOrder = mDisplayOrder;
    }


    }
