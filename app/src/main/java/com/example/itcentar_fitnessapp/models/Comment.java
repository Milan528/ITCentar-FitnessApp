package com.example.itcentar_fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class Comment {
    int postId;
    int id;
    String name;
    String email;
    @SerializedName("body")
    String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
