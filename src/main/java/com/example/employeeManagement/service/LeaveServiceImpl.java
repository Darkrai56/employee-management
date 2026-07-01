package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.Leave;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.repository.LeaveRepository;
import com.example.employeeManagement.service.LeaveService;
import com.example.employeemanagement.model.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public LeaveModel addLeave(Leave leave) {

        LeaveModel leaveModel = new LeaveModel();

        try {

            if (leave == null) {
                throw new RuntimeException("Leave details cannot be null.");
            }

            if (leave.getLeaveType() == null || leave.getLeaveType().trim().isEmpty()) {
                throw new RuntimeException("Leave type is required.");
            }

            if (leave.getFromDate() == null) {
                throw new RuntimeException("From Date is required.");
            }

            if (leave.getToDate() == null) {
                throw new RuntimeException("To Date is required.");
            }

            if (leave.getFromDate().isAfter(leave.getToDate())) {
                throw new RuntimeException("From Date cannot be after To Date.");
            }

            if (leave.getReason() == null || leave.getReason().trim().isEmpty()) {
                throw new RuntimeException("Reason is required.");
            }

            if (leave.getStatus() == null || leave.getStatus().trim().isEmpty()) {
                throw new RuntimeException("Status is required.");
            }

            if (leave.getEmployee() == null) {
                throw new RuntimeException("Employee is required.");
            }

            Optional<Employee> employeeOptional =
                    employeeRepository.findById(leave.getEmployee().getId());

            if (employeeOptional.isEmpty()) {
                throw new RuntimeException("Employee not found.");
            }

            leave.setEmployee(employeeOptional.get());

            Leave savedLeave = leaveRepository.save(leave);

            leaveModel.setId(savedLeave.getId());
            leaveModel.setEmployeeId(savedLeave.getEmployee().getId());
            leaveModel.setLeaveType(savedLeave.getLeaveType());
            leaveModel.setFromDate(savedLeave.getFromDate());
            leaveModel.setToDate(savedLeave.getToDate());
            leaveModel.setReason(savedLeave.getReason());
            leaveModel.setStatus(savedLeave.getStatus());

            return leaveModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<LeaveModel> getAllLeaves() {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            List<Leave> leaveList = leaveRepository.findAll();

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public LeaveModel getLeaveById(Integer id) {

        LeaveModel leaveModel = new LeaveModel();

        try {

            if (id == null) {
                throw new RuntimeException("Leave ID cannot be null.");
            }

            Optional<Leave> optionalLeave = leaveRepository.findById(id);

            if (optionalLeave.isEmpty()) {
                throw new RuntimeException("Leave not found.");
            }

            Leave leave = optionalLeave.get();

            leaveModel.setId(leave.getId());
            leaveModel.setEmployeeId(leave.getEmployee().getId());
            leaveModel.setLeaveType(leave.getLeaveType());
            leaveModel.setFromDate(leave.getFromDate());
            leaveModel.setToDate(leave.getToDate());
            leaveModel.setReason(leave.getReason());
            leaveModel.setStatus(leave.getStatus());

            return leaveModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public LeaveModel updateLeave(Integer id, Leave leave) {

        LeaveModel leaveModel = new LeaveModel();

        try {

            if (id == null) {
                throw new RuntimeException("Leave ID cannot be null.");
            }

            Optional<Leave> optionalLeave = leaveRepository.findById(id);

            if (optionalLeave.isEmpty()) {
                throw new RuntimeException("Leave not found.");
            }

            Leave existingLeave = optionalLeave.get();

            if (leave.getLeaveType() == null || leave.getLeaveType().trim().isEmpty()) {
                throw new RuntimeException("Leave Type is required.");
            }

            if (leave.getFromDate() == null) {
                throw new RuntimeException("From Date is required.");
            }

            if (leave.getToDate() == null) {
                throw new RuntimeException("To Date is required.");
            }

            if (leave.getFromDate().isAfter(leave.getToDate())) {
                throw new RuntimeException("From Date cannot be after To Date.");
            }

            if (leave.getReason() == null || leave.getReason().trim().isEmpty()) {
                throw new RuntimeException("Reason is required.");
            }

            if (leave.getStatus() == null || leave.getStatus().trim().isEmpty()) {
                throw new RuntimeException("Status is required.");
            }

            existingLeave.setLeaveType(leave.getLeaveType());
            existingLeave.setFromDate(leave.getFromDate());
            existingLeave.setToDate(leave.getToDate());
            existingLeave.setReason(leave.getReason());
            existingLeave.setStatus(leave.getStatus());

            if (leave.getEmployee() != null) {

                Optional<Employee> employeeOptional =
                        employeeRepository.findById(leave.getEmployee().getId());

                if (employeeOptional.isEmpty()) {
                    throw new RuntimeException("Employee not found.");
                }

                existingLeave.setEmployee(employeeOptional.get());
            }

            Leave updatedLeave = leaveRepository.save(existingLeave);

            leaveModel.setId(updatedLeave.getId());
            leaveModel.setEmployeeId(updatedLeave.getEmployee().getId());
            leaveModel.setLeaveType(updatedLeave.getLeaveType());
            leaveModel.setFromDate(updatedLeave.getFromDate());
            leaveModel.setToDate(updatedLeave.getToDate());
            leaveModel.setReason(updatedLeave.getReason());
            leaveModel.setStatus(updatedLeave.getStatus());

            return leaveModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteLeave(Integer id) {

        try {

            if (id == null) {
                throw new RuntimeException("Leave ID cannot be null.");
            }

            Optional<Leave> optionalLeave = leaveRepository.findById(id);

            if (optionalLeave.isEmpty()) {
                throw new RuntimeException("Leave not found.");
            }

            leaveRepository.delete(optionalLeave.get());

            return "Leave deleted successfully.";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<LeaveModel> getLeavesByType(String leaveType) {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            if (leaveType == null || leaveType.trim().isEmpty()) {
                throw new RuntimeException("Leave Type cannot be empty.");
            }

            List<Leave> leaveList = leaveRepository.findByLeaveType(leaveType);

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<LeaveModel> getLeavesByStatus(String status) {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            if (status == null || status.trim().isEmpty()) {
                throw new RuntimeException("Status cannot be empty.");
            }

            List<Leave> leaveList = leaveRepository.findByStatus(status);

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<LeaveModel> getLeavesByEmployee(Integer employeeId) {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            if (employeeId == null) {
                throw new RuntimeException("Employee ID cannot be null.");
            }

            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

            if (employeeOptional.isEmpty()) {
                throw new RuntimeException("Employee not found.");
            }

            List<Leave> leaveList = leaveRepository.findByEmployee_Id(employeeId);

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<LeaveModel> getLeavesByFromDate(LocalDate fromDate) {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            if (fromDate == null) {
                throw new RuntimeException("From Date cannot be null.");
            }

            List<Leave> leaveList = leaveRepository.findByFromDate(fromDate);

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<LeaveModel> getLeavesByToDate(LocalDate toDate) {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            if (toDate == null) {
                throw new RuntimeException("To Date cannot be null.");
            }

            List<Leave> leaveList = leaveRepository.findByToDate(toDate);

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<LeaveModel> searchLeaveReason(String reason) {

        List<LeaveModel> leaveModelList = new ArrayList<>();

        try {

            if (reason == null || reason.trim().isEmpty()) {
                throw new RuntimeException("Reason cannot be empty.");
            }

            List<Leave> leaveList = leaveRepository.findByReasonContainingIgnoreCase(reason);

            for (Leave leave : leaveList) {

                LeaveModel leaveModel = new LeaveModel();

                leaveModel.setId(leave.getId());
                leaveModel.setEmployeeId(leave.getEmployee().getId());
                leaveModel.setLeaveType(leave.getLeaveType());
                leaveModel.setFromDate(leave.getFromDate());
                leaveModel.setToDate(leave.getToDate());
                leaveModel.setReason(leave.getReason());
                leaveModel.setStatus(leave.getStatus());

                leaveModelList.add(leaveModel);
            }

            return leaveModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
