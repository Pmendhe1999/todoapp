package com.demo.todo.app.todoapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.todo.app.todoapp.controller.UserAuditController;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.StatusAudit;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.StatusAuditRepository;
import com.demo.todo.app.todoapp.service.StatusAuditService;

import jakarta.validation.Valid;

@Service
public class StatusAuditServiceImpl implements StatusAuditService {

	@Autowired
	private StatusAuditRepository statusAuditRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusAuditServiceImpl.class);

	@Override
	public Page<StatusAudit> getAllStatusAudit(@Valid RequestParamDto paramDto, StatusAudit getStatusAudit) {
		LOGGER.info("Inside the getAllStatusAudit method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<StatusAudit> listOfSpecificationStatusAudit = statusAuditRepository.findAll(pageable);
			return listOfSpecificationStatusAudit;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all status audit data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in status audit method");

		}
	}

}
