package com.example.itcentar_fitnessapp.clients;

import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.interfaces.IRetrofitClient;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.Item;
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
        displayOrder=createDisplayOrderList();
        mPlaceholderEvent=createNewEvent("https://cdn.quotesgram.com/img/63/66/1880228190-9e66b1020324188f2859a62e3ac9f1d4.jpg","",
                "https://www.runtastic.com/blog/wp-content/uploads/2015/07/signs-you-meed-a-rest-day-thumbnail_blog_1200x800-1-1024x683.jpg","You worked hard these past few days, you deserve it!",
                "https://static6.depositphotos.com/1005155/629/i/600/depositphotos_6291421-stock-photo-day-off-reminder.jpg","",
                null,null,true,null,"Rest day always feels like an eternity...",true,false);
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



    private List<HomeScreenParts> createDisplayOrderList() {
        List<HomeScreenParts> displayOrder=new ArrayList<>();
        displayOrder.add(HomeScreenParts.WORKOUT_EQUIPMENT);
        displayOrder.add(HomeScreenParts.RECIPE);
        displayOrder.add(HomeScreenParts.MINDSET);
        displayOrder.add(HomeScreenParts.WEEKLY_PROGRESS);
        displayOrder.add(HomeScreenParts.MONTHLY_PROGRESS);
        displayOrder.add(HomeScreenParts.WORKOUT_TIP);
        displayOrder.add(HomeScreenParts.BETTER_YOU);
        return displayOrder;
    }


    private Event createNewEvent(String recipeBackground, String recipeTittle, String mindsetBackground, String mindsetTittle,
                                 String workoutBackground, String workoutTittle, List<Item> workoutEquipment,Integer workoutDuration,
                                 boolean isRestDay,Integer eventId,String workoutTip,boolean eventIsPlaceholder,boolean isCompleted) {

        Event eventToReturn=new Event();
        Recipe recipePlaceholder=new Recipe();
        Mindset mindsetPlaceholder=new Mindset();
        Workout workoutPlaceholder=new Workout();


        recipePlaceholder.setBackground(recipeBackground);
        recipePlaceholder.setTitle(recipeTittle);


        mindsetPlaceholder.setBackground(mindsetBackground);
        mindsetPlaceholder.setTitle(mindsetTittle);


        workoutPlaceholder.setBackground(workoutBackground);
        workoutPlaceholder.setTitle(workoutTittle);
        workoutPlaceholder.setEquipment(workoutEquipment);
        workoutPlaceholder.setTime(workoutDuration);
        workoutPlaceholder.setRestDay(isRestDay);


        eventToReturn.setWorkout(workoutPlaceholder);
        eventToReturn.setMindset(mindsetPlaceholder);
        eventToReturn.setRecipe(recipePlaceholder);
        eventToReturn.setWorkout_tip(workoutTip);
        eventToReturn.setPlaceholder(eventIsPlaceholder);
        eventToReturn.setEventId(eventId);
        eventToReturn.setCompleted(isCompleted);

        return eventToReturn;

    }

    }
