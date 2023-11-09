package com.wanted.budgetmanagement.domain.member.entity;

import com.wanted.budgetmanagement.domain.budget.entity.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String refreshToken;

    @OneToMany(mappedBy = "member")
    List<Budget> budgetList = new ArrayList<>();

    @Builder
    public Member(String email, String password, String refreshToken) {
        this.email = email;
        this.password = password;
        this.refreshToken = refreshToken;
    }
}
