package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.models.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select id from Category")
    List<Long> findAllId();
}