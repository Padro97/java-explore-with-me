package ru.practicum.admins.categoriesAdmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.practicum.dtos.categoriesDto.CategoryDto;
import ru.practicum.dtos.categoriesDto.CategoryMapper;
import ru.practicum.dtos.categoriesDto.NewCategoryDto;
import ru.practicum.models.Category;
import ru.practicum.repositories.CategoryRepository;
import ru.practicum.utils.GeneralMethods;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(NewCategoryDto newCategoryDto) {
        try {
            return categoryMapper.toCategoryDto(categoryRepository.save(categoryMapper.toCategory(newCategoryDto)));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public void delete(long id) {
        GeneralMethods.findCategory(id, categoryRepository);
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public CategoryDto update(long id, NewCategoryDto newCategoryDto) {
        Category category = GeneralMethods.findCategory(id, categoryRepository);
        try {
            category.setName(newCategoryDto.getName());
            return categoryMapper.toCategoryDto(categoryRepository.save(category));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }
}