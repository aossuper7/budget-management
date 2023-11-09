package com.wanted.budgetmanagement.domain.member.repo;

import com.wanted.budgetmanagement.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}