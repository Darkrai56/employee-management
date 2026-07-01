package com.example.employeeManagement.controller;

import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.service.EmployeeService;
import com.example.employeemanagement.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {

        try {

            EmployeeModel employeeModel = employeeService.addEmployee(employee);

            return new ResponseEntity<>(employeeModel, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {

        try {

            List<EmployeeModel> employeeList = employeeService.getAllEmployees();

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {

        try {

            EmployeeModel employeeModel = employeeService.getEmployeeById(id);

            return new ResponseEntity<>(employeeModel, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id,
                                            @RequestBody Employee employee) {

        try {

            EmployeeModel employeeModel = employeeService.updateEmployee(id, employee);

            return new ResponseEntity<>(employeeModel, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {

        try {

            String message = employeeService.deleteEmployee(id);

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<?> getEmployeesByDepartment(@PathVariable String department) {

        try {

            List<EmployeeModel> employeeList =
                    employeeService.getEmployeesByDepartment(department);

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/designation/{designation}")
    public ResponseEntity<?> getEmployeesByDesignation(@PathVariable String designation) {

        try {

            List<EmployeeModel> employeeList =
                    employeeService.getEmployeesByDesignation(designation);

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/status/{isActive}")
    public ResponseEntity<?> getEmployeesByStatus(@PathVariable Boolean isActive) {

        try {

            List<EmployeeModel> employeeList =
                    employeeService.getEmployeesByStatus(isActive);

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable String email) {

        try {

            EmployeeModel employeeModel =
                    employeeService.getEmployeeByEmail(email);

            return new ResponseEntity<>(employeeModel, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchEmployeeByName(@PathVariable String name) {

        try {

            List<EmployeeModel> employeeList =
                    employeeService.searchEmployeeByName(name);

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
