package org.example.likelionspring.member.domain;

import jakarta.persistence.*;
import org.example.likelionspring.assignment.domain.Assignment;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private String part;
    private int generation;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String studentId;
    private String position;

    @OneToMany(mappedBy = "member")
    private List<Assignment> assignments = new ArrayList<>();

    protected Member() {
    }

    public Member(String name, String major, String part, int generation, RoleType roleType, String studentId, String position) {
        this.name = name;
        this.major = major;
        this.part = part;
        this.generation = generation;
        this.roleType = roleType;
        this.studentId = studentId;
        this.position = position;
    }

    public void updateInfo(String major, int generation, String part){
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public void updateStudentId(String studentId){
        this.studentId = studentId;
    }

    public void updatePosition(String position){
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getPart() {
        return part;
    }

    public int getGeneration() {
        return generation;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPosition() {
        return position;
    }
}
