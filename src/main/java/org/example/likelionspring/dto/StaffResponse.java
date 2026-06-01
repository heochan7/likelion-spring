package org.example.likelionspring.dto;

import org.example.likelionspring.domain.role.Staff;

public class StaffResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String position;

    private StaffResponse(Staff staff) {
        this.name = staff.getName();
        this.major = staff.getMajor();
        this.generation = staff.getGeneration();
        this.part = staff.getPart();
        this.roleName = staff.roleName();
        this.position = staff.getPosition();
    }

    public static StaffResponse from(Staff staff) {
        return new StaffResponse(staff);
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
