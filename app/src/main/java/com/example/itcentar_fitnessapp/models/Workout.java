package com.example.itcentar_fitnessapp.models;

import java.util.List;

public class Workout {
    private String title;
    private String background;
    private Integer time;
    private List<Item> equipment;
    private transient boolean isRestDay=false;

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public boolean isRestDay() {
        return isRestDay;
    }

    public void setRestDay(boolean restDay) {
        isRestDay = restDay;
    }
}
