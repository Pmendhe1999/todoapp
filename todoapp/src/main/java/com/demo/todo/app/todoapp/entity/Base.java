package com.demo.todo.app.todoapp.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Base {

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@Column(name = "ACTIVE")
	private Boolean active;
}
