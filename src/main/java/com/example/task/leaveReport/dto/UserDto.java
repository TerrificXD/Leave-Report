package com.example.task.leaveReport.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    private Long userId;

    @NotBlank(message = "user name is required")
    private String name;

    public UserDto() {

    }

    public UserDto(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
