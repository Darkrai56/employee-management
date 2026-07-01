package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Attendance;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.repository.AttendanceRepository;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.AttendanceService;
import com.example.employeemanagement.model.AttendanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AttendanceModel addAttendance(Attendance attendance) {

        AttendanceModel attendanceModel = new AttendanceModel();

        try {

            if (attendance == null) {
                throw new RuntimeException("Attendance details cannot be null.");
            }

            if (attendance.getDate() == null) {
                throw new RuntimeException("Date is required.");
            }

            if (attendance.getCheckIn() == null) {
                throw new RuntimeException("Check In time is required.");
            }

            if (attendance.getCheckOut() == null) {
                throw new RuntimeException("Check Out time is required.");
            }

            if (attendance.getCheckIn().isAfter(attendance.getCheckOut())) {
                throw new RuntimeException("Check In cannot be after Check Out.");
            }

            if (attendance.getStatus() == null || attendance.getStatus().trim().isEmpty()) {
                throw new RuntimeException("Status is required.");
            }

            if (attendance.getEmployee() == null) {
                throw new RuntimeException("Employee is required.");
            }

            Optional<Employee> employeeOptional =
                    employeeRepository.findById(attendance.getEmployee().getId());

            if (employeeOptional.isEmpty()) {
                throw new RuntimeException("Employee not found.");
            }

            attendance.setEmployee(employeeOptional.get());

            Attendance savedAttendance = attendanceRepository.save(attendance);

            attendanceModel.setId(savedAttendance.getId());
            attendanceModel.setEmployeeId(savedAttendance.getEmployee().getId());
            attendanceModel.setDate(savedAttendance.getDate());
            attendanceModel.setCheckIn(savedAttendance.getCheckIn());
            attendanceModel.setCheckOut(savedAttendance.getCheckOut());
            attendanceModel.setStatus(savedAttendance.getStatus());

            return attendanceModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AttendanceModel> getAllAttendance() {

        List<AttendanceModel> attendanceModelList = new ArrayList<>();

        try {

            List<Attendance> attendanceList = attendanceRepository.findAll();

            for (Attendance attendance : attendanceList) {

                AttendanceModel attendanceModel = new AttendanceModel();

                attendanceModel.setId(attendance.getId());
                attendanceModel.setEmployeeId(attendance.getEmployee().getId());
                attendanceModel.setDate(attendance.getDate());
                attendanceModel.setCheckIn(attendance.getCheckIn());
                attendanceModel.setCheckOut(attendance.getCheckOut());
                attendanceModel.setStatus(attendance.getStatus());

                attendanceModelList.add(attendanceModel);
            }

            return attendanceModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public AttendanceModel getAttendanceById(Integer id) {

        AttendanceModel attendanceModel = new AttendanceModel();

        try {

            if (id == null) {
                throw new RuntimeException("Attendance ID cannot be null.");
            }

            Optional<Attendance> optionalAttendance =
                    attendanceRepository.findById(id);

            if (optionalAttendance.isEmpty()) {
                throw new RuntimeException("Attendance not found.");
            }

            Attendance attendance = optionalAttendance.get();

            attendanceModel.setId(attendance.getId());
            attendanceModel.setEmployeeId(attendance.getEmployee().getId());
            attendanceModel.setDate(attendance.getDate());
            attendanceModel.setCheckIn(attendance.getCheckIn());
            attendanceModel.setCheckOut(attendance.getCheckOut());
            attendanceModel.setStatus(attendance.getStatus());

            return attendanceModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public AttendanceModel updateAttendance(Integer id, Attendance attendance) {

        AttendanceModel attendanceModel = new AttendanceModel();

        try {

            if (id == null) {
                throw new RuntimeException("Attendance ID cannot be null.");
            }

            Optional<Attendance> optionalAttendance =
                    attendanceRepository.findById(id);

            if (optionalAttendance.isEmpty()) {
                throw new RuntimeException("Attendance not found.");
            }

            Attendance existingAttendance = optionalAttendance.get();

            if (attendance.getDate() == null) {
                throw new RuntimeException("Date is required.");
            }

            if (attendance.getCheckIn() == null) {
                throw new RuntimeException("Check In time is required.");
            }

            if (attendance.getCheckOut() == null) {
                throw new RuntimeException("Check Out time is required.");
            }

            if (attendance.getCheckIn().isAfter(attendance.getCheckOut())) {
                throw new RuntimeException("Check In cannot be after Check Out.");
            }

            if (attendance.getStatus() == null || attendance.getStatus().trim().isEmpty()) {
                throw new RuntimeException("Status is required.");
            }

            existingAttendance.setDate(attendance.getDate());
            existingAttendance.setCheckIn(attendance.getCheckIn());
            existingAttendance.setCheckOut(attendance.getCheckOut());
            existingAttendance.setStatus(attendance.getStatus());

            if (attendance.getEmployee() != null) {

                Optional<Employee> employeeOptional =
                        employeeRepository.findById(attendance.getEmployee().getId());

                if (employeeOptional.isEmpty()) {
                    throw new RuntimeException("Employee not found.");
                }

                existingAttendance.setEmployee(employeeOptional.get());
            }

            Attendance updatedAttendance = attendanceRepository.save(existingAttendance);

            attendanceModel.setId(updatedAttendance.getId());
            attendanceModel.setEmployeeId(updatedAttendance.getEmployee().getId());
            attendanceModel.setDate(updatedAttendance.getDate());
            attendanceModel.setCheckIn(updatedAttendance.getCheckIn());
            attendanceModel.setCheckOut(updatedAttendance.getCheckOut());
            attendanceModel.setStatus(updatedAttendance.getStatus());

            return attendanceModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteAttendance(Integer id) {

        try {

            if (id == null) {
                throw new RuntimeException("Attendance ID cannot be null.");
            }

            Optional<Attendance> optionalAttendance =
                    attendanceRepository.findById(id);

            if (optionalAttendance.isEmpty()) {
                throw new RuntimeException("Attendance not found.");
            }

            attendanceRepository.delete(optionalAttendance.get());

            return "Attendance deleted successfully.";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AttendanceModel> getAttendanceByStatus(String status) {

        List<AttendanceModel> attendanceModelList = new ArrayList<>();

        try {

            if (status == null || status.trim().isEmpty()) {
                throw new RuntimeException("Status cannot be empty.");
            }

            List<Attendance> attendanceList =
                    attendanceRepository.findByStatus(status);

            for (Attendance attendance : attendanceList) {

                AttendanceModel attendanceModel = new AttendanceModel();

                attendanceModel.setId(attendance.getId());
                attendanceModel.setEmployeeId(attendance.getEmployee().getId());
                attendanceModel.setDate(attendance.getDate());
                attendanceModel.setCheckIn(attendance.getCheckIn());
                attendanceModel.setCheckOut(attendance.getCheckOut());
                attendanceModel.setStatus(attendance.getStatus());

                attendanceModelList.add(attendanceModel);
            }

            return attendanceModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<AttendanceModel> getAttendanceByDate(LocalDate date) {

        List<AttendanceModel> attendanceModelList = new ArrayList<>();

        try {

            if (date == null) {
                throw new RuntimeException("Date cannot be null.");
            }

            List<Attendance> attendanceList =
                    attendanceRepository.findByDate(date);

            for (Attendance attendance : attendanceList) {

                AttendanceModel attendanceModel = new AttendanceModel();

                attendanceModel.setId(attendance.getId());
                attendanceModel.setEmployeeId(attendance.getEmployee().getId());
                attendanceModel.setDate(attendance.getDate());
                attendanceModel.setCheckIn(attendance.getCheckIn());
                attendanceModel.setCheckOut(attendance.getCheckOut());
                attendanceModel.setStatus(attendance.getStatus());

                attendanceModelList.add(attendanceModel);
            }

            return attendanceModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AttendanceModel> getAttendanceByEmployee(Integer employeeId) {

        List<AttendanceModel> attendanceModelList = new ArrayList<>();

        try {

            if (employeeId == null) {
                throw new RuntimeException("Employee ID cannot be null.");
            }

            Optional<Employee> employeeOptional =
                    employeeRepository.findById(employeeId);

            if (employeeOptional.isEmpty()) {
                throw new RuntimeException("Employee not found.");
            }

            List<Attendance> attendanceList =
                    attendanceRepository.findByEmployee_Id(employeeId);

            for (Attendance attendance : attendanceList) {

                AttendanceModel attendanceModel = new AttendanceModel();

                attendanceModel.setId(attendance.getId());
                attendanceModel.setEmployeeId(attendance.getEmployee().getId());
                attendanceModel.setDate(attendance.getDate());
                attendanceModel.setCheckIn(attendance.getCheckIn());
                attendanceModel.setCheckOut(attendance.getCheckOut());
                attendanceModel.setStatus(attendance.getStatus());

                attendanceModelList.add(attendanceModel);
            }

            return attendanceModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}