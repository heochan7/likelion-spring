package org.example.likelionspring.assignment.dto;

import org.example.likelionspring.assignment.domain.Assignment;

public class AssignmentResponse {
    private Long id;
    private String title;
    private String description;
    private Long memberId;
    private String memberName;

    private AssignmentResponse(Assignment assignment) {
        this.id = assignment.getId();
        this.title = assignment.getTitle();
        this.description = assignment.getDescription();
        this.memberId = assignment.getMember().getId();
        this.memberName = assignment.getMember().getName();
    }

    public static AssignmentResponse from(Assignment assignment){
        return new AssignmentResponse(assignment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
