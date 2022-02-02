package com.example.itcentar_fitnessapp.models;

import com.google.gson.annotations.SerializedName;

public class Mindset {
    @SerializedName("title:")
    private String title;
    private String background;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
