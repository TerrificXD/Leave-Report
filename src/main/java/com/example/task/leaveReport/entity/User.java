package com.example.task.leaveReport.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLeaveReport> leaveReports = new ArrayList<>();

    public User() {

    }

    public List<UserLeaveReport> getLeaveReports() {
        return leaveReports;
    }

    public void setLeaveReports(List<UserLeaveReport> leaveReports) {
        this.leaveReports = leaveReports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
