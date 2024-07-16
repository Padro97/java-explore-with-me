package ru.practicum.admins.compilationsAdmin.service;

import ru.practicum.dtos.compilationsDto.CompilationDto;
import ru.practicum.dtos.compilationsDto.NewCompilationDto;
import ru.practicum.dtos.compilationsDto.UpdateCompilationDto;

public interface AdminCompilationService {
    CompilationDto save(NewCompilationDto newCompilationDto);

    void delete(long id);

    CompilationDto update(long id, UpdateCompilationDto updateCompilationDto);
}
