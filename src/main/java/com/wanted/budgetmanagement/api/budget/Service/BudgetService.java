package com.wanted.budgetmanagement.api.budget.Service;

import com.wanted.budgetmanagement.domain.category.entity.Category;
import com.wanted.budgetmanagement.domain.category.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategoryNames() {
        return categoryRepository.findAll();
    }
}
