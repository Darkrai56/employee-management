
package com.example.employeeManagement.repository;
import com.example.employeeManagement.model.TaskModel;
import com.example.employeeManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(String status);

    List<Task> findByPriority(String priority);

    List<Task> findByDueDate(LocalDate dueDate);

    List<Task> findByEmployee_Id(Integer employeeId);

    List<Task> findByTitleContainingIgnoreCase(String title);
    List<TaskModel> getTaskByStatus(String status);

    List<TaskModel> getTaskByPriority(String priority);

    List<TaskModel> getTaskByDueDate(LocalDate dueDate);

    List<TaskModel> getTaskByEmployee(Integer employeeId);

    List<TaskModel> searchTaskTitle(String title);
}