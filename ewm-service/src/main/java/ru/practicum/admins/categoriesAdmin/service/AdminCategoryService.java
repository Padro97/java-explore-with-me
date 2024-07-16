package ru.practicum.admins.categoriesAdmin.service;

import ru.practicum.dtos.categoriesDto.CategoryDto;
import ru.practicum.dtos.categoriesDto.NewCategoryDto;

public interface AdminCategoryService {
    CategoryDto save(NewCategoryDto newCategoryDto);

    void delete(long id);

    CategoryDto update(long id, NewCategoryDto newCategoryDto);
}
