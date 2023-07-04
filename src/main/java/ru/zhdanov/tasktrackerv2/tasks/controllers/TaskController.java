package ru.zhdanov.tasktrackerv2.tasks.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.zhdanov.tasktrackerv2.auth.security.UserDetailsImpl;
import ru.zhdanov.tasktrackerv2.tasks.dto.TaskDto;
import ru.zhdanov.tasktrackerv2.tasks.entity.Task;
import ru.zhdanov.tasktrackerv2.tasks.mappers.TaskMapper;
import ru.zhdanov.tasktrackerv2.tasks.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;



    @GetMapping("/task/{id}")
    public TaskDto getTaskById(@PathVariable int id){
        Task task = taskService.get(id);

        return taskMapper.convertToTaskDto(task);
    }

    @GetMapping("/tasks")
    public List<TaskDto> getAllTaskByUserId(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> taskList = taskService.findAllByUserId(userDetails.getUser().getId());

        return taskList.stream().map(taskMapper::convertToTaskDto).toList();
    }

    @PostMapping("/task")
    public TaskDto updateStatus(@RequestBody @Valid TaskDto taskDto){
        Task task = taskMapper.convertToTask(taskDto);
        Task createdTask = taskService.saveTask(task);
        return taskMapper.convertToTaskDto(createdTask);
    }
}
