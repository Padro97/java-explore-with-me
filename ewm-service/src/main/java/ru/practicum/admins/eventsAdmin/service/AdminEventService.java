package ru.practicum.admins.eventsAdmin.service;

import ru.practicum.dtos.eventsDto.EventFullDto;
import ru.practicum.dtos.eventsDto.GetEventAdminRequest;
import ru.practicum.dtos.eventsDto.UpdateEventAdminRequest;
import ru.practicum.utils.PageParams;

import java.util.List;

public interface AdminEventService {
    List<EventFullDto> getAll(GetEventAdminRequest request, PageParams pageParams);

    EventFullDto update(long id, UpdateEventAdminRequest request);
}
