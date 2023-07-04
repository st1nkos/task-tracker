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


    @Transactional(readOnly = true)
    public Task get(int id) {
        return taskRepository.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> findAllByUserId(int id) {

        return taskRepository.findAllByOwnerId(id);
    }

    @Transactional
    public Task update(int id, Task task) {
        if (task.getStatus()==null){
            task.setStatus(StatusEnum.TO_DO.getName());
        }
        task.setId(id);
        task.setOwner(task.getOwner());
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(int id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task saveTask(Task task) {
        if (task.getStatus()==null){
            task.setStatus(StatusEnum.TO_DO.getName());
        }
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateStatus(Task task) {
        task.setStatus(task.getStatus());
        return taskRepository.save(task);
    }
}
