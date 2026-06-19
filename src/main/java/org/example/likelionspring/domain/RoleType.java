package org.example.likelionspring.domain;

public enum RoleType {
    LION("아기사자"),
    STAFF("운영진");

    private String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
