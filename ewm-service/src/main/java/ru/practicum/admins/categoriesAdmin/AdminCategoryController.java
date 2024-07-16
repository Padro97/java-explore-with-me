package ru.practicum.admins.categoriesAdmin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.admins.categoriesAdmin.service.AdminCategoryService;
import ru.practicum.dtos.categoriesDto.CategoryDto;
import ru.practicum.dtos.categoriesDto.NewCategoryDto;
import ru.practicum.utils.PathConstants;

import javax.validation.Valid;

@RestController
@RequestMapping(PathConstants.ADMIN + PathConstants.CATEGORIES)
@RequiredArgsConstructor
@Slf4j
public class AdminCategoryController {
    private final AdminCategoryService adminCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto save(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Сохранение новой категории {}", newCategoryDto);
        return adminCategoryService.save(newCategoryDto);
    }

    @DeleteMapping(PathConstants.BY_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        log.info("Удаление категории с id {}", id);
        adminCategoryService.delete(id);
    }

    @PatchMapping(PathConstants.BY_ID)
    public CategoryDto update(@PathVariable long id,
                              @Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Изменение названия категории с id {} на {}", id, newCategoryDto.getName());
        return adminCategoryService.update(id, newCategoryDto);
    }
}
