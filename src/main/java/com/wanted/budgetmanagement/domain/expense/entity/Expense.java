package com.wanted.budgetmanagement.domain.expense.entity;

import com.wanted.budgetmanagement.domain.category.entity.Category;
import com.wanted.budgetmanagement.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
public class Expense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;
    private Long money;
    private LocalDateTime time;
    private String memo;
    private boolean isExcludedFromTotal;
}
