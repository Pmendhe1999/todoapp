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
public class PrioriotyAuditDto extends BaseDto{

	private Integer priorityAuditId;

	private String mode;

	private Integer priorityId;

	private String priorityName;
}
