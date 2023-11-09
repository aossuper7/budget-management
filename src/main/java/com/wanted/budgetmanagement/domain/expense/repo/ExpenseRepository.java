package com.wanted.budgetmanagement.domain.expense.repo;

import com.wanted.budgetmanagement.domain.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
