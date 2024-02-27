package com.demo.todo.app.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Priority;

import jakarta.validation.Valid;

public interface PriorityService {

	Page<Priority> getAllPriority(@Valid RequestParamDto paramDto, Priority priority);

	Optional<Priority> getPriorityById(Integer priorityId);

	void deletePriorityById(Integer priorityId);

	List<Priority> saveOrUpdatePriority(List<Priority> listOfpriority);

}
