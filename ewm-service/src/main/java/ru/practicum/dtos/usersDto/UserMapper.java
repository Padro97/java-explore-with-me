package ru.practicum.dtos.usersDto;

import org.mapstruct.Mapper;
import ru.practicum.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(NewUserDto userDto);
}
