package com.example.employeeManagement.controller;
import com.example.employeeManagement.entity.Task;
import com.example.employeeManagement.service.TaskService;
import com.example.employeeManagement.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        try {
            TaskModel taskModel = taskService.addTask(task);
            return new ResponseEntity<>(taskModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        try {
            List<TaskModel> taskList = taskService.getAllTasks();
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
        try {
            TaskModel taskModel = taskService.getTaskById(id);
            return new ResponseEntity<>(taskModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Integer id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String priority,
            @RequestParam String dueDate,
            @RequestParam String status) {
        try {
            Task task = new Task();
            task.setTitle(title);
            task.setDescription(description);
            task.setPriority(priority);
            task.setDueDate(java.time.LocalDate.parse(dueDate));
            task.setStatus(status);
            TaskModel taskModel = taskService.updateTask(id, task);
            return new ResponseEntity<>(taskModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        try {
            String message = taskService.deleteTask(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getTaskByStatus(@PathVariable String status) {
        try {
            List<TaskModel> taskList =
                    taskService.getTaskByStatus(status);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/priority/{priority}")
    public ResponseEntity<?> getTaskByPriority(@PathVariable String priority) {
        try {
            List<TaskModel> taskList =
                    taskService.getTaskByPriority(priority);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/dueDate/{dueDate}")
    public ResponseEntity<?> getTaskByDueDate(@PathVariable String dueDate) {
        try {
            List<TaskModel> taskList =
                    taskService.getTaskByDueDate(
                            java.time.LocalDate.parse(dueDate));
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getTaskByEmployee(@PathVariable Integer employeeId) {
        try {
            List<TaskModel> taskList =
                    taskService.getTaskByEmployee(employeeId);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchTaskTitle(@PathVariable String title) {
        try {
            List<TaskModel> taskList =
                    taskService.searchTaskTitle(title);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
