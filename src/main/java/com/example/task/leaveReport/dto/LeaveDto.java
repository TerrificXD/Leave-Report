package com.example.task.leaveReport.dto;

import jakarta.validation.constraints.NotBlank;

public class LeaveDto {

    private Long id;

    @NotBlank(message = "Leave name is required")
    private String leaveName;

    public LeaveDto() {
    }

    public LeaveDto(Long id, String leaveName) {
        this.id = id;
        this.leaveName = leaveName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

}
