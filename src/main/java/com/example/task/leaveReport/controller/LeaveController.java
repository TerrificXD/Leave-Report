package com.example.task.leaveReport.controller;

import com.example.task.leaveReport.dto.LeaveDto;
import com.example.task.leaveReport.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;


    @PostMapping
    public ResponseEntity<LeaveDto> createLeave(@Valid @RequestBody LeaveDto request) {
        LeaveDto response = leaveService.createLeave(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveDto> getLeaveById(@PathVariable Long id) {
        LeaveDto response = leaveService.getLeaveByID(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LeaveDto>> getAllLeave(){
        List<LeaveDto> response = leaveService.getAllLeaves();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveDto> updateLeave(@PathVariable Long id, @Valid @RequestBody LeaveDto dto){
        LeaveDto updateLeave = leaveService.updateLeave(id, dto);
        return ResponseEntity.ok(updateLeave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id){
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }



//    @PostMapping("/initialize")
//    public ResponseEntity<String> initializeLeaveTypes() {
//        leaveService.initializeLeaveTypes();
//        return ResponseEntity.ok("Leave types initialized successfully");
//    }

}
