package com.wanted.budgetmanagement.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInUpDto {
    private String email;
    private String password;
}
