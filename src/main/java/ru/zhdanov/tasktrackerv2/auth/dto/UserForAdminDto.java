package ru.zhdanov.tasktrackerv2.auth.dto;

import lombok.Data;
import ru.zhdanov.tasktrackerv2.tasks.dto.TaskDto;

import java.util.List;

@Data
public class UserForAdminDto {

    private int id;

    private String username;

    private String role;

    private List<TaskDto> tasks;
}
