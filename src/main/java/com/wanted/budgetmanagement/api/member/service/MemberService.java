package com.wanted.budgetmanagement.api.member.service;

import com.wanted.budgetmanagement.api.member.dto.SignInUpDto;
import com.wanted.budgetmanagement.api.member.dto.TokenDto;
import com.wanted.budgetmanagement.domain.member.entity.Member;
import com.wanted.budgetmanagement.domain.member.repo.MemberRepository;
import com.wanted.budgetmanagement.global.exception.ErrorCode;
import com.wanted.budgetmanagement.global.exception.ErrorException;
import com.wanted.budgetmanagement.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * join Service
     * @param signInUpDto
     */
    @Transactional
    public void join(SignInUpDto signInUpDto) {
        existsByEmail(signInUpDto.getEmail());

        Member member = Member.builder()
                .email(signInUpDto.getEmail())
                .password(passwordEncoder.encode(signInUpDto.getPassword()))
                .build();
        memberRepository.save(member);
    }

    private void existsByEmail(String email) {
        if (memberRepository.existsByEmail(email))
            throw new ErrorException(ErrorCode.EXIST_EMAIL);
    }

    /**
     * login Service
     * @param signInUpDto
     * @return
     */
    @Transactional
    public TokenDto login(SignInUpDto signInUpDto) {
        Member member = findByEmail(signInUpDto.getEmail());
        validatePassword(signInUpDto.getPassword(), member.getPassword());
        member.setRefreshToken(jwtTokenProvider.createRefreshToken(signInUpDto.getEmail()));
        return createToken(signInUpDto.getEmail());
    }

    private Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new ErrorException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private void validatePassword(final String rawPassword, final String encodePassword) {
        if (!passwordEncoder.matches(rawPassword, encodePassword))
            throw new ErrorException(ErrorCode.INVALID_PWD);
    }

    private TokenDto createToken(String email) {
        return new TokenDto(
                jwtTokenProvider.createAccessToken(email),
                jwtTokenProvider.createRefreshToken(email)
        );
    }
}
