package ru.practicum.admins.compilationsAdmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dtos.compilationsDto.CompilationDto;
import ru.practicum.dtos.compilationsDto.CompilationMapper;
import ru.practicum.dtos.compilationsDto.NewCompilationDto;
import ru.practicum.dtos.compilationsDto.UpdateCompilationDto;
import ru.practicum.models.Compilation;
import ru.practicum.repositories.CompilationRepository;
import ru.practicum.models.Event;
import ru.practicum.repositories.EventRepository;
import ru.practicum.exceptions.NotFoundException;
import ru.practicum.utils.GeneralMethods;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    private final CompilationMapper compilationMapper;

    @Override
    public CompilationDto save(NewCompilationDto newCompilationDto) {
        Set<Event> events = new HashSet<>();
        if (newCompilationDto.getEvents() != null) {
            events = findEventsByIds(newCompilationDto.getEvents());
        }
        return compilationMapper
                .toCompilationDto(compilationRepository
                        .save(compilationMapper.toCompilation(newCompilationDto, events)));
    }

    @Override
    public void delete(long id) {
        GeneralMethods.findCompilation(id, compilationRepository);
        compilationRepository.deleteById(id);
    }

    @Override
    public CompilationDto update(long id, UpdateCompilationDto updateCompilationDto) {
        Compilation compilation = GeneralMethods.findCompilation(id, compilationRepository);
        if (updateCompilationDto.getEvents() != null && !updateCompilationDto.getEvents().isEmpty()) {
            Set<Event> events = findEventsByIds(updateCompilationDto.getEvents());
            compilation.setEvents(events);
        }
        if (updateCompilationDto.getPinned() != null) {
            compilation.setPinned(updateCompilationDto.getPinned());
        }
        if (updateCompilationDto.getTitle() != null && !updateCompilationDto.getTitle().isBlank()) {
            compilation.setTitle(updateCompilationDto.getTitle());
        }
        return compilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    private Set<Event> findEventsByIds(Set<Long> eventsIds) {
        return eventsIds.stream()
                .map(eventId -> eventRepository.findById(eventId)
                        .orElseThrow(() -> new NotFoundException(String.format("Event with id=%s was not found", eventId))))
                .collect(Collectors.toSet());
    }
}