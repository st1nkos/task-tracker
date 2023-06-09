package ru.zhdanov.tasktrackerv2.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private int id;
    @NotEmpty
    private String title;
    private String description;
    @JsonProperty("status")
    private String status;

}
