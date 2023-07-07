package com.saksoft.usermangementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "first_name",length = 100)
	@NotBlank(message = "Please enter your first name")
	private String firstName;
	
	@Column(name = "last_name",length = 100)
	@NotBlank(message = "Please enter your last name")
	private String lastName;
	
	@Column(name = "email")
	@Email
	private String email;
	
	@Column(name = "age")
	@NotNull
	private int age;

}
