package com.example.task.leaveReport.service;

import com.example.task.leaveReport.dto.LeaveDto;
import com.example.task.leaveReport.entity.Leave;

import java.util.List;

public interface LeaveService {

    LeaveDto createLeave(LeaveDto leaveDto);

    LeaveDto getLeaveByID(Long id);

    List<LeaveDto> getAllLeaves();

    LeaveDto updateLeave(Long i, LeaveDto leaveDto);

    void deleteLeave(Long id);

    void initializeLeaveTypes();

}
