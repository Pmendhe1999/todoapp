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
public class CategoryAuditDto extends BaseDto{

	private Integer categoryAuditId;

	private String mode;

	private Integer categoryId;

	private String categoryName;

	private String description;
}
