package com.jakub.weather.exceptions;

public class UserSettingsNotFoundException extends RuntimeException{

    public UserSettingsNotFoundException(String message) {
        super(message);
    }

}
