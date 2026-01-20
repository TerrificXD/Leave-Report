package com.example.task.leaveReport.repository;

import com.example.task.leaveReport.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LeaveRepository extends JpaRepository<Leave, Long> {

    Optional<Leave> findByLeaveName(String leaveName);

}
