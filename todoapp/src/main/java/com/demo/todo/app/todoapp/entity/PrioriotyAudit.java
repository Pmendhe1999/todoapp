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
@Table(name = "PRIORITY_AUDIT", schema = "TO_DO")
public class PrioriotyAudit extends Base{

	@Id
	@Column(name = "PRIORITY_AUDIT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priorityAuditId;

	@Column(name = "MODE")
	private String mode;

	@Column(name = "PRIORITY_ID")
	private Integer priorityId;

	@Column(name = "PRIORITY_NAME")
	private String priorityName;

}
