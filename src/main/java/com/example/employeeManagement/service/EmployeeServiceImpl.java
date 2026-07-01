package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Employee;
import com.example.employeemanagement.model.EmployeeModel;
import com.example.employeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeModel convertToModel(Employee employee) {
        EmployeeModel model = new EmployeeModel();
        model.setId(employee.getId());
        model.setName(employee.getName());
        model.setEmail(employee.getEmail());
        model.setPhone(employee.getPhone());
        model.setDepartment(employee.getDepartment());
        model.setDesignation(employee.getDesignation());
        model.setJoiningDate(employee.getJoiningDate());
        model.setIsActive(employee.getIsActive());
        return model;
    }

    @Override
    public EmployeeModel addEmployee(Employee employee) {
        try {
            if (employee.getName() == null || employee.getName().trim().isEmpty())
                throw new RuntimeException("Name is required");

            if (employee.getEmail() == null || employee.getEmail().trim().isEmpty())
                throw new RuntimeException("Email is required");

            if (!employee.getEmail().contains("@"))
                throw new RuntimeException("Invalid email");

            if (employee.getPhone() == null || employee.getPhone().length() != 10)
                throw new RuntimeException("Phone must contain 10 digits");

            if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty())
                throw new RuntimeException("Department is required");

            if (employee.getDesignation() == null || employee.getDesignation().trim().isEmpty())
                throw new RuntimeException("Designation is required");

            if (employee.getJoiningDate() == null)
                throw new RuntimeException("Joining date is required");

            if (employee.getIsActive() == null)
                throw new RuntimeException("Status is required");

            Employee saved = employeeRepository.save(employee);
            return convertToModel(saved);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeModel> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeModel> models = new ArrayList<>();

            for (Employee employee : employees) {
                models.add(convertToModel(employee));
            }

            return models;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public EmployeeModel getEmployeeById(Integer id) {
        try {
            Optional<Employee> optional = employeeRepository.findById(id);

            if (optional.isEmpty()) {
                throw new RuntimeException("Employee not found");
            }

            return convertToModel(optional.get());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public EmployeeModel updateEmployee(Integer id, Employee employee) {
        try {
            Optional<Employee> optional = employeeRepository.findById(id);

            if (optional.isEmpty()) {
                throw new RuntimeException("Employee not found");
            }

            Employee existing = optional.get();

            existing.setName(employee.getName());
            existing.setEmail(employee.getEmail());
            existing.setPhone(employee.getPhone());
            existing.setDepartment(employee.getDepartment());
            existing.setDesignation(employee.getDesignation());
            existing.setJoiningDate(employee.getJoiningDate());
            existing.setIsActive(employee.getIsActive());

            Employee updated = employeeRepository.save(existing);

            return convertToModel(updated);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteEmployee(Integer id) {
        try {
            if (!employeeRepository.existsById(id)) {
                throw new RuntimeException("Employee not found");
            }

            employeeRepository.deleteById(id);
            return "Employee deleted successfully";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<EmployeeModel> getEmployeesByDepartment(String department) {
        try {
            List<EmployeeModel> models = new ArrayList<>();
            for (Employee e : employeeRepository.findByDepartment(department)) {
                models.add(convertToModel(e));
            }
            return models;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeModel> getEmployeesByDesignation(String designation) {
        try {
            List<EmployeeModel> models = new ArrayList<>();
            for (Employee e : employeeRepository.findByDesignation(designation)) {
                models.add(convertToModel(e));
            }
            return models;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeModel> getEmployeesByStatus(Boolean isActive) {
        try {
            List<EmployeeModel> models = new ArrayList<>();
            for (Employee e : employeeRepository.findByIsActive(isActive)) {
                models.add(convertToModel(e));
            }
            return models;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public EmployeeModel getEmployeeByEmail(String email) {
        try {
            Employee e = employeeRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            return convertToModel(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<EmployeeModel> searchEmployeeByName(String name) {
        try {
            List<EmployeeModel> models = new ArrayList<>();
            for (Employee e : employeeRepository.findByNameContainingIgnoreCase(name)) {
                models.add(convertToModel(e));
            }
            return models;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}