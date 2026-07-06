package org.example.likelionspring.assignment.repository;

import org.example.likelionspring.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByMemberId(Long memberId);

    List<Assignment> findByTitleContaining(String keyword);
}
