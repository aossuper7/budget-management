package com.wanted.budgetmanagement.api.member.service;

import com.wanted.budgetmanagement.api.member.dto.SignUpDto;
import com.wanted.budgetmanagement.api.member.dto.TokenDto;
import com.wanted.budgetmanagement.domain.member.entity.Member;
import com.wanted.budgetmanagement.domain.member.repo.MemberRepository;
import com.wanted.budgetmanagement.global.exception.ErrorCode;
import com.wanted.budgetmanagement.global.exception.ErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(SignUpDto signUpDto) {
        existsByEmail(signUpDto.getEmail());

        Member member = Member.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        memberRepository.save(member);
    }

    private void existsByEmail(String email) {
        if (memberRepository.existsByEmail(email))
            throw new ErrorException(ErrorCode.EXIST_EMAIL);
    }
}
