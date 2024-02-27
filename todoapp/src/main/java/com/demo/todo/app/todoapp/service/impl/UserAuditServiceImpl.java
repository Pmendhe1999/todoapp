package com.demo.todo.app.todoapp.service.impl;

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
import com.demo.todo.app.todoapp.entity.UserAudit;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.UserAuditRepository;
import com.demo.todo.app.todoapp.service.UserAuditService;

import jakarta.validation.Valid;

@Service
public class UserAuditServiceImpl implements UserAuditService {

	@Autowired
	private UserAuditRepository userAuditRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuditServiceImpl.class);

	private Specification<UserAudit> containsEmailId(String emailId) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_EMAIL_ID)),
				"%" + emailId.toUpperCase() + "%");
	}

	private Specification<UserAudit> containsGender(String gender) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_GENDER)),
				"%" + gender.toUpperCase() + "%");

	}

	private Specification<UserAudit> containsPhoneNo(String phoneNo) {

		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_PHONE_NO)),
				"%" + phoneNo.toUpperCase() + "%");
	}

	private Specification<UserAudit> containsLastName(String lastName) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_LAST_NAME)),
				"%" + lastName.toUpperCase() + "%");

	}

	private Specification<UserAudit> containsFirstName(String firstName) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_FIRST_NAME)),
				"%" + firstName.toUpperCase() + "%");
	}

	private Specification<UserAudit> searchUserSpecification(UserAudit userAudit) {

		Specification<UserAudit> specification = Specification.where(null);
		if (userAudit.getFirstName() != null) {
			specification = specification.and(containsFirstName(userAudit.getFirstName()));
		}
		if (userAudit.getLastName() != null) {
			specification = specification.and(containsLastName(userAudit.getLastName()));
		}
		if (userAudit.getPhoneNo() != null) {
			specification = specification.and(containsPhoneNo(userAudit.getPhoneNo()));
		}
		if (userAudit.getGender() != null) {
			specification = specification.and(containsGender(userAudit.getGender()));
		}
		if (userAudit.getEmailId() != null) {
			specification = specification.and(containsEmailId(userAudit.getEmailId()));
		}
		return specification;
	}

	@Override
	public Page<UserAudit> getAllUserAudit(@Valid RequestParamDto paramDto, UserAudit userAudit) {
		LOGGER.info("Inside the getAllUserAudit method in Service");

		try {
			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<UserAudit> listOfSpecificationUserAudit = userAuditRepository
					.findAll(searchUserSpecification(userAudit), pageable);
			return listOfSpecificationUserAudit;
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching all user audit data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in user audit method");
		}
	}

}
