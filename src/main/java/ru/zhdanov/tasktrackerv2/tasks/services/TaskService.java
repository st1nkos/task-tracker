package ru.zhdanov.tasktrackerv2.tasks.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.auth.services.UserService;
import ru.zhdanov.tasktrackerv2.tasks.entity.Task;
import ru.zhdanov.tasktrackerv2.tasks.enums.StatusEnum;
import ru.zhdanov.tasktrackerv2.tasks.repositories.TaskRepository;
import ru.zhdanov.tasktrackerv2.tasks.utils.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    @Transactional(readOnly = true)
    public Task get(int id) {
        return taskRepository.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }
    @Transactional(readOnly = true)
    public List<Task> findAllByUserId(int id) {

        return taskRepository.findAllByOwnerId(id);
    }

    @Transactional
    public Task save(int id, Task task) {
        if (task.getStatus()==null){
            task.setStatus(StatusEnum.TO_DO.getName());
        }
        Task updatedTask = taskRepository.getTaskById(id).orElseThrow(()-> new ResourceNotFoundException("task not found"));
        task.setId(id);
        task.setOwner(updatedTask.getOwner());
        return taskRepository.save(task);
    }
    @Transactional
    public void delete(Task task) {
        taskRepository.delete(task);
    }
    @Transactional
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task saveWithUserId(Task task, int id) {
        if (task.getStatus()==null){
            task.setStatus(StatusEnum.TO_DO.getName());
        }

        User user = userService.get(id);

        task.setOwner(user);

        return taskRepository.save(task);
    }

}
