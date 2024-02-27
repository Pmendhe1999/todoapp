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
import com.demo.todo.app.todoapp.dto.UserAuditDto;
import com.demo.todo.app.todoapp.dto.UserDto;
import com.demo.todo.app.todoapp.entity.User;
import com.demo.todo.app.todoapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { MasterConstants.ROOT_API_PATH_TODO })
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping(path = "/user")
	public ResponseEntity<Object> saveOrUpdateUser(@RequestBody List<User> listOfUsers) {
		LOGGER.info("Controller method to save or update user");
		try {
			List<User> savedUsers = userService.saveOrUpdateUser(listOfUsers);
			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_SAVE_OR_UPDATE_USER, "",
					true, savedUsers);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate user in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_SAVE_OR_UPDATE_USER, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/users")
	public ResponseEntity<Object> getAllUsers(@Valid RequestParamDto paramDto,@Valid UserDto userDto) {
		LOGGER.info("Controller method to fetch all users");
		try {
			
			ObjectMapper objectMapper=new ObjectMapper();
			
			User user=objectMapper.readValue(objectMapper.writeValueAsString(userDto), User.class);
			
			
			Page<User> pageOfUsers = userService.getAllUsers(paramDto,user);
			ResponseDto dto = new ResponseDto();
			dto.setPageNo(pageOfUsers.getPageable().getPageNumber());
			dto.setPageSize(pageOfUsers.getPageable().getPageSize());
			dto.setTotalPages(pageOfUsers.getTotalElements());
			dto.setContent(pageOfUsers.getContent());

			Response response = new Response(HttpStatus.OK.value(), MessageConstants.SUCCESS_FETCH_LIST_USERS, "", true,
					dto);
			return new ResponseEntity<Object>(response, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all users in Controller ", e.getMessage());
			Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_LIST_USER, e.getMessage(), false, "");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/user/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Integer userId) {
		LOGGER.info("Controller method to fetch user by Id");
		try {
			Optional<User> userById = userService.getUserById(userId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_FETCH_USER + userId + MessageConstants.COMMON_SUCCESS_FETCH, "", true,
					userById);
			return new ResponseEntity<Object>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user details by  userId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_FETCH_USER + userId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(path = "/user/{userId}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Integer userId) {
		LOGGER.info("Controller method to delete user by Id");
		try {
			userService.deleteUserById(userId);
			Response responseclass = new Response(HttpStatus.OK.value(),
					MessageConstants.SUCCESS_USER + userId + MessageConstants.COMMON_HAS_DELETED, "", true, "");
			return new ResponseEntity<>(responseclass, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting user by userId in Controller ", e.getMessage());
			Response responseclass = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					MessageConstants.ERROR_DELETE_USER + userId, e.getMessage(), false, "");
			return new ResponseEntity<Object>(responseclass, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
