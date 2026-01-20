package com.example.task.leaveReport.service.impl;

import com.example.task.leaveReport.dto.PageResponseDto;
import com.example.task.leaveReport.dto.UserLeaveReportDto;
import com.example.task.leaveReport.entity.Leave;
import com.example.task.leaveReport.entity.User;
import com.example.task.leaveReport.entity.UserLeaveReport;
import com.example.task.leaveReport.exception.ResourceNotFoundException;
import com.example.task.leaveReport.repository.LeaveRepository;
import com.example.task.leaveReport.repository.UserLeaveReportRepository;
import com.example.task.leaveReport.repository.UserRepository;
import com.example.task.leaveReport.service.LeaveReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveReportServiceImpl implements LeaveReportService {

    @Autowired
    private UserLeaveReportRepository userLeaveReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserLeaveReportDto createLeaveReport(UserLeaveReportDto leaveReportDto) {

        User user = userRepository.findById(leaveReportDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + leaveReportDto.getUserId()));

        Leave leave = leaveRepository.findById(leaveReportDto.getLeaveId())
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found with id " + leaveReportDto.getLeaveId()));

        UserLeaveReport report = new UserLeaveReport();
        report.setUser(user);
        report.setLeave(leave);
        report.setApplyDate(leaveReportDto.getApplyDate());
        report.setFromDate(leaveReportDto.getFromDate());
        report.setToDate(leaveReportDto.getToDate());
        report.setDescription(leaveReportDto.getDescription());

        UserLeaveReport savedReport = userLeaveReportRepository.save(report);

        UserLeaveReportDto responseDto = modelMapper.map(savedReport, UserLeaveReportDto.class);
        responseDto.setUserId(savedReport.getUser().getUserId());
        responseDto.setUserName(savedReport.getUser().getName());
        responseDto.setLeaveId(savedReport.getLeave().getId());
        responseDto.setLeaveName(savedReport.getLeave().getLeaveName());

        return responseDto;
    }

    @Override
    public PageResponseDto<UserLeaveReportDto> getAllLeaveReport(int pageNumber, int pageSize, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<UserLeaveReport> reportPage = userLeaveReportRepository.findAll(pageable);

        List<UserLeaveReportDto> content =  reportPage.getContent()
                .stream()
                .map(report -> {
                    UserLeaveReportDto responseDto = modelMapper.map(report, UserLeaveReportDto.class);
                    responseDto.setUserId(report.getUser().getUserId());
                    responseDto.setUserName(report.getUser().getName());
                    responseDto.setLeaveId(report.getLeave().getId());
                    responseDto.setLeaveName(report.getLeave().getLeaveName());
                    return responseDto;
                })
                .collect(Collectors.toList());

        return new PageResponseDto<>(
                content,
                reportPage.isEmpty(),
                reportPage.isFirst(),
                reportPage.isLast(),
                reportPage.getNumber(),
                reportPage.getSize(),
                reportPage.getTotalElements(),
                reportPage.getTotalPages()
                );
    }

    @Override
    public UserLeaveReportDto getLeaveReportById(Long id) {

        UserLeaveReport report = userLeaveReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave report not found with id " + id));

        UserLeaveReportDto dto = modelMapper.map(report, UserLeaveReportDto.class);
        dto.setUserId(report.getUser().getUserId());
        dto.setUserName(report.getUser().getName());
        dto.setLeaveId(report.getLeave().getId());
        dto.setLeaveName(report.getLeave().getLeaveName());

        return dto;
    }

    @Override
    public PageResponseDto<UserLeaveReportDto> getLeaveReportByUserId(Long userId, int pageNumber, int pageSize, String sortBy, String sortDirection) {

        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<UserLeaveReport> reportPage = userLeaveReportRepository.findByUser_UserId( userId, pageable);

        List<UserLeaveReportDto> content =  reportPage.getContent()
                .stream()
                .map(report -> {
                    UserLeaveReportDto responseDto = modelMapper.map(report, UserLeaveReportDto.class);
                    responseDto.setUserId(report.getUser().getUserId());
                    responseDto.setUserName(report.getUser().getName());
                    responseDto.setLeaveId(report.getLeave().getId());
                    responseDto.setLeaveName(report.getLeave().getLeaveName());
                    return responseDto;
                })
                .collect(Collectors.toList());

        return new PageResponseDto<>(
                content,
                reportPage.isEmpty(),
                reportPage.isFirst(),
                reportPage.isLast(),
                reportPage.getNumber(),
                reportPage.getSize(),
                reportPage.getTotalElements(),
                reportPage.getTotalPages()
        );

    }

    @Override
    public PageResponseDto<UserLeaveReportDto> getLeaveReportByLeaveType(Long leaveId, int pageNumber, int pageSize, String sortBy, String sortDirection) {

        if(!leaveRepository.existsById(leaveId)){
            throw new ResourceNotFoundException("Leave not found with id: " + leaveId);
        }

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<UserLeaveReport> reportPage = userLeaveReportRepository.findByLeave_Id( leaveId, pageable);

        List<UserLeaveReportDto> content =  reportPage.getContent()
                .stream()
                .map(report -> {
                    UserLeaveReportDto responseDto = modelMapper.map(report, UserLeaveReportDto.class);
                    responseDto.setUserId(report.getUser().getUserId());
                    responseDto.setUserName(report.getUser().getName());
                    responseDto.setLeaveId(report.getLeave().getId());
                    responseDto.setLeaveName(report.getLeave().getLeaveName());
                    return responseDto;
                })
                .collect(Collectors.toList());

        return new PageResponseDto<>(
                content,
                reportPage.isEmpty(),
                reportPage.isFirst(),
                reportPage.isLast(),
                reportPage.getNumber(),
                reportPage.getSize(),
                reportPage.getTotalElements(),
                reportPage.getTotalPages()
        );
    }

    @Override
    public UserLeaveReportDto updateLeaveReport(Long id, UserLeaveReportDto leaveReportDto) {

        UserLeaveReport existingReport = userLeaveReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave report not found with id " + id));

        if(leaveReportDto.getUserId() != null){
            User user = userRepository.findById(leaveReportDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + leaveReportDto.getUserId()));
            existingReport.setUser(user);
        }

        if(leaveReportDto.getLeaveId() != null){
            Leave leave = leaveRepository.findById(leaveReportDto.getLeaveId())
                    .orElseThrow(() -> new ResourceNotFoundException("Leave report not found with id " + leaveReportDto.getLeaveId()));
            existingReport.setLeave(leave);
        }

        if(leaveReportDto.getApplyDate() != null){
            existingReport.setApplyDate(leaveReportDto.getApplyDate());
        }

        if(leaveReportDto.getFromDate() != null){
            existingReport.setFromDate(leaveReportDto.getFromDate());
        }

        if(leaveReportDto.getToDate() != null){
            existingReport.setToDate(leaveReportDto.getToDate());
        }

        if(leaveReportDto.getDescription() != null){
            existingReport.setDescription(leaveReportDto.getDescription());
        }

        UserLeaveReport updatedReport = userLeaveReportRepository.save(existingReport);

        UserLeaveReportDto responseDto = modelMapper.map(updatedReport, UserLeaveReportDto.class);
        responseDto.setUserId(updatedReport.getUser().getUserId());
        responseDto.setUserName(updatedReport.getUser().getName());
        responseDto.setLeaveId(updatedReport.getLeave().getId());
        responseDto.setLeaveName(updatedReport.getLeave().getLeaveName());

        return responseDto;
    }

    @Override
    public void deleteLeaveReport(Long id) {

        UserLeaveReport report = userLeaveReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave report not found with id " + id));

        userLeaveReportRepository.delete(report);
    }

}


















