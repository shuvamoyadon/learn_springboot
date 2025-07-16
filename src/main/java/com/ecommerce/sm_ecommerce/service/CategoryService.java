package com.ecommerce.sm_ecommerce.service;

import com.ecommerce.sm_ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long ID);
    String updateCategory(Long ID, Category category);
}

