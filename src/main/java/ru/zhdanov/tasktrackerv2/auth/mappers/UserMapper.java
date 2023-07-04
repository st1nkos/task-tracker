package ru.zhdanov.tasktrackerv2.auth.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zhdanov.tasktrackerv2.auth.dto.UserDto;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.tasks.dto.TaskDto;
import ru.zhdanov.tasktrackerv2.tasks.entity.Task;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user,UserDto.class);
    }

    public User convertToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
}
