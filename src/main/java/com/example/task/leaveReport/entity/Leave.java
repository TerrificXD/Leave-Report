package com.example.task.leaveReport.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "leave_name", nullable = false, unique = true)
    private String leaveName;

    @OneToMany(mappedBy = "leave", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLeaveReport> userLeaveReports = new ArrayList<>();

    public Leave() {

    }

    public Leave(String leaveName) {
        this.leaveName = leaveName;
    }

    public List<UserLeaveReport> getUserLeaveReports() {
        return userLeaveReports;
    }

    public void setUserLeaveReports(List<UserLeaveReport> userLeaveReports) {
        this.userLeaveReports = userLeaveReports;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
