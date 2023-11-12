package com.wanted.budgetmanagement.api.member;

import com.wanted.budgetmanagement.api.member.dto.SignUpDto;
import com.wanted.budgetmanagement.api.member.dto.TokenDto;
import com.wanted.budgetmanagement.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        memberService.join(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
