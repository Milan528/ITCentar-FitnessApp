package com.example.itcentar_fitnessapp.models;

public class Event {
    private Workout workout;
    private Recipe recipe;
    private Mindset mindset;
    private String workout_tip;
    private boolean completed;
    private transient Integer eventId;
    private transient boolean isPlaceholder;

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Mindset getMindset() {
        return mindset;
    }

    public void setMindset(Mindset mindset) {
        this.mindset = mindset;
    }

    public String getWorkout_tip() {
        return workout_tip;
    }

    public void setWorkout_tip(String workout_tip) {
        this.workout_tip = workout_tip;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public boolean isPlaceholder() {
        return isPlaceholder;
    }

    public void setPlaceholder(boolean placeholder) {
        isPlaceholder = placeholder;
    }
}
