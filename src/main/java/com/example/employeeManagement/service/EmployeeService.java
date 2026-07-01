package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Employee;
import com.example.employeemanagement.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    EmployeeModel addEmployee(Employee employee);

    List<EmployeeModel> getAllEmployees();

    EmployeeModel getEmployeeById(Integer id);

    EmployeeModel updateEmployee(Integer id, Employee employee);

    String deleteEmployee(Integer id);
    List<EmployeeModel> getEmployeesByDepartment(String department);

    List<EmployeeModel> getEmployeesByDesignation(String designation);

    List<EmployeeModel> getEmployeesByStatus(Boolean isActive);

    EmployeeModel getEmployeeByEmail(String email);

    List<EmployeeModel> searchEmployeeByName(String name);
}