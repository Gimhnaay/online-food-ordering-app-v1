package org.example.service.impl;

import org.example.model.Category;
import org.example.model.Restaurant;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.example.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant =restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantByID(id);
        return categoryRepository.findByRestaurantId(id);
    }
//@Override
//public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
//    return categoryRepository.findByRestaurantId(id);
//}


    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new Exception("category is not found");
        }
        return optionalCategory.get();
    }
}
