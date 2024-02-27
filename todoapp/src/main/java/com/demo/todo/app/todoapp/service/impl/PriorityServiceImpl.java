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
import com.demo.todo.app.todoapp.controller.UserController;
import com.demo.todo.app.todoapp.dto.RequestParamDto;
import com.demo.todo.app.todoapp.entity.Priority;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.PriorityRepository;
import com.demo.todo.app.todoapp.service.PriorityService;

import jakarta.validation.Valid;

@Service
public class PriorityServiceImpl implements PriorityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriorityServiceImpl.class);

	@Autowired
	private PriorityRepository priorityRepository;

	@Override
	public Page<Priority> getAllPriority(@Valid RequestParamDto paramDto, Priority priority) {
		try {
			LOGGER.info("Inside the getAllPriority method in Service");

			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);

			Page<Priority> listOfPriority = priorityRepository.findAll(serachCriteriaForPriority(priority), pageable);
			return listOfPriority;

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all priority data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getAllPriority method");
		}
	}

	private Specification<Priority> serachCriteriaForPriority(Priority priority) {
		Specification<Priority> specification = Specification.where(null);

		if (priority.getPriorityName() != null) {
			specification = specification.and(containsFirstName(priority.getPriorityName()));
		}

		return specification;
	}

	private Specification<Priority> containsFirstName(String priorityName) {
		return (priorirty, cq, cb) -> cb.like(cb.upper(priorirty.get(MessageConstants.SPECIFICATION_PRIORITY_NAME)),
				"%" + priorityName.toUpperCase() + "%");
	}

	@Override
	public Optional<Priority> getPriorityById(Integer priorityId) {
		LOGGER.info("Controller method to getPriority  by Id");

		try {
			return Optional.ofNullable(priorityRepository.findById(priorityId))
					.orElseThrow(() -> new GlobalServiceException("user not found for id: " + priorityId));
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching priority details by priorityId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getPriorityById method: " + e.getMessage());
		}

	}

	@Override
	public void deletePriorityById(Integer priorityId) {
		LOGGER.info("Controller method to delete priority by Id");

		try {
			if (priorityRepository.existsById(priorityId)) {
				priorityRepository.deleteById(priorityId);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting prioritu by priorityId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in deletePriorityById method: " + e.getMessage());
		}

	}

	@Override
	public List<Priority> saveOrUpdatePriority(List<Priority> listOfpriority) {
		LOGGER.info("Inside the saveOrUpdatePriority method in Service");

		try {
			return priorityRepository.saveAllAndFlush(listOfpriority);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate priority  in Service", e.getMessage());
			throw new GlobalServiceException("Exception in priority saveOrUpdate method: " + e.getMessage());
		}
		}

}
