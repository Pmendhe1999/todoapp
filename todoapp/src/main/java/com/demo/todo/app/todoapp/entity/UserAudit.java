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
@Table(name = "USER_AUDIT", schema = "TO_DO")
public class UserAudit extends Base{

	@Id
	@Column(name = "USER_AUDIT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userAuditId;

	@Column(name = "MODE")
	private String mode;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PHONE_NO")
	private String phoneNo;

}
