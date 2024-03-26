package com.example.massimportuniversalendpoint.repository;

import com.example.massimportuniversalendpoint.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
