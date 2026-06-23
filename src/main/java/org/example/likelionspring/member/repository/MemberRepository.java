package org.example.likelionspring.member.repository;

import org.example.likelionspring.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    Boolean existsByName(String name);
}