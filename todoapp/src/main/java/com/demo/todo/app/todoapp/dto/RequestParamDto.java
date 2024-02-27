package com.demo.todo.app.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestParamDto {
	
	private int pageNo = 0;

	private int recordsPerPage = Integer.MAX_VALUE;

	private String sortKey = "updatedTime";

	private String sortDir = "desc";
}
