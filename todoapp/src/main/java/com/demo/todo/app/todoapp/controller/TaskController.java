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
import com.demo.todo.app.todoapp.dto.TaskDto;
import com.demo.todo.app.todoapp.entity.Task;
import com.demo.todo.app.todoapp.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class TaskController {

	@Autowired
	private TaskService taskService;

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@GetMapping(path = "/task")
	public ResponseEntity<Object> getAllTasks(@Valid RequestParamDto paramDto, @Valid TaskDto taskDto) {
		LOGGER.info("Controller method to fetch all tasks");
		try {

			ObjectMapper objectMapper = new ObjectMapper();

			Task task = objectMapper.readValue(objectMapper.writeValueAsString(taskDto), Task.class);
			Page<Task> pageOfTasks = taskService.getAllTasks(paramDto, task);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfTasks.getPageable().getPageNumber());
			dto.setPageSize(pageOfTasks.getPageable().getPageSize());
			dto.setTotalPages(pageOfTasks.getTotalElements());
			dto.setContent(pageOfTasks.getContent());

			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_TAKS, "", true,
					dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all users in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_USER, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/task/{taskId}")
	public ResponseEntity<Object> getTaskById(@PathVariable Integer taskId) {
		LOGGER.info("Controller method to get Task By Id by Id");

		try {
			Optional<Task> taskById = taskService.getTaskById(taskId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_FETCH_USER + taskId + MessageConstants.COMMON_SUCCESS_FETCH, "", true,
					taskById);
			return new ResponseEntity<Object>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching task details by  taskId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_TASK + taskId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/task/{taskId}")
	public ResponseEntity<Object> deleteTaskById(@PathVariable Integer taskId) {
		LOGGER.info("Controller method to fetch task by Id");

		try {
			taskService.deleteTaskById(taskId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_TASK + taskId + MessageConstants.COMMON_HAS_DELETED, "", true, "");
			return new ResponseEntity<>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting task by taskId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_DELETE_USER + taskId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/task")
	public ResponseEntity<Object> saveAndUpdateTask(@RequestBody List<Task> listOfTasks) {
		LOGGER.info("Controller method to save or update task");

		try {
			List<Task> savedUsers = taskService.saveOrUpdateTasks(listOfTasks);
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_SAVE_OR_UPDATE_USER, "",
					true, savedUsers);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate task in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_SAVE_OR_UPDATE_TASK, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/getTaskByUserId/{taskByUserId}")
	public ResponseEntity<Object> getTaskByUserId(@PathVariable Integer taskByUserId) {
		LOGGER.info("Entering into get Tasks Sort By Catagory Method in TaskController");
		try {
			List<Task> listTasks = taskService.getTaskByUserId(taskByUserId);
			Response response = new Response(HttpStatus.OK.value(), "get task by user Id fetched succeefully", "", true,
					listTasks);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while get task by user Id in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"Error Occured while get task by user Id", e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
