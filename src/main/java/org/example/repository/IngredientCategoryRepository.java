package org.example.repository;

import org.example.model.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientsCategory,Long> {
    List<IngredientsCategory> findByRestaurantId(Long id);
}