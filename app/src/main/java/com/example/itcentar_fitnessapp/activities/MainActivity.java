package com.example.itcentar_fitnessapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.adapters.DaysOfWeekViewAdapter;
import com.example.itcentar_fitnessapp.adapters.EventDataViewAdapter;
import com.example.itcentar_fitnessapp.clients.AppClient;
import com.example.itcentar_fitnessapp.enums.MyDaysOfTheWeek;
import com.example.itcentar_fitnessapp.interfaces.IComponentInitializer;
import com.example.itcentar_fitnessapp.interfaces.IDayOfWeekClickedListener;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;
import com.example.itcentar_fitnessapp.models.DailyEvent;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.User;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;
import com.makeramen.roundedimageview.RoundedImageView;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IComponentInitializer, View.OnClickListener {
    TextView mUserGreetingsText,mUserNameText,mUserWorkoutLevelText,mUserPointsText;
    TextView mDayOfTheWeekText,mDayMonthText;
    RecyclerView mDaysOfWeekRecyclerView,mViewEventDataRecyclerView;
    RoundedImageView mUserImage;
    ImageButton documentButton;
    User mUserToDisplay;
    List<Event> mWeeklyEvents;
    WeeklyProgress mWeeklyProgress;
    DaysOfWeekViewAdapter mDaysOfWeekViewAdapter;
    EventDataViewAdapter mEventDataViewAdapter;
    IUserToDisplayCallback mUserToDisplayCallback;
    IEventToDisplayCallback mEventToDisplayCallback;
    IWeeklyProgressCallback mWeeklyProgressCallback;
    IDayOfWeekClickedListener mDayOfWeekClickedListener;
    int mCollectedEventsCounter;


    @Override
    protected void onResume() {
        super.onResume();
        if(mViewEventDataRecyclerView.getAdapter()!=null)
            mViewEventDataRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        AppClient.getInstance().getUser(mUserToDisplayCallback);
        displayDateAndGreetUser();


    }

    @Override
    public void initializeComponents() {
        mUserImage=findViewById(R.id.image_view_user_image);
        mUserNameText=findViewById(R.id.text_view_user_name);
        mUserPointsText=findViewById(R.id.text_view_user_points);
        mUserWorkoutLevelText=findViewById(R.id.text_view_user_workout_level);
        mDayMonthText=findViewById(R.id.text_view_date);
        mDayOfTheWeekText=findViewById(R.id.text_view_day_of_the_week);
        mUserGreetingsText=findViewById(R.id.text_view_user_greetings);
        mDaysOfWeekRecyclerView=findViewById(R.id.recycler_view_days_of_week);
        mViewEventDataRecyclerView=findViewById(R.id.recycler_view_event_data);
        documentButton=findViewById(R.id.image_button_document);
        documentButton.setOnClickListener(this);
        mUserToDisplayCallback=new UserToDisplayCallback();
        mEventToDisplayCallback=new EventToDisplayCallback();
        mWeeklyProgressCallback=new WeeklyProgressCallback();
        mDayOfWeekClickedListener=new DayOfWeekClickedListener();
        mWeeklyEvents=new ArrayList<>();
    }


    @Override
    public void onClick(View view) {
        int clickedId=view.getId();
        if(clickedId==R.id.image_button_document){
            Intent intent = new Intent(this, ReorderElementsActivity.class);
            startActivity(intent);
        }
    }


    private void displayUser(User fetchedUser) {
        Glide.with(this)
                .load(fetchedUser.getImage())
                .into(mUserImage);
        mUserNameText.setText(fetchedUser.getName());
        mUserPointsText.setText(fetchedUser.getPoints().toString());
        mUserWorkoutLevelText.setText(fetchedUser.getWorkout_level());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayDateAndGreetUser() {
        LocalDate todaysDate=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM");
        int day=todaysDate.getDayOfMonth();
        String dateToDisplay=day+" "+todaysDate.format(formatter);
        mDayMonthText.setText(dateToDisplay);
        DayOfWeek dayOfWeek=todaysDate.getDayOfWeek();
        mDayOfTheWeekText.setText(dayOfWeek.toString().charAt(0)+dayOfWeek.toString().substring(1).toLowerCase());
        greetUser();
    }

    private void greetUser() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("kk");
        String timeIn24Hours = formatter.format(currentDate);
        int currentHour=Integer.parseInt(timeIn24Hours);

        if(currentHour>=5 && currentHour<12){
            displayGreetings("Good Morning");
        }else if(currentHour>=13 && currentHour<17)
            displayGreetings("Good Afternoon");
        else if(currentHour>=17 || currentHour<5)
            displayGreetings("Good Evening");
        else
            displayGreetings("Greetings");
    }

    private void displayGreetings(String greetings) {
        mUserGreetingsText.setText(greetings);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkDataForWeekCompleted() {
        mCollectedEventsCounter++;
        if(mCollectedEventsCounter==7) {
            createWeekAdapterView(0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createWeekAdapterView(int position) {
        if(mDaysOfWeekViewAdapter==null) {
            mDaysOfWeekViewAdapter = new DaysOfWeekViewAdapter(7, mWeeklyProgress, mWeeklyEvents, this, mDayOfWeekClickedListener);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager((Context) this, 7);
            mDaysOfWeekRecyclerView.setLayoutManager(layoutManager);
            mDaysOfWeekRecyclerView.setAdapter(mDaysOfWeekViewAdapter);
            int clickedPosition=mDaysOfWeekViewAdapter.getClickedPosition();
            String clickedDay= MyDaysOfTheWeek.values()[clickedPosition].getDay();
            DailyEvent clickedDailyEvent=mDaysOfWeekViewAdapter.getDailyEventByName(clickedDay);
            Event clickedEvent=mDaysOfWeekViewAdapter.getEventToBind(clickedDailyEvent.getEventId());
            createEventToDisplayAdapterView(clickedEvent);
        }else{
            mDaysOfWeekViewAdapter.setClickedPosition(position);
            mDaysOfWeekRecyclerView.setAdapter(mDaysOfWeekViewAdapter);
        }
    }

    private void createEventToDisplayAdapterView(Event eventToDisplay) {

        if(mEventDataViewAdapter==null) {
            mEventDataViewAdapter = new EventDataViewAdapter(AppClient.getInstance().getDisplayOrder(), mWeeklyProgress, eventToDisplay, this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager((Context) this, 1);
            mViewEventDataRecyclerView.setLayoutManager(layoutManager);
            mViewEventDataRecyclerView.setAdapter(mEventDataViewAdapter);
        }else{
            mEventDataViewAdapter.setEventToDisplay(eventToDisplay);
            mViewEventDataRecyclerView.setAdapter(mEventDataViewAdapter);
        }

    }




    private class WeeklyProgressCallback implements  IWeeklyProgressCallback{

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void getWeeklyProgressSuccess(WeeklyProgress fetchedProgress) {
            mWeeklyProgress=fetchedProgress;
            mCollectedEventsCounter=0;
            for(DailyEvent dailyEvent : mWeeklyProgress.getEvents()){
                if(dailyEvent.getEventId()!=null)
                  AppClient.getInstance().getEvent(mEventToDisplayCallback,dailyEvent.getEventId());
                else
                    checkDataForWeekCompleted();
            }
        }
        @Override
        public void getWeeklyProgressFailed(String log) {
            Log.e("WeeklyProgressError: ",log);
        }
    }


    private class EventToDisplayCallback implements IEventToDisplayCallback {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void getEventSuccess(Event fetchedEvent) {
            if(fetchedEvent!=null) {
                mWeeklyEvents.add(fetchedEvent);
                checkDataForWeekCompleted();
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void getEventFailed(String log) {
            Log.e("EventError: ",log);
            checkDataForWeekCompleted();
        }
    }

    private class UserToDisplayCallback implements IUserToDisplayCallback{
        @Override
        public void getUserSuccess(User fetchedUser) {
            mUserToDisplay=fetchedUser;
            AppClient.getInstance().getWeeklyProgress(mWeeklyProgressCallback);
            displayUser(fetchedUser);
        }
        @Override
        public void getUserFailed(String log) {
            Log.e("UserError: ",log);
        }
    }

    public class DayOfWeekClickedListener implements IDayOfWeekClickedListener{

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void dayOfWeekClicked(Event dailyEvent,int position) {
            createEventToDisplayAdapterView(dailyEvent);
            createWeekAdapterView(position);

        }
    }


}