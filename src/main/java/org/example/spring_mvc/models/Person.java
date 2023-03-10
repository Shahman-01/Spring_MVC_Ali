package org.example.spring_mvc.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {
	private int id;
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 30, message = "Too long or too short name")
	private String name;
	@NotEmpty(message = "email cant be empty")
	@Email
	private String email;
	@Min(value = 0, message = "age cant be less than 0")
	private int age;

	public Person(int id, String name, int age, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public Person() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
