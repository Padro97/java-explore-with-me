package ru.practicum.privates.eventsPrivates.service;

import ru.practicum.dtos.eventsDto.EventFullDto;
import ru.practicum.dtos.eventsDto.EventShortDto;
import ru.practicum.dtos.eventsDto.NewEventDto;
import ru.practicum.dtos.eventsDto.UpdateEventUserRequest;
import ru.practicum.dtos.requestsDto.EventRequestStatusUpdate;
import ru.practicum.dtos.requestsDto.EventRequestStatusUpdateResult;
import ru.practicum.dtos.requestsDto.ParticipationRequestDto;
import ru.practicum.utils.PageParams;

import java.util.List;

public interface PrivateEventService {
    List<EventShortDto> getAll(long userId, PageParams pageParams);

    EventFullDto save(long userId, NewEventDto newEventDto);

    EventFullDto getById(long userId, long id);

    EventFullDto update(long userId, long id, UpdateEventUserRequest updateEvent);

    List<ParticipationRequestDto> getRequests(long userId, long id);

    EventRequestStatusUpdateResult updateStatusRequests(long userId, long id,
                                                        EventRequestStatusUpdate requestStatusUpdate);
}
