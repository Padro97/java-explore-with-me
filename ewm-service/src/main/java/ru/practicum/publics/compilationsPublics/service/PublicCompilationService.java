package ru.practicum.publics.compilationsPublics.service;

import ru.practicum.dtos.compilationsDto.CompilationDto;
import ru.practicum.utils.PageParams;

import java.util.List;

public interface PublicCompilationService {
    List<CompilationDto> getAll(Boolean pinned, PageParams pageParams);

    CompilationDto getById(long id);
}
