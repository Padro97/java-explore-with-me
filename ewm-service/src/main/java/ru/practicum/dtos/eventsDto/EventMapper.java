package ru.practicum.dtos.eventsDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.models.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "category.id", source = "newEventDto.category")
    Event toEvent(NewEventDto newEventDto);

    @Mapping(target = "confirmedRequests", source = "event.confirmedRequests")
    EventFullDto toEventFullDto(Event event);

    @Mapping(target = "confirmedRequests", source = "event.confirmedRequests")
    EventShortDto toEventShortDto(Event event);
}