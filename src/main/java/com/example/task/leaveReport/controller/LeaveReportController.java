package com.example.task.leaveReport.controller;

import com.example.task.leaveReport.dto.PageResponseDto;
import com.example.task.leaveReport.dto.UserLeaveReportDto;
import com.example.task.leaveReport.service.LeaveReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave-reports")
public class LeaveReportController {

    @Autowired
    private LeaveReportService leaveReportService;

    @PostMapping
    public ResponseEntity<UserLeaveReportDto> createLeaveReport(@Valid @RequestBody UserLeaveReportDto request) {
        UserLeaveReportDto response = leaveReportService.createLeaveReport(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLeaveReportDto> getLeaveReportById(@PathVariable Long id) {
        UserLeaveReportDto response = leaveReportService.getLeaveReportById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponseDto<UserLeaveReportDto>> getAllLeaveReport(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        PageResponseDto<UserLeaveReportDto> response = leaveReportService.getAllLeaveReport(pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leave-type/{leaveId}")
    public ResponseEntity<PageResponseDto<UserLeaveReportDto>> getLeaveReportsByLeaveType(
            @PathVariable Long leaveId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        PageResponseDto<UserLeaveReportDto> response = leaveReportService.getLeaveReportByLeaveType(leaveId, pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PageResponseDto<UserLeaveReportDto>> getLeaveReportByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        PageResponseDto<UserLeaveReportDto> response = leaveReportService.getLeaveReportByUserId(userId, pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLeaveReportDto> updateLeaveReport(@PathVariable Long id, @RequestBody UserLeaveReportDto dto){
        UserLeaveReportDto updateReport = leaveReportService.updateLeaveReport(id, dto);
        return ResponseEntity.ok(updateReport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeaveReport(@PathVariable Long id){
        leaveReportService.deleteLeaveReport(id);
        return new ResponseEntity<>("Leave report deleted successfully", HttpStatus.OK);
    }

}













//    @GetMapping
//    public ResponseEntity<List<UserLeaveReportDto>> getAllLeaveReport(){
//        List<UserLeaveReportDto> response = leaveReportService.getAllLeaveReport();
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/leave-type/{leaveId}")
//    public ResponseEntity<List<UserLeaveReportDto>> getLeaveReportsByLeaveType(@PathVariable Long leaveId){
//        List<UserLeaveReportDto> response = leaveReportService.getLeaveReportByLeaveType(leaveId);
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<UserLeaveReportDto>> getLeaveReportByUserId(@PathVariable Long userId){
//        List<UserLeaveReportDto> response = leaveReportService.getLeaveReportByUserId(userId);
//        return ResponseEntity.ok(response);
//    }