//    @Override
//    public List<UserLeaveReportDto> getAllLeaveReport() {
//        return userLeaveReportRepository.findAll()
//                .stream()
//                .map(report -> {
//                    UserLeaveReportDto responseDto = modelMapper.map(report, UserLeaveReportDto.class);
//                    responseDto.setUserId(report.getUser().getUserId());
//                    responseDto.setUserName(report.getUser().getName());
//                    responseDto.setLeaveId(report.getLeave().getId());
//                    responseDto.setLeaveName(report.getLeave().getLeaveName());
//                    return responseDto;
//                })
//                .collect(Collectors.toList());
//    }



//    @Override
//    public List<UserLeaveReportDto> getLeaveReportByUserId(Long userId) {
//
//        if(!userRepository.existsById(userId)){
//            throw new ResourceNotFoundException("User not found with id: " + userId);
//        }
//
//        return userLeaveReportRepository.findByUser_UserId(userId)
//                .stream()
//                .map(report -> {
//                    UserLeaveReportDto responseDto = modelMapper.map(report, UserLeaveReportDto.class);
//                    responseDto.setUserId(report.getUser().getUserId());
//                    responseDto.setUserName(report.getUser().getName());
//                    responseDto.setLeaveId(report.getLeave().getId());
//                    responseDto.setLeaveName(report.getLeave().getLeaveName());
//                    return responseDto;
//                })
//                .collect(Collectors.toList());
//    }

//    @Override
//    public List<UserLeaveReportDto> getLeaveReportByLeaveType(Long leaveId) {
//
//        if(! leaveRepository.existsById(leaveId)){
//            throw new ResourceNotFoundException("Leave report not found with id: " + leaveId);
//        }
//
//        return userLeaveReportRepository.findByLeave_Id(leaveId)
//                .stream()
//                .map(report -> {
//                    UserLeaveReportDto responseDto = modelMapper.map(report, UserLeaveReportDto.class);
//                    responseDto.setUserId(report.getUser().getUserId());
//                    responseDto.setUserName(report.getUser().getName());
//                    responseDto.setLeaveId(report.getLeave().getId());
//                    responseDto.setLeaveName(report.getLeave().getLeaveName());
//                    return responseDto;
//                })
//                .collect(Collectors.toList());
//    }

