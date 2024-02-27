package com.demo.todo.app.todoapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.demo.todo.app.todoapp.constants.MessageConstants;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Category;
import com.demo.todo.app.todoapp.entity.Task;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.TaskRepository;
import com.demo.todo.app.todoapp.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Override
	public Page<Task> getAllTasks(RequestParamDto paramDto, Task task) {
		LOGGER.info("Inside the getAllTasks method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<Task> listOfUsers = taskRepository.findAll(serachCriteriaForTasks(task), pageable);
			return listOfUsers;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all tasks data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getAllTasks method");
		}

	}

	private Specification<Task> serachCriteriaForTasks(Task task) {

		Specification<Task> specification = Specification.where(null);
		if (task.getTaskname() != null) {
			specification = specification.and(containsTaskName(task.getTaskname()));
		}
		return specification;
	}

	private Specification<Task> containsTaskName(String taskname) {

		return (task, cq, cb) -> cb.like(cb.upper(task.get(MessageConstants.SPECIFICATION_TASK_NAME)),
				"%" + taskname.toUpperCase() + "%");
	}

	@Override
	public Optional<Task> getTaskById(Integer taskId) {
		LOGGER.info("Inside the getTaskById method in Service");

		try {
//			return Optional.ofNullable(taskRepository.findById(taskId))
//					.orElseThrow(() -> new GlobalServiceException("task not found for id: " + taskId));

			Optional<Task> newTask = taskRepository.findById(taskId);
			Task task = newTask.get();
			Category category = task.getCategoryId();
			return newTask;

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user details by userId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getUserById method: " + e.getMessage());
		}
	}

	@Override
	public void deleteTaskById(Integer taskId) {
		LOGGER.info("Inside the deleteTaskById method in Service");

		try {
			if (taskRepository.existsById(taskId)) {
				taskRepository.deleteById(taskId);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting task by taskId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in deleteTaskById method: " + e.getMessage());
		}

	}

	@Override
	public List<Task> saveOrUpdateTasks(List<Task> listOfTasks) {
		LOGGER.info("Inside the saveOrUpdateTaks method in Service");

		try {
			return taskRepository.saveAllAndFlush(listOfTasks);

		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate task  in Service", e.getMessage());
			throw new GlobalServiceException("Exception in task saveOrUpdate method: " + e.getMessage());
		}

	}

	@Override
	public List<Task> getTaskByUserId(Integer taskByUserId) {
		LOGGER.info("Inside the get task by user Id method in Service");

		try {
			List<Task> listOfTask = taskRepository.getTaskByUserId(taskByUserId);
			return listOfTask;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

}
