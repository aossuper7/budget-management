package com.wanted.budgetmanagement.domain.member.entity;

import com.wanted.budgetmanagement.domain.budget.entity.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String refreshToken;

    @OneToMany(mappedBy = "member")
    List<Budget> budgetList = new ArrayList<>();
}
