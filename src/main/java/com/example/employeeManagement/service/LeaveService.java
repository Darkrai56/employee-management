package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Leave;
import com.example.employeemanagement.model.LeaveModel;

import java.time.LocalDate;
import java.util.List;

public interface LeaveService {

    LeaveModel addLeave(Leave leave);

    List<LeaveModel> getAllLeaves();

    LeaveModel getLeaveById(Integer id);

    LeaveModel updateLeave(Integer id, Leave leave);

    String deleteLeave(Integer id);
    List<LeaveModel> getLeavesByType(String leaveType);

    List<LeaveModel> getLeavesByStatus(String status);

    List<LeaveModel> getLeavesByEmployee(Integer employeeId);

    List<LeaveModel> getLeavesByFromDate(LocalDate fromDate);

    List<LeaveModel> getLeavesByToDate(LocalDate toDate);

    List<LeaveModel> searchLeaveReason(String reason);
}