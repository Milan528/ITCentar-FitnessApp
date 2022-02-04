package com.example.itcentar_fitnessapp.clients;

import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.interfaces.IRetrofitClient;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.Mindset;
import com.example.itcentar_fitnessapp.models.Recipe;
import com.example.itcentar_fitnessapp.models.Workout;

import java.util.ArrayList;
import java.util.List;

public class AppClient {
    private IRetrofitClient mRetrofitClient;
    private List<HomeScreenParts> displayOrder;
    private Event mPlaceholderEvent;


    private AppClient(){

        mRetrofitClient=new FitnessAppRetrofitClient();
        displayOrder=new ArrayList<>();
        displayOrder.add(HomeScreenParts.WORKOUT_EQUIPMENT);
        displayOrder.add(HomeScreenParts.RECIPE);
        displayOrder.add(HomeScreenParts.MINDSET);
        displayOrder.add(HomeScreenParts.WEEKLY_PROGRESS);
        displayOrder.add(HomeScreenParts.MONTHLY_PROGRESS);
        displayOrder.add(HomeScreenParts.WORKOUT_TIP);
        displayOrder.add(HomeScreenParts.BETTER_YOU);

        Recipe recipePlaceholder=new Recipe();
        Mindset mindsetPlaceholder=new Mindset();
        Workout workoutPlaceholder=new Workout();

        mindsetPlaceholder.setBackground("https://www.runtastic.com/blog/wp-content/uploads/2015/07/signs-you-meed-a-rest-day-thumbnail_blog_1200x800-1-1024x683.jpg");
        mindsetPlaceholder.setTitle("You worked hard these past few days, you deserve it!");
        recipePlaceholder.setBackground("https://www.foodspring.de/magazine/wp-content/uploads/2020/11/pizza_vegan-1.jpg");
        recipePlaceholder.setTitle("Protein pizza with vegetables, legumes and whole grains");
        workoutPlaceholder.setBackground("https://static6.depositphotos.com/1005155/629/i/600/depositphotos_6291421-stock-photo-day-off-reminder.jpg");
        workoutPlaceholder.setTitle("");
        workoutPlaceholder.setEquipment(null);
        workoutPlaceholder.setTime(null);
        workoutPlaceholder.setRestDay(true);
        mPlaceholderEvent=new Event();
        mPlaceholderEvent.setWorkout(workoutPlaceholder);
        mPlaceholderEvent.setMindset(mindsetPlaceholder);
        mPlaceholderEvent.setRecipe(recipePlaceholder);
        mPlaceholderEvent.setWorkout_tip("Rest day always feels like an eternity");

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

    public Event getPlaceholderEvent() {
        return mPlaceholderEvent;
    }

    public void setPlaceholderEvent(Event placeholderEvent) {
        this.mPlaceholderEvent = placeholderEvent;
    }


    }
