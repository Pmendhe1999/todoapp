package com.demo.todo.app.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.todo.app.todoapp.entity.TaskAudit;

public interface TaskAuditRepository extends JpaRepository<TaskAudit, Integer>, JpaSpecificationExecutor<TaskAudit> {

}
