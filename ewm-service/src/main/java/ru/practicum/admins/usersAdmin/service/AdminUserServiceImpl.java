package ru.practicum.admins.usersAdmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.dtos.usersDto.NewUserDto;
import ru.practicum.dtos.usersDto.UserDto;
import ru.practicum.dtos.usersDto.UserMapper;
import ru.practicum.models.User;
import ru.practicum.repositories.UserRepository;
import ru.practicum.utils.GeneralMethods;
import ru.practicum.utils.PageParams;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> get(List<Long> ids, PageParams pageParams) {
        List<User> users;
        PageRequest page = pageParams.getPageRequest();
        if (ids.isEmpty()) {
            users = userRepository.findAll(page).getContent();
        } else {
            users = userRepository.findAllByIdIn(ids, page).getContent();
        }
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto save(NewUserDto userDto) {
        try {
            return userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }

    }

    @Override
    public void delete(long id) {
        GeneralMethods.findUser(id, userRepository);
        userRepository.deleteById(id);
    }
}