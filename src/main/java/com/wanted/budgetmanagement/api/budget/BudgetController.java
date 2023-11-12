package com.wanted.budgetmanagement.api.budget;

import com.wanted.budgetmanagement.api.budget.Service.BudgetService;
import com.wanted.budgetmanagement.domain.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/category")
    public List<Category> getAllCategory() {
        return budgetService.getAllCategoryNames();
    }
}
