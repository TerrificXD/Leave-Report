package com.example.task.leaveReport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class UserLeaveReportDto {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    private String userName;

    @NotNull(message = "Leave ID is required")
    private Long leaveId;

    private String leaveName;

    @NotNull(message = "Apply Date is required")
    @PastOrPresent(message = "Apply date cannot be in the future")
    private LocalDate applyDate;

    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    @NotBlank(message = "Description is required")
    private String description;

    public UserLeaveReportDto() {

    }

    public UserLeaveReportDto(LocalDate applyDate, String description, LocalDate fromDate, Long id, Long leaveId, String leaveName, LocalDate toDate, Long userId, String userName) {
        this.applyDate = applyDate;
        this.description = description;
        this.fromDate = fromDate;
        this.id = id;
        this.leaveId = leaveId;
        this.leaveName = leaveName;
        this.toDate = toDate;
        this.userId = userId;
        this.userName = userName;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
