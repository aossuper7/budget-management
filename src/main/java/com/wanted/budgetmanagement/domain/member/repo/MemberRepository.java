package com.wanted.budgetmanagement.domain.member.repo;

import com.wanted.budgetmanagement.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email); //회원가입시 이메일 체크
    Optional<Member> findByEmail(String email);
}
