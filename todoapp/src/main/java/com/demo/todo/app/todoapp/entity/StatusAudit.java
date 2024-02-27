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
@Table(name = "STATUS_AUDIT", schema = "TO_DO")
public class StatusAudit extends Base{

	@Id
	@Column(name = "STATUS_AUDIT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusAuditId;

	@Column(name = "MODE")
	private String mode;

	@Column(name = "STATUS_ID")
	private Integer statusId;

	@Column(name = "STATUS_NAME")
	private String statusName;

}
