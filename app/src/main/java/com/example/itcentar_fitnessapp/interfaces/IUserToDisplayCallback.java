package com.example.itcentar_fitnessapp.interfaces;

import com.example.itcentar_fitnessapp.models.User;

public interface IUserToDisplayCallback {
    void getUserSuccess(User fetchedUser);
    void getUserFailed(String log);
}
