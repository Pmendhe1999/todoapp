package com.demo.todo.app.todoapp.dto;

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
public class StatusAuditDto extends BaseDto{

	private Integer statusAuditId;

	private String mode;

	private Integer statusId;

	private String statusName;
}
