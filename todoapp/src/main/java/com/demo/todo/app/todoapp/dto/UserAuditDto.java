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
public class UserAuditDto extends BaseDto{

	private Integer userAuditId;

	private String mode;

	private Integer userId;

	private String firstName;

	private String lastName;

	private String gender;

	private String emailId;

	private String phoneNo;
}
