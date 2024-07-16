package ru.practicum.publics.categoriesPublics.service;

import ru.practicum.dtos.categoriesDto.CategoryDto;
import ru.practicum.utils.PageParams;

import java.util.List;

public interface PublicCategoryService {
    List<CategoryDto> getAll(PageParams pageParams);

    CategoryDto getById(long id);
}
