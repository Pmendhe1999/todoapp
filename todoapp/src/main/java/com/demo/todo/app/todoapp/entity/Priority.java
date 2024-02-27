package com.demo.todo.app.todoapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "PRIORITY", schema = "TO_DO")
public class Priority extends Base{

	@Id
	@Column(name = "PRIORITY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priorityId;
	
	@Column(name = "PRIORITY_NAME")
	private String priorityName;
	

}
