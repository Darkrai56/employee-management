package com.example.employeeManagement.controller;
import com.example.employeeManagement.entity.Attendance;
import com.example.employeeManagement.service.AttendanceService;
import com.example.employeeManagement.model.AttendanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping("/add")
    public ResponseEntity<?> addAttendance(@RequestBody Attendance attendance) {
        try {
            AttendanceModel attendanceModel =
                    attendanceService.addAttendance(attendance);
            return new ResponseEntity<>(attendanceModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllAttendance() {
        try {
            List<AttendanceModel> attendanceList =
                    attendanceService.getAllAttendance();
            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Integer id) {
        try {
            AttendanceModel attendanceModel =
                    attendanceService.getAttendanceById(id);
            return new ResponseEntity<>(attendanceModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(
            @PathVariable Integer id,
            @RequestParam String date,
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam String status) {
        try {
            Attendance attendance = new Attendance();
            attendance.setDate(java.time.LocalDate.parse(date));
            attendance.setCheckIn(java.time.LocalTime.parse(checkIn));
            attendance.setCheckOut(java.time.LocalTime.parse(checkOut));
            attendance.setStatus(status);
            AttendanceModel attendanceModel = attendanceService.updateAttendance(id, attendance);
            return new ResponseEntity<>(attendanceModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Integer id) {
        try {
            String message = attendanceService.deleteAttendance(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getAttendanceByStatus(@PathVariable String status) {
        try {
            List<AttendanceModel> attendanceList =
                    attendanceService.getAttendanceByStatus(status);
            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getAttendanceByDate(@PathVariable String date) {
        try {
            List<AttendanceModel> attendanceList =
                    attendanceService.getAttendanceByDate(
                            java.time.LocalDate.parse(date));
            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getAttendanceByEmployee(@PathVariable Integer employeeId) {
        try {
            List<AttendanceModel> attendanceList =
                    attendanceService.getAttendanceByEmployee(employeeId);
            return new ResponseEntity<>(attendanceList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
