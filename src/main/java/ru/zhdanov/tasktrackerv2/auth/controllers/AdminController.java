package ru.zhdanov.tasktrackerv2.auth.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zhdanov.tasktrackerv2.auth.dto.UserForAdminDto;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.auth.mappers.UserMapper;
import ru.zhdanov.tasktrackerv2.auth.services.UserService;
import ru.zhdanov.tasktrackerv2.tasks.dto.TaskDto;
import ru.zhdanov.tasktrackerv2.tasks.entity.Task;
import ru.zhdanov.tasktrackerv2.tasks.mappers.TaskMapper;
import ru.zhdanov.tasktrackerv2.tasks.services.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AdminController {


    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final UserService userService;

    private final UserMapper userMapper;


    @GetMapping("/users")
    public List<UserForAdminDto> getAllUsers(){
        List<User> users = userService.findAll();
        return users.stream().map(userMapper::convertToUserForAdminDto).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserForAdminDto getUser(@PathVariable("id") int id){
        User user = userService.get(id);
        return userMapper.convertToUserForAdminDto(user);
    }

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks(){
        List<Task> tasks = taskService.findAll();
        return tasks.stream().map(taskMapper::convertToTaskDto).collect(Collectors.toList());
    }

    @GetMapping("/task/{id}")
    public TaskDto getTaskById(@PathVariable int id){
        Task task = taskService.get(id);

        return taskMapper.convertToTaskDto(task);
    }


    @PatchMapping("/task/{id}")
    public TaskDto updateTask(@PathVariable int id, @RequestBody @Valid TaskDto taskDto) {
        Task updatedTask = taskService.update(id, taskMapper.convertToTask(taskDto));
        return taskMapper.convertToTaskDto(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.delete(id);
    }

    @PostMapping("/task")
    public TaskDto createTask(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskMapper.convertToTask(taskDto);
        if (taskDto.getOwnerId()==0){
            task.setOwner(null);
        }
        Task createdTask = taskService.updateStatus(task);
        return taskMapper.convertToTaskDto(createdTask);
    }
}
