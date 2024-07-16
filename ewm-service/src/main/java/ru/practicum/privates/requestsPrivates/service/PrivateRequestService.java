package ru.practicum.privates.requestsPrivates.service;

import ru.practicum.dtos.requestsDto.ParticipationRequestDto;

import java.util.List;

public interface PrivateRequestService {
    List<ParticipationRequestDto> getAll(long userId);

    ParticipationRequestDto save(long userId, long eventId);

    ParticipationRequestDto cancel(long userId, long id);
}
