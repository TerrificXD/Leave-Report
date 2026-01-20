package com.example.task.leaveReport.service.impl;

import com.example.task.leaveReport.dto.LeaveDto;
import com.example.task.leaveReport.entity.Leave;
import com.example.task.leaveReport.exception.DuplicateResourceException;
import com.example.task.leaveReport.exception.ResourceNotFoundException;
import com.example.task.leaveReport.repository.LeaveRepository;
import com.example.task.leaveReport.service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LeaveDto createLeave(LeaveDto leaveDto) {

        if(leaveRepository.findByLeaveName(leaveDto.getLeaveName()).isPresent()){
            throw new DuplicateResourceException("Leave with name " + leaveDto.getLeaveName() + " already exists");
        }

        Leave leave = modelMapper.map(leaveDto, Leave.class);

        Leave savedLeave = leaveRepository.save(leave);

        return modelMapper.map(savedLeave, LeaveDto.class);
    }

    @Override
    public LeaveDto getLeaveByID(Long id) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found with id: " + id));

        return modelMapper.map(leave, LeaveDto.class);
    }

    @Override
    public List<LeaveDto> getAllLeaves() {
        return leaveRepository.findAll()
                .stream()
                .map(leave -> modelMapper.map(leave, LeaveDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LeaveDto updateLeave(Long id, LeaveDto leaveDto) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found with id: " + id));

        leaveRepository.findByLeaveName(leaveDto.getLeaveName())
                .ifPresent(existingLeave -> {
                    if(!existingLeave.getId().equals(id)){
                        throw new DuplicateResourceException("Leave with name " + leaveDto.getLeaveName() + " already exists");
                    }
                });

        leave.setLeaveName(leaveDto.getLeaveName());

        Leave updatedLeave = leaveRepository.save(leave);

        return modelMapper.map(updatedLeave, LeaveDto.class);

    }

    @Override
    public void deleteLeave(Long id) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found with id: " + id));

        leaveRepository.delete(leave);
    }

    @Override
    public void initializeLeaveTypes() {
        String[] leaveTypes = {
                "Casual Leave",
                "Compensatory Leave",
                "Leave without Pay",
                "Medical Leave",
                "Privilege Leave",
                "Probationary Leave",
                "Special Leave"
        };

        for(String typesOfLeaves : leaveTypes){
            if(leaveRepository.findByLeaveName(typesOfLeaves).isEmpty()){
                Leave leave = new Leave(typesOfLeaves);
                leaveRepository.save(leave);
            }
        }
    }
}
