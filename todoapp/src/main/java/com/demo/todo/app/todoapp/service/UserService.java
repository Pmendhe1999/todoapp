package com.demo.todo.app.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.User;

import jakarta.validation.Valid;

public interface UserService {

	List<User> saveOrUpdateUser(List<User> listOfUsers);
	
	Page<User> getAllUsers(@Valid RequestParamDto paramDto, User user);

	Optional<User> getUserById(Integer userId);

	void deleteUserById(Integer userId);

	

}
