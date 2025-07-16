package com.ecommerce.sm_ecommerce.service;

import com.ecommerce.sm_ecommerce.model.Category;
import com.ecommerce.sm_ecommerce.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        // Let JPA handle the ID generation since we have @GeneratedValue in the entity
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public String deleteCategory(Long categoryId) {
        try {
            // First check if the category exists
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId));
            
            // Delete the category
            categoryRepository.delete(category);
            categoryRepository.flush(); // Force the delete operation to execute immediately
            
            return "Category with categoryId: " + categoryId + " deleted successfully !!";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error deleting category with id: " + categoryId, e);
        }
    }

    @Override
    @Transactional
    public String updateCategory(Long categoryId, Category category) {
        // Find the existing category by ID
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Category not found with id: " + categoryId));
        
        // Update the category name
        existingCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existingCategory);
        return "Category with categoryId: " + categoryId + " updated successfully !!";
    }
}
