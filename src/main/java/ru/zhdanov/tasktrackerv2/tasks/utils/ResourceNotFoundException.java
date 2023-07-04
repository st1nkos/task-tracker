package ru.zhdanov.tasktrackerv2.tasks.utils;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
