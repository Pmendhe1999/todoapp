package com.demo.todo.app.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorInfo {
	
	private String success;

	private String message;

	private String errorMessage;

	private Integer errorCode;
}
