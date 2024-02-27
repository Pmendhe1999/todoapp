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
import com.demo.todo.app.todoapp.entity.User;
import com.demo.todo.app.todoapp.exception.GlobalServiceException;
import com.demo.todo.app.todoapp.repository.UserRepository;
import com.demo.todo.app.todoapp.service.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;


	private Specification<User> containsEmailId(String emailId) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_EMAIL_ID)),
				"%" + emailId.toUpperCase() + "%");
	}

	private Specification<User> containsGender(String gender) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_GENDER)),
				"%" + gender.toUpperCase() + "%");

	}

	private Specification<User> containsPhoneNo(String phoneNo) {

		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_PHONE_NO)),
				"%" + phoneNo.toUpperCase() + "%");
	}

	private Specification<User> containsLastName(String lastName) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_LAST_NAME)),
				"%" + lastName.toUpperCase() + "%");

	}

	private Specification<User> containsFirstName(String firstName) {
		return (userAudit, cq, cb) -> cb.like(cb.upper(userAudit.get(MessageConstants.SPECIFICATION_FIRST_NAME)),
				"%" + firstName.toUpperCase() + "%");
	}

	
	private Specification<User> serachCriteriaForUser(User user) {

		Specification<User> specification = Specification.where(null);
		if (user.getFirstName() != null) {
			specification = specification.and(containsFirstName(user.getFirstName()));
		}
		if (user.getLastName() != null) {
			specification = specification.and(containsLastName(user.getLastName()));
		}
		if (user.getPhoneNo() != null) {
			specification = specification.and(containsPhoneNo(user.getPhoneNo()));
		}
		if (user.getGender() != null) {
			specification = specification.and(containsGender(user.getGender()));
		}
		if (user.getEmailId() != null) {
			specification = specification.and(containsEmailId(user.getEmailId()));
		}

		return specification;
	}
	
	
	
	@Override
	public List<User> saveOrUpdateUser(List<User> listOfUsers) {
		LOGGER.info("Inside the saveOrUpdateUser method in Service");
		try {
			return userRepository.saveAllAndFlush(listOfUsers);
		} catch (Exception e) {
			LOGGER.error("Error occured while saveOrUpdate user  in Service", e.getMessage());
			throw new GlobalServiceException("Exception in user saveOrUpdate method: " + e.getMessage());
		}

	}

	@Override
	public Page<User> getAllUsers(@Valid RequestParamDto paramDto, User user) {
		LOGGER.info("Inside the getAllUsers method in Service");
		try {

			Sort sort = Sort.by(paramDto.getSortDir().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
					paramDto.getSortKey());
			Pageable pageable = PageRequest.of(paramDto.getPageNo(), paramDto.getRecordsPerPage(), sort);
			Page<User> listOfUsers = userRepository.findAll(serachCriteriaForUser(user), pageable);
			return listOfUsers;
		} catch (GlobalServiceException e) {
			LOGGER.error("Error occured while fetching all contentType data in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getAllContentTypes method");
		}
	}

	

	@Override
	public Optional<User> getUserById(Integer userId) {
		LOGGER.info("Inside the getUserById method in Service");
		try {
			return Optional.ofNullable(userRepository.findById(userId))
					.orElseThrow(() -> new GlobalServiceException("user not found for id: " + userId));
		} catch (Exception e) {
			LOGGER.error("Error occured while fetching user details by userId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in getUserById method: " + e.getMessage());
		}
	}

	@Override
	public void deleteUserById(Integer userId) {
		LOGGER.info("Inside the deleteUserById method in Service");
		try {
			if (userRepository.existsById(userId)) {
				userRepository.deleteById(userId);

			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while deleting user by userId in Service", e.getMessage());
			throw new GlobalServiceException("Exception in deleteUserById method: " + e.getMessage());
		}

	}

}
