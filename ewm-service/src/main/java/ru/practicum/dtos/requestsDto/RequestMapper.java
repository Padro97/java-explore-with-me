package ru.practicum.dtos.requestsDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.models.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(target = "requester", source = "request.requester.id")
    @Mapping(target = "event", source = "request.event.id")
    ParticipationRequestDto toRequestDto(Request request);
}
