package ru.practicum.publics.eventsPublics.service;

import ru.practicum.dtos.eventsDto.EventFullDto;
import ru.practicum.dtos.eventsDto.EventShortDto;
import ru.practicum.dtos.eventsDto.GetEventsPublicRequest;
import ru.practicum.utils.PageParams;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PublicEventService {
    List<EventShortDto> getAll(GetEventsPublicRequest getPublicRequest, PageParams pageParams, HttpServletRequest request);

    EventFullDto getById(long id, HttpServletRequest request);
}
