package com.demo.todo.app.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private int statusCode;

	private String message;

	private String traceMessage;

	private boolean success;

	private Object responseData;
}
