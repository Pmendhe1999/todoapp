package com.demo.todo.app.todoapp.dto;

import java.sql.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {

	private Integer createdBy;

	private Date createdTime;

	private Integer updatedBy;

	private Date updatedTime;

	private Boolean active;

}
