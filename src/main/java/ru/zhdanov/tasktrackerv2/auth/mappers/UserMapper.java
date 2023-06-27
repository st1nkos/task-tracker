package ru.zhdanov.tasktrackerv2.auth.mappers;

import org.mapstruct.Mapper;
import ru.zhdanov.tasktrackerv2.auth.dto.UserDto;
import ru.zhdanov.tasktrackerv2.auth.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
