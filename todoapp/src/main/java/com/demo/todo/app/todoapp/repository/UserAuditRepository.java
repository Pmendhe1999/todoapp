package com.demo.todo.app.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.todo.app.todoapp.entity.UserAudit;

public interface UserAuditRepository extends JpaRepository<UserAudit, Integer>, JpaSpecificationExecutor<UserAudit> {

}
