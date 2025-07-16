package com.ecommerce.sm_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.sm_ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
