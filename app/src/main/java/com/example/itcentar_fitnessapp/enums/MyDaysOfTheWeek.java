package com.example.itcentar_fitnessapp.enums;

public enum MyDaysOfTheWeek {
    Monday(0, "Monday"),Tuesday(1, "Tuesday"),Wendesday(2, "Wendesday"),Thursday(3, "Thursday"),
    Friday(4, "Friday"),Saturday(5, "Saturday"),Sunday(6, "Sunday");

    private final int value;
    private final String name;
    private MyDaysOfTheWeek(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    public String getDay(){ return this.name;}
}
