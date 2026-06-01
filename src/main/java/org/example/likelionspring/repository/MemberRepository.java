package org.example.likelionspring.repository;

import org.example.likelionspring.domain.role.Role;

import java.util.List;

public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    List<Role> findAll();
    boolean existsByName(String name);
    void updateByName(String name, Role member);
    boolean deleteByName(String name);
}