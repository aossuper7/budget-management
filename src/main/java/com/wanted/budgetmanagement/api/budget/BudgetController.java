package com.wanted.budgetmanagement.api.budget;

import com.wanted.budgetmanagement.api.budget.Service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/category")
    public String getAllCategory() {
        return budgetService.getAllCategoryNames();
    }
}
