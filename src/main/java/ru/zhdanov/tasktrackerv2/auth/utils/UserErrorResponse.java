package ru.zhdanov.tasktrackerv2.auth.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorResponse {
    private String message;
    private long timestamp;

    public UserErrorResponse(String message) {
        this.message = message;
    }
}
