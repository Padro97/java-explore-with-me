package ru.practicum.utils;

import ru.practicum.models.Category;
import ru.practicum.repositories.CategoryRepository;
import ru.practicum.models.Compilation;
import ru.practicum.repositories.CompilationRepository;
import ru.practicum.dtos.eventsDto.UpdateEventDto;
import ru.practicum.models.Event;
import ru.practicum.repositories.EventRepository;
import ru.practicum.exceptions.IncorrectRequestException;
import ru.practicum.exceptions.NotFoundException;
import ru.practicum.dtos.locationsDto.LocationMapper;
import ru.practicum.models.Location;
import ru.practicum.repositories.LocationRepository;
import ru.practicum.models.User;
import ru.practicum.repositories.UserRepository;

import java.time.LocalDateTime;

public class GeneralMethods {
    public static void updateEventCommonFields(Event event, UpdateEventDto updateEventDto, int hoursCount,
                                               CategoryRepository categoryRepository, LocationRepository locationRepository, LocationMapper locationMapper) {
        LocalDateTime eventDate = updateEventDto.getEventDate();
        if (eventDate != null) {
            checkDateTime(eventDate, hoursCount);
            event.setEventDate(eventDate);
        }

        if (updateEventDto.getAnnotation() != null && !updateEventDto.getAnnotation().isBlank()) {
            event.setAnnotation(updateEventDto.getAnnotation());
        }
        if (updateEventDto.getCategory() != null) {
            Category category = categoryRepository.findById(updateEventDto.getCategory())
                    .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found", updateEventDto.getCategory())));
            event.setCategory(category);
        }
        if (updateEventDto.getDescription() != null && !updateEventDto.getDescription().isBlank()) {
            event.setDescription(updateEventDto.getDescription());
        }
        if (updateEventDto.getLocation() != null) {
            Location location = locationRepository.save(locationMapper.toLocation(updateEventDto.getLocation()));
            event.setLocation(location);
        }
        if (updateEventDto.getPaid() != null) {
            event.setPaid(updateEventDto.getPaid());
        }
        if (updateEventDto.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEventDto.getParticipantLimit());
        }
        if (updateEventDto.getRequestModeration() != null) {
            event.setRequestModeration(updateEventDto.getRequestModeration());
        }
        if (updateEventDto.getTitle() != null && !updateEventDto.getTitle().isBlank()) {
            event.setTitle(updateEventDto.getTitle());
        }
    }

    public static void checkDateTime(LocalDateTime dateTime, int hoursCount) {
        if (dateTime.isBefore(LocalDateTime.now().plusHours(hoursCount))) {
            throw new IncorrectRequestException("Field: eventDate. " +
                    "Error: должно содержать дату, которая еще не наступила. " +
                    "Value: " + dateTime);
        }
    }

    public static User findUser(long userId, UserRepository userRepository) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d was not found", userId)));
    }

    public static Event findEvent(long id, EventRepository eventRepository) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Event with id=%d was not found", id)));
    }

    public static Category findCategory(long id, CategoryRepository categoryRepository) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found", id)));
    }

    public static Compilation findCompilation(long id, CompilationRepository compilationRepository) {
        return compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Compilation with id=%s was not found", id)));
    }
}
