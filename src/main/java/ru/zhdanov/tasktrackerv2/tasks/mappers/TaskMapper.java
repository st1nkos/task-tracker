package ru.zhdanov.tasktrackerv2.tasks.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zhdanov.tasktrackerv2.tasks.dto.TaskDto;
import ru.zhdanov.tasktrackerv2.tasks.entity.Task;

@Component
public class TaskMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public TaskDto convertToTaskDto(Task task) {
        return modelMapper.map(task,TaskDto.class);
    }

    public Task convertToTask(TaskDto taskDto){
        return modelMapper.map(taskDto,Task.class);
    }
}
