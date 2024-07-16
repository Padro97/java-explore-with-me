package ru.practicum.dtos.categoriesDto;

import org.mapstruct.Mapper;
import ru.practicum.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(NewCategoryDto newCategoryDto);

    CategoryDto toCategoryDto(Category category);
}
