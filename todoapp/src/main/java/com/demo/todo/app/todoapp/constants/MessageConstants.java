package com.demo.todo.app.todoapp.constants;

public class MessageConstants {

	
	//user controller
	public static final String ERROR_FETCH_LIST_USER = "Failed to fetch users";
	public static final String SUCCESS_FETCH_LIST_USERS = "users records fetched succesfully";
	public static final String SUCCESS_FETCH_USER = "user details for userId - ";
	public static final String COMMON_SUCCESS_FETCH = "fetched successfully!";
	public static final String ERROR_FETCH_USER = "Error occured while fetching userId details for userId - ";
	public static final String SUCCESS_USER = "user details for userId - ";
	public static final String COMMON_HAS_DELETED = "has been deleted";
	public static final String ERROR_DELETE_USER = "Error occured while deleting user by userId - ";
	public static final String SUCCESS_SAVE_OR_UPDATE_USER = "save Or Update user was successful";
	public static final String ERROR_SAVE_OR_UPDATE_USER = "Failed to save or update user";
	public static final String SUCCESS_SAVE_OR_UPDATE_STATUS = "save Or Update status was successful";

	//USER AUDIT
	public static final String SUCCESS_FETCH_LIST_USERS_AUDIT =	"user audit fetched successfully";
	public static final String ERROR_FETCH_LIST_USER_AUDIT = "Failed to fetch users audit";
		
	
	//specifications for user audit
	public static final String SPECIFICATION_FIRST_NAME = "firstName";
	public static final String SPECIFICATION_LAST_NAME = "lastName";
	public static final String SPECIFICATION_PHONE_NO = "phoneNo";
	public static final String SPECIFICATION_GENDER = "gender";
	public static final String SPECIFICATION_EMAIL_ID = "emailId";
	public static final String SUCCESS_SAVE_OR_UPDATE_CATEGEORY = "save Or Update category was successful";


	//category
	public static final String SUCCESS_FETCH_LIST_CATEGORY = "category records fetched succesfully";
	public static final String ERROR_FETCH_LIST_CATEGORY = "Failed to fetch category";
	public static final String SUCCESS_FETCH_CATEGORY = "user details for categoryId - ";
	public static final String ERROR_FETCH_CATEGORY = "Error occured while fetching category details for categoryId - ";
	public static final String SUCCESS_CATEGROY = "category details for categoryId - ";
	public static final String ERROR_SAVE_OR_UPDATE_CATEGORY = "Failed to save or update category";
	public static final String ERROR_DELETE_CATEGORY = "Error occured while deleting category by categoryId - ";
	public static final String SUCCESS_FETCH_LIST_CATEGORY_AUDIT = "category Audit records fetched succesfully";
	public static final String	SPECIFICATION_CATEGORY_NAME="categoryName";
	
	
	
	//status
	public static final String SUCCESS_FETCH_LIST_STATUS = "status records fetched succesfully";
	public static final String ERROR_FETCH_LIST_STATUS = "Failed to fetch status";
	public static final String SUCCESS_FETCH_STATUS = "status details for userId - ";
	public static final String ERROR_FETCH_STATUS = "Error occured while fetching status details for statusId - ";
	public static final String SPECIFICATION_STATUS_NAME = "statusName";
	public static final String ERROR_SAVE_OR_UPDATE_STATUS = "Failed to save or update status";
	public static final String SUCCESS_STATUS = "status details for categoryId - ";
	public static final String ERROR_DELETE_STATUS = "Error occured while deleting status by statusId - ";

	
	//priority
	public static final String ERROR_FETCH_LIST_PRIORITY = "Failed to fetch priority";
	public static final String SUCCESS_FETCH_LIST_PRIORITY = "priority records fetched succesfully";
	public static final String SUCCESS_FETCH_PRIORITY = "priority details for userId - ";
	public static final String SUCCESS_PRIORITY = "priority details for priority - ";
	public static final String ERROR_FETCH_PRIORITY = "Error occured while fetching priority details for userId - ";
	public static final String ERROR_DELETE_PRIORITY = "Error occured while deleting priority by prioryById - ";
	public static final String SPECIFICATION_PRIORITY_NAME = "categoryName";
	public static final String SUCCESS_FETCH_LIST_PRIORITY_AUDIT =	"priority audit fetched successfully";
	public static final String ERROR_FETCH_LIST_PRIORITY_AUDIT = "Failed to fetch priority audit";
	public static final String SUCCESS_SAVE_OR_UPDATE_PRIORITY= "save Or Update priority was successful";
	public static final String ERROR_SAVE_OR_UPDATE_PRORITY = "Failed to save or update priority";

	
	//Task
	
	public static final String SUCCESS_FETCH_LIST_TAKS = "task records fetched succesfully";
	public static final String ERROR_FETCH_LIST_TAS = "Failed to fetch task";
	public static final String SPECIFICATION_TASK_NAME = "taskName";
	public static final String ERROR_FETCH_TASK = "Error occured while fetching task details for task - ";
	public static final String SUCCESS_TASK = "task details for userId - ";
	public static final String ERROR_SAVE_OR_UPDATE_TASK = "Failed to save or update task";
	public static final String SUCCESS_FETCH_LIST_TASK_AUDIT =	"task audit fetched successfully";


}
