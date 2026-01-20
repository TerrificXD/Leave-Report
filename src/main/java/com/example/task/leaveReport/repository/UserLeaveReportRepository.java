package com.example.task.leaveReport.repository;

import com.example.task.leaveReport.entity.UserLeaveReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLeaveReportRepository extends JpaRepository<UserLeaveReport, Long> {

    List<UserLeaveReport> findByUser_UserId(Long userId);

    Page<UserLeaveReport> findByUser_UserId(Long userId, Pageable pageable);

    List<UserLeaveReport> findByLeave_Id(Long leaveId);

    Page<UserLeaveReport> findByLeave_Id(Long leaveId, Pageable pageable);


}
