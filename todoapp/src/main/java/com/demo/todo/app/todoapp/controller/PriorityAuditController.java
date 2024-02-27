package com.demo.todo.app.todoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todo.app.todoapp.constants.MasterConstants;
import com.demo.todo.app.todoapp.constants.MessageConstants;
import com.demo.todo.app.todoapp.dto.PrioriotyAuditDto;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.Response;
import com.demo.todo.app.todoapp.dto.ResponseDto;
import com.demo.todo.app.todoapp.entity.PrioriotyAudit;
import com.demo.todo.app.todoapp.service.PriorityAuditService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class PriorityAuditController {

	@Autowired
	private PriorityAuditService priorityAuditService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PriorityAuditController.class);

	@GetMapping(path = "/prioriotyAudit")
	public ResponseEntity<Object> getAllPriorityAudit(@Valid RequestParamDto paramDto,
			@Valid PrioriotyAuditDto prioriotyAuditDto) {
		LOGGER.info("Controller method to fetch all priority audit");

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			PrioriotyAudit prioriotyAudit = objectMapper.readValue(objectMapper.writeValueAsString(prioriotyAuditDto),
					PrioriotyAudit.class);

			Page<PrioriotyAudit> pageOfprioriotyAudit = priorityAuditService.getAllPrioriotyAudit(paramDto,
					prioriotyAudit);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfprioriotyAudit.getPageable().getPageNumber());
			dto.setPageSize(pageOfprioriotyAudit.getPageable().getPageSize());
			dto.setTotalPages(pageOfprioriotyAudit.getTotalElements());
			dto.setContent(pageOfprioriotyAudit.getContent());
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_PRIORITY_AUDIT,
					"", true, dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all priority audit data in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_PRIORITY_AUDIT, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
