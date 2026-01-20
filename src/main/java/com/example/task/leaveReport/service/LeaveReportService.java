package com.example.task.leaveReport.service;

import com.example.task.leaveReport.dto.PageResponseDto;
import com.example.task.leaveReport.dto.UserLeaveReportDto;

import java.util.List;

public interface LeaveReportService {

    UserLeaveReportDto createLeaveReport(UserLeaveReportDto leaveReportDto);

    PageResponseDto<UserLeaveReportDto> getAllLeaveReport(int pageNumber, int pageSize, String sortBy, String sortDirection);

    UserLeaveReportDto getLeaveReportById(Long id);

    PageResponseDto<UserLeaveReportDto> getLeaveReportByUserId(Long userId, int pageNumber, int pageSize, String sortBy, String sortDirection);

    PageResponseDto<UserLeaveReportDto> getLeaveReportByLeaveType(Long leaveId, int pageNumber, int pageSize, String sortBy, String sortDirection);

    UserLeaveReportDto updateLeaveReport(Long id, UserLeaveReportDto leaveReportDto);

    void deleteLeaveReport(Long id);

}
