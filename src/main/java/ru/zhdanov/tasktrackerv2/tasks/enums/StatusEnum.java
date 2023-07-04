package ru.zhdanov.tasktrackerv2.tasks.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    TO_DO("Выполнить"),
    IN_PROGRESS("Выполняется"),
    DONE("Выполнено");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
