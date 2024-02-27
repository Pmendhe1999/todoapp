package com.demo.todo.app.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Task;
import com.demo.todo.app.todoapp.entity.User;

public interface TaskService {

	Page<Task> getAllTasks(RequestParamDto paramDto, Task task);

	Optional<Task> getTaskById(Integer taskId);

	void deleteTaskById(Integer taskId);

	List<Task> saveOrUpdateTasks(List<Task> listOfTasks);

	List<Task> getTaskByUserId(Integer taskByUserId);

}
