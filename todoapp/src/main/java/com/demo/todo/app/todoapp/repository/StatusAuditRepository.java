package com.demo.todo.app.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.todo.app.todoapp.entity.Status;
import com.demo.todo.app.todoapp.entity.StatusAudit;

public interface StatusAuditRepository extends JpaRepository<StatusAudit, Integer>, JpaSpecificationExecutor<StatusAudit> {

}
