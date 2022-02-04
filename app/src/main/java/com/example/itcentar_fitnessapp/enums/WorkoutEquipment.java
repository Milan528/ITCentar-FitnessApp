package com.example.itcentar_fitnessapp.enums;

import androidx.recyclerview.widget.RecyclerView;

public enum WorkoutEquipment{
    mat(0, "Mat"),scale(1, "Scale"),dumbbell(2, "Dumbbell");


    private final int value;
    private final String name;
    private WorkoutEquipment(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    public String getDay(){ return this.name;}
}
