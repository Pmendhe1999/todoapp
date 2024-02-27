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
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.dto.Response;
import com.demo.todo.app.todoapp.dto.ResponseDto;
import com.demo.todo.app.todoapp.dto.StatusDto;
import com.demo.todo.app.todoapp.entity.Status;
import com.demo.todo.app.todoapp.service.StatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class StatusController {

	@Autowired
	private StatusService statusService;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

	@GetMapping(path = "/status")
	public ResponseEntity<Object> getAllStatus(@Valid RequestParamDto paramDto, @Valid StatusDto statusDto) {
		LOGGER.info("Controller method to fetch all category");
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			Status Status = objectMapper.readValue(objectMapper.writeValueAsString(statusDto), Status.class);

			Page<Status> pageOfStatus = statusService.getAllStatus(paramDto, Status);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfStatus.getPageable().getPageNumber());
			dto.setPageSize(pageOfStatus.getPageable().getPageSize());
			dto.setTotalPages(pageOfStatus.getTotalElements());
			dto.setContent(pageOfStatus.getContent());

			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_STATUS, "",
					true, dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all status in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_STATUS, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/status/{statusId}")
	public ResponseEntity<Object> getStatusById(@PathVariable Integer statusId) {
		LOGGER.info("Controller method to fetch status by Id");

		try {
			Optional<Status> statusById = statusService.getUserStatusId(statusId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_FETCH_STATUS + statusId + MessageConstants.COMMON_SUCCESS_FETCH, "", true,
					statusById);
			return new ResponseEntity<Object>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching status details by  statusId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_USER + statusId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/status")
	public ResponseEntity<Object> saveAndUpdateStatus(@RequestBody List<Status> listOfStatus) {
		LOGGER.info("Controller method to save or update status");

		try {
			List<Status> savedCategory = statusService.saveAndUpdateStatus(listOfStatus);
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_SAVE_OR_UPDATE_STATUS, "",
					true, savedCategory);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate status in category ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_SAVE_OR_UPDATE_STATUS, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping(path = "/status/{statusId}")
	public ResponseEntity<Object> deleteStatus(@PathVariable Integer statusId){
		LOGGER.info("Controller method to delete status by Id");

		try {
			statusService.deleteStatus(statusId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_STATUS + statusId + MessageConstants.COMMON_HAS_DELETED, "", true, "");
			return new ResponseEntity<>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting staus by statusId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_DELETE_STATUS + statusId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
