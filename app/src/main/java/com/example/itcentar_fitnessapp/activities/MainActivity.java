package com.example.itcentar_fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.clients.AppClient;
import com.example.itcentar_fitnessapp.interfaces.IComponentInitializer;
import com.example.itcentar_fitnessapp.interfaces.IUserToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IEventToDisplayCallback;
import com.example.itcentar_fitnessapp.interfaces.IWeeklyProgressCallback;
import com.example.itcentar_fitnessapp.models.DailyEvent;
import com.example.itcentar_fitnessapp.models.Event;
import com.example.itcentar_fitnessapp.models.User;
import com.example.itcentar_fitnessapp.models.WeeklyProgress;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IComponentInitializer {
    TextView textViewResult;
    User userToDisplay;
    List<Event> weeklyEvents;
    WeeklyProgress mWeeklyProgress;
    IUserToDisplayCallback mUserToDisplayCallback;
    IEventToDisplayCallback mEventToDisplayCallback;
    IWeeklyProgressCallback mWeeklyProgressCallback;

    private class WeeklyProgressCallback implements  IWeeklyProgressCallback{

        @Override
        public void getWeeklyProgressSuccess(WeeklyProgress fetchedProgress) {
            mWeeklyProgress=fetchedProgress;
            for(DailyEvent dailyEvent : mWeeklyProgress.getEvents()){
                AppClient.getInstance().getEvent(mEventToDisplayCallback,dailyEvent.getEventId());
            }
        }
        @Override
        public void getWeeklyProgressFailed(String log) {
            Log.e("WeeklyProgressError: ",log);
        }
    }

    private class EventToDisplayCallback implements IEventToDisplayCallback {
        @Override
        public void getEventSuccess(Event fetchedEvent) {
            weeklyEvents.add(fetchedEvent);
        }
        @Override
        public void getEventFailed(String log) {
            Log.e("EventError: ",log);
        }
    }
    private class UserToDisplayCallback implements IUserToDisplayCallback{
        @Override
        public void getUserSuccess(User fetchedUser) {
            userToDisplay=fetchedUser;
            displayUser(fetchedUser);
        }
        @Override
        public void getUserFailed(String log) {
            Log.e("UserError: ",log);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        AppClient.getInstance().getUser(mUserToDisplayCallback);
        AppClient.getInstance().getWeeklyProgress(mWeeklyProgressCallback);


    }
/*
    private void getComments() {
        Call<List<Comment>> call=ijsonPlaceholderAPI.getComments("posts/3/comments");
        //Call<List<Comment>> call=ijsonPlaceholderAPI.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText(response.code());
                    return;
                }
                List<Comment> comments= response.body();

                for(Comment p : comments){
                    String content="";
                    content+="ID: "+p.getId()+"\n";
                    content+="PostID: "+p.getPostId()+"\n";
                    content+="Email: "+p.getEmail()+"\n";
                    content+="Text: "+p.getText()+"\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }

    private void getposts() {
        Map<String,String> params=new HashMap<>();
        params.put("userId","1");
        params.put("_sort","id");
        params.put("_order","desc");

        Call<List<Post>> call=ijsonPlaceholderAPI.getPosts(params);
      //  Call<List<Post>> call=ijsonPlaceholderAPI.getPosts(new Integer[]{2,3,6},null,null);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText(response.code());
                    return;
                }
                List<Post> posts= response.body();

                for(Post p : posts){
                    String content="";
                    content+="ID: "+p.getId()+"\n";
                    content+="UserID: "+p.getUserId()+"\n";
                    content+="Title: "+p.getTitle()+"\n";
                    content+="Text: "+p.getText()+"\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
*/
    @Override
    public void initializeComponents() {
        textViewResult=findViewById(R.id.text_view);
        mUserToDisplayCallback=new UserToDisplayCallback();
        mEventToDisplayCallback=new EventToDisplayCallback();
        mWeeklyProgressCallback=new WeeklyProgressCallback();
        weeklyEvents=new ArrayList<>();
    }
    private void displayUser(User fetchedUser) {
        String content="";
        content+="Name: "+fetchedUser.getName();
        content+="Points: "+fetchedUser.getPoints();
        content+="Workout Level: "+fetchedUser.getWorkout_level();
        content+="Image: "+fetchedUser.getImage();
        textViewResult.append(content);
    }
}