package com.example.itcentar_fitnessapp.enums;

public enum HomeScreenParts {
    WORKOUT_EQUIPMENT(0), RECIPE(1),MINDSET(2),PROGRESS(3),BETTER_YOU(4);

    private final int value;
    private HomeScreenParts(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
