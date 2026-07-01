package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.Task;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.repository.TaskRepository;
import com.example.employeeManagement.service.TaskService;
import com.example.employeeManagement.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public TaskModel addTask(Task task) {

        TaskModel taskModel = new TaskModel();

        try {

            if (task == null) {
                throw new RuntimeException("Task details cannot be null.");
            }

            if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
                throw new RuntimeException("Task title is required.");
            }

            if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
                throw new RuntimeException("Description is required.");
            }

            if (task.getPriority() == null || task.getPriority().trim().isEmpty()) {
                throw new RuntimeException("Priority is required.");
            }

            if (task.getDueDate() == null) {
                throw new RuntimeException("Due Date is required.");
            }

            if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
                throw new RuntimeException("Status is required.");
            }

            if (task.getEmployee() == null) {
                throw new RuntimeException("Employee is required.");
            }

            Optional<Employee> employeeOptional =
                    employeeRepository.findById(task.getEmployee().getId());

            if (employeeOptional.isEmpty()) {
                throw new RuntimeException("Employee not found.");
            }

            task.setEmployee(employeeOptional.get());

            Task savedTask = taskRepository.save(task);

            taskModel.setId(savedTask.getId());
            taskModel.setEmployeeId(savedTask.getEmployee().getId());
            taskModel.setTitle(savedTask.getTitle());
            taskModel.setDescription(savedTask.getDescription());
            taskModel.setPriority(savedTask.getPriority());
            taskModel.setDueDate(savedTask.getDueDate());
            taskModel.setStatus(savedTask.getStatus());

            return taskModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TaskModel> getAllTasks() {

        List<TaskModel> taskModelList = new ArrayList<>();

        try {

            List<Task> taskList = taskRepository.findAll();

            for (Task task : taskList) {

                TaskModel taskModel = new TaskModel();

                taskModel.setId(task.getId());
                taskModel.setEmployeeId(task.getEmployee().getId());
                taskModel.setTitle(task.getTitle());
                taskModel.setDescription(task.getDescription());
                taskModel.setPriority(task.getPriority());
                taskModel.setDueDate(task.getDueDate());
                taskModel.setStatus(task.getStatus());

                taskModelList.add(taskModel);
            }

            return taskModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TaskModel getTaskById(Integer id) {

        TaskModel taskModel = new TaskModel();

        try {

            if (id == null) {
                throw new RuntimeException("Task ID cannot be null.");
            }

            Optional<Task> optionalTask = taskRepository.findById(id);

            if (optionalTask.isEmpty()) {
                throw new RuntimeException("Task not found.");
            }

            Task task = optionalTask.get();

            taskModel.setId(task.getId());
            taskModel.setEmployeeId(task.getEmployee().getId());
            taskModel.setTitle(task.getTitle());
            taskModel.setDescription(task.getDescription());
            taskModel.setPriority(task.getPriority());
            taskModel.setDueDate(task.getDueDate());
            taskModel.setStatus(task.getStatus());

            return taskModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public TaskModel updateTask(Integer id, Task task) {

        TaskModel taskModel = new TaskModel();

        try {

            if (id == null) {
                throw new RuntimeException("Task ID cannot be null.");
            }

            Optional<Task> optionalTask = taskRepository.findById(id);

            if (optionalTask.isEmpty()) {
                throw new RuntimeException("Task not found.");
            }

            Task existingTask = optionalTask.get();

            if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
                throw new RuntimeException("Task title is required.");
            }

            if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
                throw new RuntimeException("Description is required.");
            }

            if (task.getPriority() == null || task.getPriority().trim().isEmpty()) {
                throw new RuntimeException("Priority is required.");
            }

            if (task.getDueDate() == null) {
                throw new RuntimeException("Due Date is required.");
            }

            if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
                throw new RuntimeException("Status is required.");
            }

            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setStatus(task.getStatus());

            if (task.getEmployee() != null) {

                Optional<Employee> employeeOptional =
                        employeeRepository.findById(task.getEmployee().getId());

                if (employeeOptional.isEmpty()) {
                    throw new RuntimeException("Employee not found.");
                }

                existingTask.setEmployee(employeeOptional.get());
            }

            Task updatedTask = taskRepository.save(existingTask);

            taskModel.setId(updatedTask.getId());
            taskModel.setEmployeeId(updatedTask.getEmployee().getId());
            taskModel.setTitle(updatedTask.getTitle());
            taskModel.setDescription(updatedTask.getDescription());
            taskModel.setPriority(updatedTask.getPriority());
            taskModel.setDueDate(updatedTask.getDueDate());
            taskModel.setStatus(updatedTask.getStatus());

            return taskModel;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteTask(Integer id) {

        try {

            if (id == null) {
                throw new RuntimeException("Task ID cannot be null.");
            }

            Optional<Task> optionalTask = taskRepository.findById(id);

            if (optionalTask.isEmpty()) {
                throw new RuntimeException("Task not found.");
            }

            taskRepository.delete(optionalTask.get());

            return "Task deleted successfully.";

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TaskModel> getTaskByStatus(String status) {

        List<TaskModel> taskModelList = new ArrayList<>();

        try {

            if (status == null || status.trim().isEmpty()) {
                throw new RuntimeException("Status cannot be empty.");
            }

            List<Task> taskList = taskRepository.findByStatus(status);

            for (Task task : taskList) {

                TaskModel taskModel = new TaskModel();

                taskModel.setId(task.getId());
                taskModel.setEmployeeId(task.getEmployee().getId());
                taskModel.setTitle(task.getTitle());
                taskModel.setDescription(task.getDescription());
                taskModel.setPriority(task.getPriority());
                taskModel.setDueDate(task.getDueDate());
                taskModel.setStatus(task.getStatus());

                taskModelList.add(taskModel);
            }

            return taskModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<TaskModel> getTaskByPriority(String priority) {

        List<TaskModel> taskModelList = new ArrayList<>();

        try {

            if (priority == null || priority.trim().isEmpty()) {
                throw new RuntimeException("Priority cannot be empty.");
            }

            List<Task> taskList = taskRepository.findByPriority(priority);

            for (Task task : taskList) {

                TaskModel taskModel = new TaskModel();

                taskModel.setId(task.getId());
                taskModel.setEmployeeId(task.getEmployee().getId());
                taskModel.setTitle(task.getTitle());
                taskModel.setDescription(task.getDescription());
                taskModel.setPriority(task.getPriority());
                taskModel.setDueDate(task.getDueDate());
                taskModel.setStatus(task.getStatus());

                taskModelList.add(taskModel);
            }

            return taskModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TaskModel> getTaskByDueDate(LocalDate dueDate) {

        List<TaskModel> taskModelList = new ArrayList<>();

        try {

            if (dueDate == null) {
                throw new RuntimeException("Due Date cannot be null.");
            }

            List<Task> taskList = taskRepository.findByDueDate(dueDate);

            for (Task task : taskList) {

                TaskModel taskModel = new TaskModel();

                taskModel.setId(task.getId());
                taskModel.setEmployeeId(task.getEmployee().getId());
                taskModel.setTitle(task.getTitle());
                taskModel.setDescription(task.getDescription());
                taskModel.setPriority(task.getPriority());
                taskModel.setDueDate(task.getDueDate());
                taskModel.setStatus(task.getStatus());

                taskModelList.add(taskModel);
            }

            return taskModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TaskModel> getTaskByEmployee(Integer employeeId) {

        List<TaskModel> taskModelList = new ArrayList<>();

        try {

            if (employeeId == null) {
                throw new RuntimeException("Employee ID cannot be null.");
            }

            Optional<Employee> employeeOptional =
                    employeeRepository.findById(employeeId);

            if (employeeOptional.isEmpty()) {
                throw new RuntimeException("Employee not found.");
            }

            List<Task> taskList =
                    taskRepository.findByEmployee_Id(employeeId);

            for (Task task : taskList) {

                TaskModel taskModel = new TaskModel();

                taskModel.setId(task.getId());
                taskModel.setEmployeeId(task.getEmployee().getId());
                taskModel.setTitle(task.getTitle());
                taskModel.setDescription(task.getDescription());
                taskModel.setPriority(task.getPriority());
                taskModel.setDueDate(task.getDueDate());
                taskModel.setStatus(task.getStatus());

                taskModelList.add(taskModel);
            }

            return taskModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TaskModel> searchTaskTitle(String title) {

        List<TaskModel> taskModelList = new ArrayList<>();

        try {

            if (title == null || title.trim().isEmpty()) {
                throw new RuntimeException("Title cannot be empty.");
            }

            List<Task> taskList =
                    taskRepository.findByTitleContainingIgnoreCase(title);

            for (Task task : taskList) {

                TaskModel taskModel = new TaskModel();

                taskModel.setId(task.getId());
                taskModel.setEmployeeId(task.getEmployee().getId());
                taskModel.setTitle(task.getTitle());
                taskModel.setDescription(task.getDescription());
                taskModel.setPriority(task.getPriority());
                taskModel.setDueDate(task.getDueDate());
                taskModel.setStatus(task.getStatus());

                taskModelList.add(taskModel);
            }

            return taskModelList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
