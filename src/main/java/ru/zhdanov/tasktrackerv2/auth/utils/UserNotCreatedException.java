package ru.zhdanov.tasktrackerv2.auth.utils;

public class UserNotCreatedException extends RuntimeException{

    public UserNotCreatedException(String msg){
        super(msg);
    }
}
