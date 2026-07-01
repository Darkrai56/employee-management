package com.example.employeeManagement.controller;

import com.example.employeeManagement.entity.Leave;
import com.example.employeeManagement.service.LeaveService;
import com.example.employeeManagement.model.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
@CrossOrigin(origins = "*")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/add")
    public ResponseEntity<?> addLeave(@RequestBody Leave leave) {

        try {

            LeaveModel leaveModel = leaveService.addLeave(leave);

            return new ResponseEntity<>(leaveModel, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLeaves() {

        try {

            List<LeaveModel> leaveList = leaveService.getAllLeaves();

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaveById(@PathVariable Integer id) {

        try {

            LeaveModel leaveModel = leaveService.getLeaveById(id);

            return new ResponseEntity<>(leaveModel, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLeave(
            @PathVariable Integer id,
            @RequestParam String leaveType,
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam String reason,
            @RequestParam String status) {

        try {

            Leave leave = new Leave();

            leave.setLeaveType(leaveType);
            leave.setFromDate(java.time.LocalDate.parse(fromDate));
            leave.setToDate(java.time.LocalDate.parse(toDate));
            leave.setReason(reason);
            leave.setStatus(status);

            LeaveModel leaveModel = leaveService.updateLeave(id, leave);

            return new ResponseEntity<>(leaveModel, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLeave(@PathVariable Integer id) {

        try {

            String message = leaveService.deleteLeave(id);

            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/type/{leaveType}")
    public ResponseEntity<?> getLeavesByType(@PathVariable String leaveType) {

        try {

            List<LeaveModel> leaveList =
                    leaveService.getLeavesByType(leaveType);

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getLeavesByStatus(@PathVariable String status) {

        try {

            List<LeaveModel> leaveList =
                    leaveService.getLeavesByStatus(status);

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getLeavesByEmployee(@PathVariable Integer employeeId) {

        try {

            List<LeaveModel> leaveList =
                    leaveService.getLeavesByEmployee(employeeId);

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/fromDate/{fromDate}")
    public ResponseEntity<?> getLeavesByFromDate(@PathVariable String fromDate) {

        try {

            List<LeaveModel> leaveList =
                    leaveService.getLeavesByFromDate(java.time.LocalDate.parse(fromDate));

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/toDate/{toDate}")
    public ResponseEntity<?> getLeavesByToDate(@PathVariable String toDate) {

        try {

            List<LeaveModel> leaveList =
                    leaveService.getLeavesByToDate(java.time.LocalDate.parse(toDate));

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/search/{reason}")
    public ResponseEntity<?> searchLeaveReason(@PathVariable String reason) {

        try {

            List<LeaveModel> leaveList =
                    leaveService.searchLeaveReason(reason);

            return new ResponseEntity<>(leaveList, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}