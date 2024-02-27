package com.demo.todo.app.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.todo.app.todoapp.entity.CategoryAudit;

public interface CategoryAuditRepository
		extends JpaRepository<CategoryAudit, Integer>, JpaSpecificationExecutor<CategoryAudit> {

}
