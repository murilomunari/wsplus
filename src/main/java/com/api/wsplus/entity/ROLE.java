package com.api.wsplus.entity;

public enum ROLE {
    ADMIN("admin"),
    SELLER("seller"),
    USER("user");

    private String role;

    ROLE(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
