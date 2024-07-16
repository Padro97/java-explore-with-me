package ru.practicum.dtos.compilationsDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.models.Compilation;
import ru.practicum.models.Event;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CompilationMapper {
    @Mapping(target = "events", source = "newEvents")
    Compilation toCompilation(NewCompilationDto newCompilationDto, Set<Event> newEvents);

    CompilationDto toCompilationDto(Compilation compilation);

}
