package com.example.itcentar_fitnessapp.interfaces;

import com.example.itcentar_fitnessapp.models.Event;


public interface IEventToDisplayCallback {
    void getEventSuccess(Event fetchedEvent);
    void getEventFailed(String log);
}
