package com.wanted.budgetmanagement.domain.category.repo;

import com.wanted.budgetmanagement.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
