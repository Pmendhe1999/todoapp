package com.demo.todo.app.todoapp.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "TASK", schema = "TO_DO")
public class Task extends Base{

	@Id
	@Column(name = "TASK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;
	
	@Column(name = "TASK_NAME")
	private String taskname;
	
	@Column(name = "TAST_TITLE")
	private String taskTitle;
	
	@Column(name = "TASK_DESCRIPTION")
	private String taskDescription;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", updatable = false,insertable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID",updatable = false,insertable = false)
	private Category categoryId;
	
	@ManyToOne
	@JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID",updatable = false,insertable = false)
	private Status statusId;
	
	@ManyToOne
	@JoinColumn(name = "PRIORITY_ID", referencedColumnName = "PRIORITY_ID",updatable = false,insertable = false)
	private Priority priorityId;
	
	@Column(name = "TASK_START_DATE")
	private Date taskStartDate;
	
	@Column(name = "COMPLETE_DATE")
	private Date completeDate;
	
	
}
