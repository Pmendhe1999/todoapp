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
import com.demo.todo.app.todoapp.dto.UserAuditDto;
import com.demo.todo.app.todoapp.dto.UserDto;
import com.demo.todo.app.todoapp.entity.User;
import com.demo.todo.app.todoapp.entity.UserAudit;
import com.demo.todo.app.todoapp.service.UserAuditService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class UserAuditController {

	@Autowired
	private UserAuditService userAuditService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuditController.class);

	@GetMapping(path = "/userAudit")
	public ResponseEntity<Object> getAllUserAudit(@Valid RequestParamDto paramDto
			,@Valid UserAuditDto userAuditDto) {
		LOGGER.info("Controller method to fetch all user audit");
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			UserAudit getUserAudDto = objectMapper.readValue(objectMapper.writeValueAsString(userAuditDto), UserAudit.class);
			
			
			Page<UserAudit> pageOfUsers = userAuditService.getAllUserAudit(paramDto,getUserAudDto);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfUsers.getPageable().getPageNumber());
			dto.setPageSize(pageOfUsers.getPageable().getPageSize());
			dto.setTotalPages(pageOfUsers.getTotalElements());
			dto.setContent(pageOfUsers.getContent());
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_USERS_AUDIT, "",
					true, dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all user audit data in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_USER, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
