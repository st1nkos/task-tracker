package ru.zhdanov.tasktrackerv2.auth.utils;

import lombok.Data;

import java.util.Map;

@Data
public class ExceptionBody {
    private String message;
    private Map<String, String> errors;

    public ExceptionBody(String message) {
        this.message = message;
    }
}
