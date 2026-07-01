
package com.example.employeeManagement.repository;

import com.example.employeeManagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

        List<Attendance> findByStatus(String status);

        List<Attendance> findByDate(LocalDate date);

        List<Attendance> findByEmployee_Id(Integer employeeId);
    }
