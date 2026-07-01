package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Attendance;
import com.example.employeemanagement.model.AttendanceModel;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceModel addAttendance(Attendance attendance);

    List<AttendanceModel> getAllAttendance();

    AttendanceModel getAttendanceById(Integer id);

    AttendanceModel updateAttendance(Integer id, Attendance attendance);

    String deleteAttendance(Integer id);

    List<AttendanceModel> getAttendanceByStatus(String status);

    List<AttendanceModel> getAttendanceByDate(LocalDate date);

    List<AttendanceModel> getAttendanceByEmployee(Integer employeeId);
}