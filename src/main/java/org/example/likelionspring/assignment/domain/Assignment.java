package org.example.likelionspring.assignment.domain;

import jakarta.persistence.*;
import org.example.likelionspring.member.domain.Member;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    protected Assignment(){}

    public Assignment(String title, String description, Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

    public void updateInfo(String title, String description){
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Member getMember() {
        return member;
    }
}
