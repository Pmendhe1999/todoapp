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
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.Response;
import com.demo.todo.app.todoapp.dto.ResponseDto;
import com.demo.todo.app.todoapp.dto.StatusAuditDto;
import com.demo.todo.app.todoapp.entity.StatusAudit;
import com.demo.todo.app.todoapp.service.StatusAuditService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class StatusAuditController {

	@Autowired
	private StatusAuditService statusAuditService;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusAuditController.class);

	@GetMapping(path = "/statusAudit")
	public ResponseEntity<Object> getAllStatusAudit(@Valid RequestParamDto paramDto,
			@Valid StatusAuditDto statusAuditDto) {
		LOGGER.info("Controller method to fetch all status audit");

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			StatusAudit getStatusAudit = objectMapper.readValue(objectMapper.writeValueAsString(statusAuditDto),
					StatusAudit.class);

			Page<StatusAudit> pageOfStatus = statusAuditService.getAllStatusAudit(paramDto, getStatusAudit);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfStatus.getPageable().getPageNumber());
			dto.setPageSize(pageOfStatus.getPageable().getPageSize());
			dto.setTotalPages(pageOfStatus.getTotalElements());
			dto.setContent(pageOfStatus.getContent());
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_USERS_AUDIT, "",
					true, dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all user audit data in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_STATUS, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
