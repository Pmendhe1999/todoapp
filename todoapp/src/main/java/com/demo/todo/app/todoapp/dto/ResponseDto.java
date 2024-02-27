package com.demo.todo.app.todoapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	private int pageNo;

	private Long totalPages;

	private int pageSize;

	private List<?> content;

}
