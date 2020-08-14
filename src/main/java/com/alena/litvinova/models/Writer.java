package com.alena.litvinova.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "WRITTERS")
public class Writer {
	public Writer(){}
	public Writer(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "ID")
	private Integer id;
	 
	 @NotBlank(message = "FirstName is mandatory")

	 @Column(name = "FIRSTNAME")
	private String firstName;
	 
	 @NotBlank(message = "LastName is mandatory")
	 @Column(name = "LASTNAME")
	 private String lastName;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
