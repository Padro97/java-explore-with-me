package ru.practicum.admins.compilationsAdmin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.admins.compilationsAdmin.service.AdminCompilationService;
import ru.practicum.dtos.compilationsDto.CompilationDto;
import ru.practicum.dtos.compilationsDto.NewCompilationDto;
import ru.practicum.dtos.compilationsDto.UpdateCompilationDto;
import ru.practicum.utils.PathConstants;

import javax.validation.Valid;

@RestController
@RequestMapping(PathConstants.ADMIN + PathConstants.COMPILATIONS)
@RequiredArgsConstructor
@Slf4j
public class AdminCompilationController {
    private final AdminCompilationService adminCompilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto save(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        log.info("Сохранение новой компиляции {}", newCompilationDto);
        return adminCompilationService.save(newCompilationDto);
    }

    @DeleteMapping(PathConstants.BY_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        log.info("Удаление компиляции с id {}", id);
        adminCompilationService.delete(id);
    }

    @PatchMapping(PathConstants.BY_ID)
    public CompilationDto update(@PathVariable long id, @Valid @RequestBody UpdateCompilationDto updateCompilationDto) {
        log.info("Обновление компиляции с id {}", id);
        return adminCompilationService.update(id, updateCompilationDto);
    }
}
