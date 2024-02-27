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
import com.demo.todo.app.todoapp.entity.PrioriotyAudit;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.PriorityAuditRepository;
import com.demo.todo.app.todoapp.service.PriorityAuditService;

import jakarta.validation.Valid;

@Service
public class PriorityAuditServiceImpl implements PriorityAuditService {

	@Autowired
	private PriorityAuditRepository priorityAuditRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(PriorityAuditServiceImpl.class);

	@Override
	public Page<PrioriotyAudit> getAllPrioriotyAudit(@Valid RequestParamDto paramDto, PrioriotyAudit prioriotyAudit) {
		LOGGER.info("Inside the getAllPrioriotyAudit method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<PrioriotyAudit> listOfSpecificationUserAudit = priorityAuditRepository.findAll(pageable);
			return listOfSpecificationUserAudit;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all priority audit data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in priority audit method");
		}

	}

}
