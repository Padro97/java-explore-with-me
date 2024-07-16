package ru.practicum.admins.usersAdmin.service;

import ru.practicum.dtos.usersDto.NewUserDto;
import ru.practicum.dtos.usersDto.UserDto;
import ru.practicum.utils.PageParams;

import java.util.List;

public interface AdminUserService {
    List<UserDto> get(List<Long> ids, PageParams pageParams);

    UserDto save(NewUserDto userDto);

    void delete(long id);
}
