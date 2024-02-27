package com.demo.todo.app.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todo.app.todoapp.constants.MasterConstants;
import com.demo.todo.app.todoapp.constants.MessageConstants;
import com.demo.todo.app.todoapp.dto.PriorityDto;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.Response;
import com.demo.todo.app.todoapp.dto.ResponseDto;
import com.demo.todo.app.todoapp.entity.Priority;
import com.demo.todo.app.todoapp.service.PriorityService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping({ MasterConstants.ROOT_API_PATH_TODO })
public class PriorityController {

	@Autowired
	private PriorityService priorityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PriorityController.class);

	@GetMapping(path = "/priority")
	public ResponseEntity<Object> getAllPriority(@Valid RequestParamDto paramDto, @Valid PriorityDto priorityDto) {
		LOGGER.info("Controller method to fetch all priority");

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			Priority priority = objectMapper.readValue(objectMapper.writeValueAsString(priorityDto), Priority.class);

			Page<Priority> pageOfPriority = priorityService.getAllPriority(paramDto, priority);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfPriority.getPageable().getPageNumber());
			dto.setPageSize(pageOfPriority.getPageable().getPageSize());
			dto.setTotalPages(pageOfPriority.getTotalElements());
			dto.setContent(pageOfPriority.getContent());

			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_USERS, "", true,
					dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all priority in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_PRIORITY, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/priority/{priorityId}")
	public ResponseEntity<Object> getPriorityById(@PathVariable Integer priorityId) {
		LOGGER.info("Controller method to fetch all priority by Id");

		try {
			Optional<Priority> userById = priorityService.getPriorityById(priorityId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_FETCH_PRIORITY + priorityId + MessageConstants.COMMON_SUCCESS_FETCH, "",
					true, userById);
			return new ResponseEntity<Object>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user details by  userId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_PRIORITY + priorityId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(path = "/priority/{priorityId}")
	public ResponseEntity<Object> deletePriorityById(@PathVariable Integer priorityId) {
		LOGGER.info("Inside the deletePriorityById method in Service");

		try {
			priorityService.deletePriorityById(priorityId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_PRIORITY + priorityId + MessageConstants.COMMON_HAS_DELETED, "", true, "");
			return new ResponseEntity<>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting user by userId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_DELETE_PRIORITY + priorityId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/priority")
	public ResponseEntity<Object> saveOrUpdatePriority(@RequestBody List<Priority> listOfpriority) {
		LOGGER.info("Controller method to save or update user");

		try {
			List<Priority> savedPriority = priorityService.saveOrUpdatePriority(listOfpriority);
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_SAVE_OR_UPDATE_PRIORITY,
					"", true, savedPriority);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate priority in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_SAVE_OR_UPDATE_PRORITY, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
