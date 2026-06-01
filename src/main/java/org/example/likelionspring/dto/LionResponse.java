package org.example.likelionspring.dto;

import org.example.likelionspring.domain.role.Lion;

public class LionResponse {
    //name, major, generation, part, roleName, studentId
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String studentId;

    private LionResponse(Lion lion) {
        this.name = lion.getName();
        this.major = lion.getMajor();
        this.generation = lion.getGeneration();
        this.part = lion.getPart();
        this.roleName = lion.roleName();
        this.studentId = lion.getStudentId();
    }

    public static LionResponse from(Lion lion) {
        return new LionResponse(lion);
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
