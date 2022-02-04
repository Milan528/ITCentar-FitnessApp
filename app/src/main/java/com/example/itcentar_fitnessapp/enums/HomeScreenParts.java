package com.example.itcentar_fitnessapp.enums;

public enum HomeScreenParts {
    WORKOUT_EQUIPMENT(0,"Workout, equipment"), RECIPE(1, "Recipe"),
    MINDSET(2, "Mindset"),WEEKLY_PROGRESS(3, "Week progress"),MONTHLY_PROGRESS(4, "Month progress"),
    WORKOUT_TIP(5, "Workout tip"),BETTER_YOU(6, "5 day better you button"),PROGRESS(7, "Week progress, month progress, workout tip");

    private final int value;
    private final String name;
    private HomeScreenParts(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    public String getName(){return name; }

}
