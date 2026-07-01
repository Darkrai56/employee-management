package com.example.employeeManagement.repository;

import com.example.employeeManagement.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    List<Leave> findByLeaveType(String leaveType);

    List<Leave> findByStatus(String status);

    List<Leave> findByEmployee_Id(Integer employeeId);

    List<Leave> findByFromDate(LocalDate fromDate);

    List<Leave> findByToDate(LocalDate toDate);

    List<Leave> findByReasonContainingIgnoreCase(String reason);
}