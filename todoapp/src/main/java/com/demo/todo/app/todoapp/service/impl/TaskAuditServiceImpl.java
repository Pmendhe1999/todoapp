package com.demo.todo.app.todoapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.TaskAudit;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.TaskAuditRepository;
import com.demo.todo.app.todoapp.service.TaskAuditService;

import jakarta.validation.Valid;
@Service
public class TaskAuditServiceImpl implements TaskAuditService {

	@Autowired
	private TaskAuditRepository taskAuditRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskAuditServiceImpl.class);

	@Override
	public Page<TaskAudit> getAllTaskAudit(@Valid RequestParamDto paramDto, TaskAudit getTaskAudit) {
		LOGGER.info("Inside the getAllTaskAudit method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<TaskAudit> listOfSpecificationUserAudit = taskAuditRepository.findAll(pageable);
			return listOfSpecificationUserAudit;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all task audit data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in task audit method");
		}
	}
}
