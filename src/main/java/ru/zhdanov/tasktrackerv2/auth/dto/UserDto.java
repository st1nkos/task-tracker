package ru.zhdanov.tasktrackerv2.auth.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
