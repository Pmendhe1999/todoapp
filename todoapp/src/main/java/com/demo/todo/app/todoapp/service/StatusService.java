package com.demo.todo.app.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Status;
import com.demo.todo.app.todoapp.entity.User;

import jakarta.validation.Valid;

public interface StatusService {

	Page<Status> getAllStatus(@Valid RequestParamDto paramDto, Status status);

	Optional<Status> getUserStatusId(Integer statusId);

	List<Status> saveAndUpdateStatus(List<Status> listOfStatus);

	void deleteStatus(Integer statusId);

}
