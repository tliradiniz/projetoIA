package com.tdiniz.backend.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @NotNull
    private Date createdDate;
    
    private Date updatedDate;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String isAdmin;

	
	public User(String name, String username, String email, String password ,Date createdDate, String isAdmin) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.isAdmin = isAdmin;
	}

}
