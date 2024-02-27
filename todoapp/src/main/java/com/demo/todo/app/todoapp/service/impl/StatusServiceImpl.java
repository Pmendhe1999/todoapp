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
import com.demo.todo.app.todoapp.entity.Status;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.StatusRepository;
import com.demo.todo.app.todoapp.service.StatusService;

import jakarta.validation.Valid;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusServiceImpl.class);

	@Override
	public Page<Status> getAllStatus(@Valid RequestParamDto paramDto, Status status) {
		LOGGER.info("Inside the getAllStatus method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<Status> listOfStatus = statusRepository.findAll(serachCriteriaForStatus(status), pageable);

			return listOfStatus;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all status data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getAllContentTypes method");
		}
	}

	private Specification<Status> serachCriteriaForStatus(Status status) {

		Specification<Status> specification = Specification.where(null);
		if (status.getStatusName() != null) {
			specification = specification.and(containsStatusName(status.getStatusName()));
		}

		return specification;
	}

	private Specification<Status> containsStatusName(String statusName) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_STATUS_NAME)),
				"%" + statusName.toUpperCase() + "%");
	}

	@Override
	public Optional<Status> getUserStatusId(Integer statusId) {
		LOGGER.info("Inside the getUserStatusId method in Service");

		try {

			return Optional.ofNullable(statusRepository.findById(statusId))
					.orElseThrow(() -> new GlobalServiceException("status not found for id: " + statusId));
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user details by status in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getUserStatusId method: " + e.getMessage());
		}

	}

	@Override
	public List<Status> saveAndUpdateStatus(List<Status> listOfStatus) {

		LOGGER.info("Inside the saveAndUpdateStatus method in Service");
		try {
			return statusRepository.saveAllAndFlush(listOfStatus);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate status  in Service", e.getMessage());
			throw new GlobalServiceException("Exception in user saveAndUpdateStatus method: " + e.getMessage());
		}
	}

	@Override
	public void deleteStatus(Integer statusId) {
		LOGGER.info("Inside the deleteStatus method in Service");

		try {
			if(statusRepository.existsById(statusId)) {
				statusRepository.deleteById(statusId);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting status by statusId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in deleteStatus method: " + e.getMessage());
		}
		
	}

}
