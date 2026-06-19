package org.example.likelionspring.dto;

import org.example.likelionspring.domain.Member;

public class MemberResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;
    private String position;

    private MemberResponse(Member member){
        this.name = member.getName();
        this.major = member.getMajor();
        this.generation = member.getGeneration();
        this.part = member.getPart();
        this.studentId = member.getStudentId();
        this.position = member.getPosition();
    }

    public static MemberResponse from(Member member){
        return new MemberResponse(member);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
