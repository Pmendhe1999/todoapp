package com.demo.todo.app.todoapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class TaskDto extends BaseDto {

	private Integer taskId;

	private String taskname;

	private String taskTitle;

	private String taskDescription;

	private UserDto userId;

	private CategoryDto categoryId;

	private StatusDto statusId;

	private PriorityDto priorityId;

	private Date taskStartDate;

	private Date completeDate;

}
