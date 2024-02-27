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
@Table(name = "CATEGORY_AUDIT", schema = "TO_DO")
public class CategoryAudit extends Base {

	@Id
	@Column(name = "CATEGORY_AUDIT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryAuditId;

	@Column(name = "MODE")
	private String mode;

	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "DESCRIPTION")
	private String description;
}
