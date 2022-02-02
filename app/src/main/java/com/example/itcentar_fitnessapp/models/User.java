package com.example.itcentar_fitnessapp.models;

public class User {
    private String name;
    private String image;
    private Integer points;
    private String workout_level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getWorkout_level() {
        return workout_level;
    }

    public void setWorkout_level(String workout_level) {
        this.workout_level = workout_level;
    }
}
