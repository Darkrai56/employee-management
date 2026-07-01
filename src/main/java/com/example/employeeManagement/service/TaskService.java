package com.example.employeeManagement.service;

import com.example.employeeManagement.entity.Task;
import com.example.employeeManagement.model.TaskModel;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    TaskModel addTask(Task task);

    List<TaskModel> getAllTasks();

    TaskModel getTaskById(Integer id);

    TaskModel updateTask(Integer id, Task task);

    String deleteTask(Integer id);

    List<TaskModel> getTaskByStatus(String status);

    List<TaskModel> getTaskByPriority(String priority);

    List<TaskModel> getTaskByDueDate(LocalDate dueDate);

    List<TaskModel> getTaskByEmployee(Integer employeeId);

    List<TaskModel> searchTaskTitle(String title);
}