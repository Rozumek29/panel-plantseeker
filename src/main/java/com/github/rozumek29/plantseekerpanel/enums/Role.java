package com.github.rozumek29.plantseekerpanel.enums;

public enum Role {
    USER("user"), ADMIN("admin");

    private String roleName;

    private Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}
